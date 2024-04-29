package com.example.urlshortener.domain.user.dto;

import com.example.urlshortener.domain.user.entity.ApplicationUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ApplicationUserDto {
    @NotEmpty(message = "Enter your firstname")
    private String firstName;

    @NotEmpty(message = "Enter your lastname")
    private String lastName;

    @NotEmpty(message = "Enter a username")
    private String username;

    @NotEmpty(message = "Enter an email")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Enter a password")
    private String password;

    public static ApplicationUserDto from(ApplicationUser applicationUser) {
        return ApplicationUserDto.builder()
                .firstName(applicationUser.getFirstName())
                .lastName(applicationUser.getLastName())
                .username(applicationUser.getUsername())
                .email(applicationUser.getEmail())
                .password(applicationUser.getPassword())
                .build();
    }
}
