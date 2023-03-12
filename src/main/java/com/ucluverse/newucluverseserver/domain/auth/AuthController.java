package com.ucluverse.newucluverseserver.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final OauthService oauthService;

    @GetMapping("/google")
    public void googleLoginRedirect() throws IOException {
        oauthService.requestGoogle();
    }

    @GetMapping("/google/callback")
    public void callback (
            @RequestParam(name = "code") String code)throws IOException {
        oauthService.googleLogin(code);
    }

}
