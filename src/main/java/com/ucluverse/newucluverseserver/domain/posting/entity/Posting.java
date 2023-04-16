package com.ucluverse.newucluverseserver.domain.posting.entity;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import com.ucluverse.newucluverseserver.domain.member.Member;
import com.ucluverse.newucluverseserver.domain.posting.dto.UpdatePostingRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Posting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_id")
    private Long id;
    private String title;
    private String content;
    private boolean isPublic;
    private boolean allowComments;
    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne()
    @JoinColumn(name = "clubPosting_id")
    private ClubPosting clubPosting;
    @OneToMany(mappedBy = "posting")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "posting")
    private List<Star> likes = new ArrayList<>();

    public void update(UpdatePostingRequest dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.isPublic = dto.isPublic();
        this.allowComments = dto.isAllowComments();
    }
}
