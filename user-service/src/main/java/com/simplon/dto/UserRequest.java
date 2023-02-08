package com.simplon.dto;


import com.simplon.utils.UserDepartment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserRequest {

    private String name;
    private UserDepartment category;
    private BigDecimal amount;
}
