package com.ucluverse.newucluverseserver.domain.club.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ucluverse.newucluverseserver.common.BaseEntity;
import com.ucluverse.newucluverseserver.domain.club.MemberClubRole;
import com.ucluverse.newucluverseserver.domain.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberClub extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberClub_id")
    private long id;
    private MemberClubRole role;
    @ColumnDefault("false")
    private boolean status;
    @ColumnDefault("false")
    private boolean star;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "club_id")
    private Club club;

    public void updateStatus(boolean status){
        this.status = status;
    }
    public void updateStar(boolean star){
        this.star = star;
    }
}
