package com.ucluverse.newucluverseserver.domain.member;
import com.ucluverse.newucluverseserver.domain.auth.TokenProvider;
import com.ucluverse.newucluverseserver.domain.department.Department;
import com.ucluverse.newucluverseserver.domain.department.DepartmentRepository;
import com.ucluverse.newucluverseserver.domain.member.dto.MemberLoginRequest;
import com.ucluverse.newucluverseserver.domain.member.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 가입된 이메일입니다.");
        });
        List<String> roles = new ArrayList<>();
        roles.add(Role.USER.getKey());
        Department department = departmentRepository.findOneByName(dto.getDepartment()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "학과 정보가 존재하지 않습니다."));
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아주대 이메일이 아닙니다.");
        }
        Member member = memberRepository.findOneByEmail(dto.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "가입되지 않은 이메일입니다."));
        return tokenProvider.generateToken(member.getEmail(), member.getRoles());
    }

}
