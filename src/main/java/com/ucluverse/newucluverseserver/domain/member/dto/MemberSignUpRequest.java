package com.ucluverse.newucluverseserver.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberSignUpRequest {
    private String email;
    private String userName;
    private String nickName;
    private String contactNumber;
    private String department;
}
