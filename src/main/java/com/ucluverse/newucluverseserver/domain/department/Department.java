package com.ucluverse.newucluverseserver.domain.department;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "college_id")
    private College college;

}
