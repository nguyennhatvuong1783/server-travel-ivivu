package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.enums.StatusBooking;
import com.projectj2ee.travel_server.dto.request.BookingRequest;
import com.projectj2ee.travel_server.dto.request.EmailDto;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.dto.response.PageResponse;
import com.projectj2ee.travel_server.entity.*;
import com.projectj2ee.travel_server.mapper.BookingMapper;
import com.projectj2ee.travel_server.repository.*;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService {
    @Autowired
    private final BookingRepository bookingRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TourDateRepository tourDateRepository;

    @Autowired
    private final TourPackageRepository tourPackageRepository;

    @Autowired
    private final BookingPromotionRepository bookingPromotionRepository;

    @Autowired
    private final  PromotionRepository promotionRepository;

    @Autowired
    private BookingMapper bookingMapper;

    private final JavaMailSender javaMailSender;


    @PreAuthorize("hasAuthority('ADMIN')")
    public PageResponse<Booking> getAllBooking(int page, int size){
        Sort sort = Sort.by("booking_id").descending();
        Pageable pageable = PageRequest.of(page -1,size,sort);
        var pageData = bookingRepository.findAll(pageable);
        return PageResponse.<Booking>builder()
                .statusCode(HttpStatus.OK.value())
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream().toList())
                .build();
    }

    @PostAuthorize("returnObject.data.username == authentication.name")
    public ApiResponse<Booking> getBookingById(int id){
        Booking entity = bookingRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Booking not found"));
        return new ApiResponse<>(HttpStatus.OK.value(), "Success",entity);
    }


    @PreAuthorize("hasAuthority('USER', 'ADMIN)")
    public ApiResponse<Booking> addBooking(BookingRequest bookingRequest){
        if (!userRepository.existsById(bookingRequest.getUserId())){
            throw new RuntimeException("User not found");
        }

        if (!tourDateRepository.existsById(bookingRequest.getTourDateId())){
            throw new RuntimeException("TourDate not found");
        }

        Booking entity = bookingMapper.toBooking(bookingRequest);
        entity.setStatus(StatusBooking.PENDING);

        bookingRepository.save(entity);

        // check promotion -> update price
        Optional<Booking> bookings = bookingRepository.findByUser_IdAndStatus(bookingRequest.getUserId(),StatusBooking.PENDING);
        if (bookings.isPresent()){
            Booking booking = bookings.get();
            List<BookingPromotion> bookingPromotions = bookingPromotionRepository.findById_BookingId(booking.getId());
            if (!bookingPromotions.isEmpty()){
                List<Integer> promotionId = new ArrayList<>();
                bookingPromotions.forEach(p -> promotionId.add(p.getId().getPromotionId()));

                List<Promotion> promotions = new ArrayList<>();
                promotionId.forEach(id ->{
                    Optional<Promotion> promotionOpt = promotionRepository.findById(id);
                    promotionOpt.ifPresent(promotions::add);
                });

                List<BigDecimal> discount = new ArrayList<>();
                promotions.forEach(d -> discount.add(d.getDiscount()));

                BigDecimal totalPrice = booking.getTotalPrice();
                BigDecimal discoundAmout = new BigDecimal("0");
                discount.forEach(amout -> discoundAmout.add(amout));

                BigDecimal finalPrice = totalPrice.subtract(discoundAmout);
                booking.setTotalPrice(finalPrice);

                bookingRepository.save(booking);

            }
        }

        // Send mail to confirm
        Optional<TourDate> tourDateOpt = tourDateRepository.findById(bookingRequest.getTourDateId());
        TourDate tourDate = tourDateOpt.orElseGet(TourDate::new);

        Optional<TourPackage> tourPackageOpt = tourPackageRepository.findById(tourDate.getId());
        TourPackage tourPackage = tourPackageOpt.orElseGet(TourPackage::new);
        User user = userRepository.findById(bookingRequest.getUserId());
        emailService(user,tourPackage,entity,tourDate);

        return new ApiResponse<>(HttpStatus.CREATED.value(), "Booking Success",entity);
    }

    @PreAuthorize("hasAuthority('USER')")
    public ApiResponse<Booking> updateStatus(int id,String status){
        Booking entity = bookingRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Booking not found"));
        switch (status){
            case "confirm" :
                entity.setStatus(StatusBooking.CONFIRMED);
                break;
            case "complete" :
                entity.setStatus(StatusBooking.COMPLETED);
                break;
            case "cancelled" :
                entity.setStatus(StatusBooking.CANCELLED);
                break;
            default:
                throw new RuntimeException("Action not valid");
        }
        bookingRepository.save(entity);
        return new ApiResponse<>(HttpStatus.OK.value(), "Update success",entity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Void> deleteBooking(int id){
        Booking entity = bookingRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Booking not found"));
        bookingRepository.delete(entity);
        return new ApiResponse<>(HttpStatus.OK.value(), "Delete success");
    }

    private void emailService(User user, TourPackage tourPackage, Booking entity, TourDate tourDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String email = user.getEmail();
        String tourName = tourPackage.getName();
        String tourId = String.valueOf(tourDate.getId());
        String duration = String.valueOf(tourPackage.getDuration());
        String participant = String.valueOf(entity.getParticipants());
        String start = formatter.format(tourDate.getStartDate());
        String end = formatter.format(tourDate.getEndDate());
        String name = user.getFull_name();
        String phone = user.getPhone_number();
        String pricePackage = String.valueOf(tourPackage.getPrice());
        String totalPrice = String.valueOf(entity.getTotalPrice());
        EmailDto emailDto = EmailDto.builder()
                .email(email)
                .tourName(tourName)
                .tourId(tourId)
                .duration(duration)
                .participant(participant)
                .startDate(start)
                .endDate(end)
                .name(name)
                .phone(phone)
                .pricePackage(pricePackage)
                .totalPrice(totalPrice)
                .build();
        sendEmail(emailDto);
    }


    public String sendEmail(EmailDto emailDto){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("trannhatqui712@gmail.com");
            helper.setTo(emailDto.getEmail());
            helper.setSubject("Xác nhận đặt tour");

            try(var inputStream = Objects.requireNonNull(BookingService.class.getResourceAsStream("/templates/email-content.html"))) {
                String htmlContent =  new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

                htmlContent = htmlContent.replace("{{tourName}}",emailDto.getTourName())
                        .replace("{{id}}",emailDto.getTourId())
                        .replace("{{duration}}",emailDto.getDuration() + " ngày")
                        .replace("{{adult}}",emailDto.getParticipant())
                        .replace("{{start}}",emailDto.getStartDate())
                        .replace("{{end}}",emailDto.getEndDate())
                        .replace("{{name}}",emailDto.getName())
                        .replace("{{phone}}",emailDto.getPhone())
                        .replace("{{email}}",emailDto.getEmail())
                        .replace("{{pricePackage}}",emailDto.getPricePackage())
                        .replace("{{totalPrice}}", emailDto.getTotalPrice());
                helper.setText(htmlContent, true);
            };

            helper.addInline("logo.png",new ClassPathResource("templates/logo.png"));
            javaMailSender.send(message);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Success";
    }
}
