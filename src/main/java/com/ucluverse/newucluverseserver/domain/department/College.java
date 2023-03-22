package com.ucluverse.newucluverseserver.domain.department;

import com.ucluverse.newucluverseserver.common.BaseEntity;
import jakarta.persistence.*;
import jdk.jfr.Name;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="college")
public class College extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "college", fetch = FetchType.LAZY)
    private List<Department> departments = new ArrayList<>();
}
