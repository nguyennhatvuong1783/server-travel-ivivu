package com.projectj2ee.travel_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    private static  final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date registration_date;

    @Column
    private Date updated_at;

    @Column
    private Date deleted_at;

    @Column
    private Boolean status;

    @Column(name = "is_admin")
    private Boolean isAdmin;

}
