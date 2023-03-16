package com.ucluverse.newucluverseserver.domain.auth;

import com.ucluverse.newucluverseserver.domain.auth.dto.GetSocialOAuthRes;
import com.ucluverse.newucluverseserver.domain.member.Member;
import com.ucluverse.newucluverseserver.domain.member.MemberRepository;
import com.ucluverse.newucluverseserver.domain.member.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final TokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        String email = (String) oAuth2User.getAttributes().get("email");
        Optional<Member> member = memberRepository.findByEmail(email);
        String jwtToken;
        jwtToken = jwtTokenProvider.createJWT(email, Role.GUEST.getKey());
        if(member.isPresent()){
            jwtToken=jwtTokenProvider.createJWT(member.get().getEmail() , member.get().getRole().getKey());
        }
        response.setHeader("access", jwtToken);
        System.out.println(jwtToken);
    }

}
