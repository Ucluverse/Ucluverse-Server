package com.ucluverse.newucluverseserver.domain.posting;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import com.ucluverse.newucluverseserver.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne()
    @JoinColumn(name = "posting_id")
    private Posting posting;
}
