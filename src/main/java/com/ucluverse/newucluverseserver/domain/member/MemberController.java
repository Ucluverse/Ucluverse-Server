package com.ucluverse.newucluverseserver.domain.member;

import com.ucluverse.newucluverseserver.domain.member.dto.MemberLoginRequest;
import com.ucluverse.newucluverseserver.domain.member.dto.MemberSignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp (@RequestBody MemberSignInRequest dto) {
        return ResponseEntity.ok(memberService.signUp(dto).toString());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody MemberLoginRequest dto) {
        return ResponseEntity.ok(memberService.login(dto));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test () {
        return ResponseEntity.ok("good");
    }
}
