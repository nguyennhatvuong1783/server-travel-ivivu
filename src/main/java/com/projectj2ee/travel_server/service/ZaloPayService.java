package com.projectj2ee.travel_server.service;


import com.projectj2ee.travel_server.dto.request.PaymentRequest;
import com.projectj2ee.travel_server.utils.HMACUtil;
import jakarta.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class ZaloPayService {
    @Value("${zalopay.app-id}")
    private String appId;

    @Value("${zalopay.key1}")
    private String key1;

    @Value("${zalopay.key2}")
    private String key2;

    @Value("${zalopay.endpoint}")
    private String endpoint;

    private Mac HmacSHA256;


    private static String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTime());
    }

    public String createOrder(PaymentRequest request) throws Exception {
        Random rand = new Random();
        int randomId = rand.nextInt(1000000);

        Map<String, Object> embedData = new HashMap<>();
        embedData.put("redirecturl", "https://percenren.com");

        Map<String, Object>[] item = new Map[]{new HashMap<>(){
            {
               put("booking_id",request.getBookingId());
            }
        }};

        Map<String, Object> order = new HashMap<>() {{
            put("app_id", appId);
            put("app_trans_id", getCurrentTimeString("yyMMdd") + "_" + randomId);
            put("app_time", System.currentTimeMillis());
            put("app_user", "user123");
            put("amount", request.getAmount());
            put("description", "Payment for the order #" + randomId);
            put("bank_code", "");
            put("item", new JSONArray(item).toString());
            put("embed_data", new JSONObject(embedData).toString());
            put("callback_url","https://4822-125-235-238-144.ngrok-free.app/api/auth/payment/zalo-pay-callback");
        }};

        // Generate MAC
        String data = order.get("app_id") + "|" + order.get("app_trans_id") + "|" +
                order.get("app_user") + "|" + order.get("amount") + "|" +
                order.get("app_time") + "|" + order.get("embed_data") + "|" + order.get("item");
        order.put("mac",HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, key1, data));
        log.info((String) order.get("mac"));

        // HTTP request
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(endpoint);

            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, Object> entry : order.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            for (NameValuePair param : params) {
                log.info(param.getName() + " = " + param.getValue());
            }

            post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
            try (CloseableHttpResponse res = client.execute(post);
                 BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()))) {

                StringBuilder resultJsonStr = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    resultJsonStr.append(line);
                }

                JSONObject result = new JSONObject(resultJsonStr.toString());

                // Kiểm tra phản hồi từ API
                if (result.getInt("return_code") == 1) { // Success code = 1
                    String token = result.getString("zp_trans_token"); // Lấy mã token
                    return result.getString("order_url"); // Trả về URL điều hướng FE
                } else {
                    throw new RuntimeException("ZaloPay API Error: " + result.getInt("sub_return_code"));
                }

            }
        }
    }

    public String callback( String jsonStr) {
        try {
            HmacSHA256 = Mac.getInstance("HmacSHA256");
            HmacSHA256.init(new SecretKeySpec(key2.getBytes(), "HmacSHA256"));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        JSONObject result = new JSONObject();

        try {
            JSONObject cbdata = new JSONObject(jsonStr);
            String dataStr = cbdata.getString("data");
            String reqMac = cbdata.getString("mac");

            byte[] hashBytes = HmacSHA256.doFinal(dataStr.getBytes());
            String mac = DatatypeConverter.printHexBinary(hashBytes).toLowerCase();

            // kiểm tra callback hợp lệ (đến từ ZaloPay server)
            if (!reqMac.equals(mac)) {
                // callback không hợp lệ
                result.put("return_code", -1);
                result.put("return_message", "mac not equal");
            } else {
                // thanh toán thành công
                // merchant cập nhật trạng thái cho đơn hàng
                JSONObject data = new JSONObject(dataStr);
                log.info("update order's status = success where app_trans_id = " + data.getString("app_trans_id"));

                result.put("return_code", 1);
                result.put("return_message", "success");
                result.put("zp_trans_id",data.get("zp_trans_id"));
            }
        } catch (Exception ex) {
            result.put("return_code", 0); // ZaloPay server sẽ callback lại (tối đa 3 lần)
            result.put("return_message", ex.getMessage());
        }

        // thông báo kết quả cho ZaloPay server
        return result.toString();
    }
}
