package com.ucluverse.newucluverseserver.domain.posting.dto;

import lombok.Getter;

@Getter
public class UpdatePostingRequest {
    Long id;
    String title;
    String content;
    boolean isPublic;
    boolean allowComments;
}
