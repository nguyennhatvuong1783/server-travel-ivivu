package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.ChangePasswordRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.ForgotPassword;
import com.projectj2ee.travel_server.entity.User;
import com.projectj2ee.travel_server.repository.ForgotPasswordRepository;
import com.projectj2ee.travel_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
@AllArgsConstructor
public class ForgotPasswordController {

    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/verifyMail/{email}")
    public ApiResponse<String> verifyEmail(@PathVariable("email") String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("Please provide an valid email"));

        int otp = otpGenerator();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("trannhatqui712@gmail.com");
        message.setSubject("OTP for your Forgot Password request");
        message.setText("This is OTP for your Forgot Password request : " + otp);


        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000))
                .user(user)
                .build();

        javaMailSender.send(message);
        forgotPasswordRepository.save(fp);
        return new ApiResponse<>(HttpStatus.OK.value(), "Email sent for verification!");
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ApiResponse<String> verifyOtp(@PathVariable("otp") Integer otp, @PathVariable("email") String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("Please provide an valid email " ));

        ForgotPassword forgotPassword = forgotPasswordRepository.findByOtpAndUser(otp,user)
                .orElseThrow(()->new RuntimeException("Invalid OTP for email " + email));


        if (forgotPassword.getExpirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepository.deleteById(forgotPassword.getFpid());
            return new ApiResponse<>(HttpStatus.EXPECTATION_FAILED.value(), "OTP has expired");
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "OTP verified!");
    }

    @PostMapping("/changePassword/{email}")
    public ApiResponse<String> changePasswordHandler(@RequestBody ChangePasswordRequest changePasswordRequest,
                                                     @PathVariable("email") String email){
        if (!Objects.equals(changePasswordRequest.password(),changePasswordRequest.repeatPassword())){
            return new ApiResponse<>(HttpStatus.EXPECTATION_FAILED.value(), "Please enter the password again!");
        }

        String encodedPassword = passwordEncoder.encode(changePasswordRequest.password());
        userRepository.updatePassword(email,encodedPassword);

        return new ApiResponse<>(HttpStatus.OK.value(), "Password has been changed!");
    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000,999_999);
    }
}
