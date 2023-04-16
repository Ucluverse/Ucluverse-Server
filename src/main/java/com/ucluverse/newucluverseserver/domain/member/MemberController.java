package com.ucluverse.newucluverseserver.domain.member;

import com.ucluverse.newucluverseserver.domain.member.dto.MemberLoginRequest;
import com.ucluverse.newucluverseserver.domain.member.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<Member> signUp (@RequestBody MemberSignUpRequest dto) {
        return ResponseEntity.ok(memberService.signUp(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody MemberLoginRequest dto) {
        return ResponseEntity.ok().header("Authentication", memberService.login(dto)).build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test (@AuthenticationPrincipal Member member) {
        System.out.println(member);
        return ResponseEntity.ok("good");
    }
}
