package com.projectj2ee.travel_server.entity;

import com.projectj2ee.travel_server.dto.enums.PaymentMethod;
import com.projectj2ee.travel_server.dto.enums.StatusPayment;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "payments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    int id;

    @ManyToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false,precision = 10,scale = 2)
    BigDecimal amount;

    @Column(name = "payment_date")
    Date date =  Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    PaymentMethod method;

    @Column(name = "transaction_id")
    String transaction;

    @Enumerated(EnumType.STRING)
    @Column
    StatusPayment status;

}
