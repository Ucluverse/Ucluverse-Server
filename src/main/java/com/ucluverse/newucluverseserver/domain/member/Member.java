package com.ucluverse.newucluverseserver.domain.member;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucluverse.newucluverseserver.common.BaseEntity;
import com.ucluverse.newucluverseserver.domain.club.entity.MemberClub;
import com.ucluverse.newucluverseserver.domain.department.Department;
import com.ucluverse.newucluverseserver.domain.posting.Comment;
import com.ucluverse.newucluverseserver.domain.posting.Star;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String userName;
    private String nickname;
    private String contactNumber;
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;
    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<MemberClub> memberClubs = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<Star> likes = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return "1111";
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
