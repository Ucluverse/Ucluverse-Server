package com.ucluverse.newucluverseserver.domain.club;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberClubRole {
    CLUB_GUEST("CLUB_GUEST", "동아리 비회원"),
    CLUB_ADMIN("CLUB_ADMIN", "동아리 관리자"),
    CLUB_MEMBER("CLUB_MEMBER", "동아리 회원");

    private final String key;
    private final String title;
}
