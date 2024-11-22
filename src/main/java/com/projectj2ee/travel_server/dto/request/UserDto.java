package com.projectj2ee.travel_server.dto.request;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Data
public class UserDto {
    private String username;

    @Size(min = 8, message = "Password must be at least 8 character")
    private String password;

    @Email
    private String email;

    @NotBlank(message = "The name field can't be plank")
    private String full_name;

    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    private String phone_number;

    private String address;

    private Date date_of_birth;

}
