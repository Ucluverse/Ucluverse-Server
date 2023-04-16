package com.ucluverse.newucluverseserver.domain.posting.dto;

import lombok.Getter;

@Getter
public class CreatePostingRequest {
    String title;
    String content;
    boolean isPublic;
    boolean allowComments;
    int clubPosting_id;
}
