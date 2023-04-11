package com.ucluverse.newucluverseserver.domain.member;
import com.ucluverse.newucluverseserver.domain.auth.TokenProvider;
import com.ucluverse.newucluverseserver.domain.department.Department;
import com.ucluverse.newucluverseserver.domain.department.DepartmentRepository;
import com.ucluverse.newucluverseserver.domain.member.dto.MemberLoginRequest;
import com.ucluverse.newucluverseserver.domain.member.dto.MemberSignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final DepartmentRepository departmentRepository;
    private final TokenProvider tokenProvider;

    public Long signUp(MemberSignInRequest dto ){
        memberRepository.findByEmail(dto.getEmail()).ifPresent(a -> {
            throw new IllegalArgumentException("이미 가입된 email 입니다.");
        });
        List<String> roles = new ArrayList<>();
        roles.add(Role.USER.getKey());
        Department department = departmentRepository.findOneByName(dto.getDepartment()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학과입니다."));
        Member member = Member.builder()
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .nickname(dto.getNickName())
                .contactNumber(dto.getContactNumber())
                .roles(roles)
                .department(department)
                .build();
        return memberRepository.save(member).getId();
    }

    public String login(MemberLoginRequest dto ){
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        return tokenProvider.generateToken(member.getEmail(), member.getRoles());
    }

}
