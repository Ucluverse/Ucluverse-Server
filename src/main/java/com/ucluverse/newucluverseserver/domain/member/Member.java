package com.ucluverse.newucluverseserver.domain.member;

import com.ucluverse.newucluverseserver.domain.department.Department;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String nickname;
    private String contactNumber;
    private String email;
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

}
