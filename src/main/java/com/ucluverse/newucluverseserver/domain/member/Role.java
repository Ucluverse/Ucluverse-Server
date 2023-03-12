package com.ucluverse.newucluverseserver.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER("ROLE_USER", "일반 사용자"),
    MANAGER("ROLE_MANAGER", "동아리 장"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
