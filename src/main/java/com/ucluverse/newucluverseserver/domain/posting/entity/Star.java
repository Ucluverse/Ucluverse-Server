package com.ucluverse.newucluverseserver.domain.posting.entity;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import com.ucluverse.newucluverseserver.domain.member.Member;
import com.ucluverse.newucluverseserver.domain.posting.entity.Posting;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Star extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne()
    @JoinColumn(name = "posting_id")
    private Posting posting;
}
