package com.ucluverse.newucluverseserver.domain.club;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClubType {
    DEPARTMENT("DEPARTMENT_CLUB", "과 소학회"),
    COLLEGE("COLLEGE_CLUB", "중앙 동아리");

    private final String key;
    private final String title;
}
