package com.ucluverse.newucluverseserver.domain.posting;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import com.ucluverse.newucluverseserver.domain.club.Club;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Posting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private boolean isPublic;
    private boolean allowComments;
    @ManyToOne()
    @JoinColumn(name = "clubPosting_id")
    private ClubPosting clubPosting;
}
