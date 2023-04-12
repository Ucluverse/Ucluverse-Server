package com.ucluverse.newucluverseserver.domain.member;

import com.ucluverse.newucluverseserver.domain.member.dto.MemberLoginRequest;
import com.ucluverse.newucluverseserver.domain.member.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> test () {
        return ResponseEntity.ok("good");
    }
}
