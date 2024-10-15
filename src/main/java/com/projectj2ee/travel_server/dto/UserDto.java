package com.projectj2ee.travel_server.dto;



import lombok.*;

import java.util.Date;

@Data
public class UserDto {

    private int user_id;
    private String username;

    private String password;

    private String email;

    private String full_name;

    private String phone_number;

    private String address;

    private Date date_of_birth;

    private Date registration_date;
}
