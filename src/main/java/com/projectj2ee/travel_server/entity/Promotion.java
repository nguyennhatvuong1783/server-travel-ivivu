package com.projectj2ee.travel_server.entity;

import com.projectj2ee.travel_server.dto.enums.PromotionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    int id;

    @Column
    String code;

    @Column
    String description;

    @Enumerated(EnumType.STRING)
    @Column(name="discount_type")
    PromotionType type;


    @Column(name = "discount_percentage",precision = 10,scale = 2)
    BigDecimal discount;

    @Column(name = "start_date")
    Date start;

    @Column(name = "end_date")
    Date end;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    Boolean status;
}
