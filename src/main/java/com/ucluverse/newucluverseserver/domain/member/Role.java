package com.ucluverse.newucluverseserver.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    GUEST("ROLE_GUEST", "회원가입 필요"),
    USER("ROLE_USER", "일반 사용자"),
    MANAGER("ROLE_MANAGER", "동아리 장"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
