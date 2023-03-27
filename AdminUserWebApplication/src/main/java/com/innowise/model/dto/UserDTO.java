package com.innowise.model.dto;

import com.innowise.model.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private String fullName;
    private String position;
    private String department;
    private String address;
    private String phoneNumber;
}
