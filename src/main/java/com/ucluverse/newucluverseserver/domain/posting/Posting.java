package com.ucluverse.newucluverseserver.domain.posting;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Posting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private boolean isPublic;
    private boolean allowComments;

}
