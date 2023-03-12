package com.ucluverse.newucluverseserver.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public String join(String email, String password){
        memberRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new RuntimeException("회원이 이미 존재합니다");
                });

        return "회원가입 완료";
    }
}
