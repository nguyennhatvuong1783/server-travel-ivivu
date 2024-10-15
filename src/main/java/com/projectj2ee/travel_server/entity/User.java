package com.projectj2ee.travel_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@ToString
public class User {
    private static  final long serialVersionUID = 1L;

    @Id
    private int user_id;

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

    @Column
    private Date registration_date;


}
