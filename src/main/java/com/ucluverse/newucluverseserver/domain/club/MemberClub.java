package com.ucluverse.newucluverseserver.domain.club;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import com.ucluverse.newucluverseserver.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MemberClub extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberClub_id")
    private long id;
    private MemberClubRole role;
    private boolean status;
    private boolean star;
    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne()
    @JoinColumn(name = "club_id")
    private Club club;
}
