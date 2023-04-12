package com.ucluverse.newucluverseserver.domain.posting;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
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
    @JoinColumn(name = "clubPosting_id")
    private ClubPosting clubPosting;
    @OneToMany(mappedBy = "posting")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "posting")
    private List<Star> likes = new ArrayList<>();
}
