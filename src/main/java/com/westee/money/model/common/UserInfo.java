package com.westee.money.model.common;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserInfo {
    private Long id;
    private String username;
    private String password;
}
