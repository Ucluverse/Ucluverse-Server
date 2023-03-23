package com.ucluverse.newucluverseserver.domain.club;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

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

}
