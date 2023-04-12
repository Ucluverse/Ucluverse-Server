package com.ucluverse.newucluverseserver.domain.club.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucluverse.newucluverseserver.common.BaseEntity;
import com.ucluverse.newucluverseserver.domain.club.ClubType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Club extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;
    private Long department_id;
    private Long college_id;
    private String name;
    private ClubType type;
    private String description;
    private String logoPath;
    @JsonManagedReference
    @OneToMany(mappedBy = "club")
    private List<MemberClub> memberClubs = new ArrayList<>();
    @OneToMany(mappedBy = "club")
    private List<ClubCategory> categories = new ArrayList<>();

}
