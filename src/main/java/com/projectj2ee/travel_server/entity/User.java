package com.projectj2ee.travel_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User{
    private static  final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String full_name;

    @Column
    private String phone_number;

    @Column
    private String address;

    @Column
    private Date date_of_birth;

    @Temporal(TemporalType.DATE)
    @Column(name = "registration_date")
    private Date registration = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

    @Column
    private Date updated_at;

    @Column
    private Date deleted_at;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean status;

    private Set<String> roles;

    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;

}
