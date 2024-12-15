package com.manish.WorkHub.dto;

import com.manish.WorkHub.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ApiResponse {
    private String email;
    private String name;
    private String password;
    private String profileUrl;
    private String phoneNo;
    private Role role;
    private Date date;
    private List<String> interests;
}
