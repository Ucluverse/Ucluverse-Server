package com.ucluverse.newucluverseserver.domain.member;
import com.ucluverse.newucluverseserver.common.CustomResponseStatusException;
import com.ucluverse.newucluverseserver.common.ErrorCode;
import com.ucluverse.newucluverseserver.domain.auth.TokenProvider;
import com.ucluverse.newucluverseserver.domain.department.Department;
import com.ucluverse.newucluverseserver.domain.department.DepartmentRepository;
import com.ucluverse.newucluverseserver.domain.member.dto.MemberLoginRequest;
import com.ucluverse.newucluverseserver.domain.member.dto.MemberSignUpRequest;
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

    public Member signUp(MemberSignUpRequest dto ){
        memberRepository.findOneByEmail(dto.getEmail()).ifPresent(a -> {
            throw new CustomResponseStatusException(ErrorCode.ALREADY_SIGNED_UP);
        });
        List<String> roles = new ArrayList<>();
        roles.add(Role.USER.getKey());
        Department department = departmentRepository.findOneByName(dto.getDepartment())
                .orElseThrow(() -> new CustomResponseStatusException(ErrorCode.DEPARTMENT_NOT_FOUND));
        Member member = Member.builder()
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .nickname(dto.getNickName())
                .contactNumber(dto.getContactNumber())
                .roles(roles)
                .department(department)
                .build();
        return memberRepository.save(member);
    }

    public String login(MemberLoginRequest dto ){
        if(!dto.getEmail().split("@")[1].equals("ajou.ac.kr")){
            throw new CustomResponseStatusException(ErrorCode.INVALID_EMAIL_DOMAIN);
        }
        Member member = memberRepository.findOneByEmail(dto.getEmail())
                .orElseThrow(() -> new CustomResponseStatusException(ErrorCode.MEMBER_NOT_FOUND));
        return tokenProvider.generateToken(member.getEmail(), member.getRoles());
    }

}
