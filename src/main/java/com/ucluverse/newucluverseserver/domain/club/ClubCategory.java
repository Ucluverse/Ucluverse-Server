package com.ucluverse.newucluverseserver.domain.club;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ClubCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "club_id")
    private Club club;
}
