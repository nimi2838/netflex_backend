package com.mysite.nexfilx.auth.jwt;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseDto {
    private String code;

    private String message;

    private String status;
}
