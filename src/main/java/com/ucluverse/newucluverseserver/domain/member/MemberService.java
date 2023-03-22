package com.ucluverse.newucluverseserver.domain.member;
import com.ucluverse.newucluverseserver.domain.auth.TokenProvider;
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
    private final TokenProvider tokenProvider;

    public Long signUp(MemberSignInRequest dto ){
        memberRepository.findByEmail(dto.getEmail()).ifPresent(a -> {
            throw new IllegalArgumentException("이미 가입된 email 입니다.");
        });
        List<String> roles = new ArrayList<>();
        roles.add(Role.USER.getKey());
        Member member = Member.builder()
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .nickname(dto.getNickName())
                .contactNumber(dto.getContactNumber())
                .roles(roles)
                .build();
        return memberRepository.save(member).getId();
    }

}
