package com.ucluverse.newucluverseserver.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class GetSocialOAuthRes {
    private String jwtToken;
    private int member_idx;
    private String googleAccessToken;
}
