package com.ucluverse.newucluverseserver.domain.department;

import jakarta.persistence.*;
import jdk.jfr.Name;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="college")
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "college", fetch = FetchType.LAZY)
    private List<Department> departments = new ArrayList<>();
}
