package com.projectj2ee.travel_server.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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

    private Set<String> roles;


}
