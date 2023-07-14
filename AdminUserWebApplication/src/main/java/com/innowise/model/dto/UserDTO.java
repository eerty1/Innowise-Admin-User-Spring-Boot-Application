package com.innowise.model.dto;

import com.innowise.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.innowise.model.User.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 30, message = NOT_IN_RANGE)
    private String fullName;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 30, message = NOT_IN_RANGE)
    private String position;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 30, message = NOT_IN_RANGE)
    private String department;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 30, message = NOT_IN_RANGE)
    private String address;
    @Pattern(regexp = PHONE_REGEX, message = DOESNT_MATCH_FORMAT)
    private String phoneNumber;
}
