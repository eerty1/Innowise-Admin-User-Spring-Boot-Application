package com.innowise.model.dto;

import com.innowise.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private static final String NOT_BLANK = "must not be blank";
    private static final String NOT_NULL = "must not be null";
    private static final String NOT_IN_RANGE = "must not be longer than {max}";
    private static final String DOESNT_MATCH_FORMAT = "must match belarusian format";
    private static final String PHONE_REGEX = "^(\\+375)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";
    private Long id;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 30, message = NOT_IN_RANGE)
    private String username;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 100, message = NOT_IN_RANGE)
    private String password;
    @NotNull(message = NOT_NULL)
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
