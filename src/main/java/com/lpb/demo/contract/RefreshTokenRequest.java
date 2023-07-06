package com.lpb.demo.contract;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenRequest {
    private Integer id;
    
    @NonNull
    private String refreshToken;
}
