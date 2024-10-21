package com.projectj2ee.travel_server.dto.request;



import com.projectj2ee.travel_server.validation.PasswordSpecialCharacter;
import jakarta.validation.constraints.Email;
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

    private String full_name;

    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    private String phone_number;

    private String address;

    private Date date_of_birth;

    private Boolean isAdmin;
}
