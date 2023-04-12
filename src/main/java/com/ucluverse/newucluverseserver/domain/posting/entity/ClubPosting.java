package com.ucluverse.newucluverseserver.domain.posting.entity;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import com.ucluverse.newucluverseserver.domain.club.entity.Club;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class ClubPosting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String boardName;
    @ManyToOne()
    @JoinColumn(name = "club_id")
    private Club club;
    @OneToMany(mappedBy = "clubPosting")
    private List<Posting> postings = new ArrayList<>();
}
