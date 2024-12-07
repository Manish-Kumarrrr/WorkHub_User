package com.manish.WorkHub.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoadUserInfoRequest {
    String email;
    String password;
    public String getEmail() {
        return this.email;
    }
}
