package com.ucluverse.newucluverseserver.domain.auth;

import com.ucluverse.newucluverseserver.domain.auth.dto.GetSocialOAuthRes;
import com.ucluverse.newucluverseserver.domain.auth.dto.GoogleOAuthToken;
import com.ucluverse.newucluverseserver.domain.auth.dto.GoogleUser;
import com.ucluverse.newucluverseserver.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final GoogleOauth googleOauth;
    private final HttpServletResponse response;
    private final MemberRepository memberRepository;

    public void requestGoogle() throws IOException {
        response.sendRedirect(googleOauth.getOauthRedirectURL());
    }

    public GetSocialOAuthRes googleLogin(String code) throws IOException {
        ResponseEntity<String> accessTokenResponse= googleOauth.requestAccessToken(code);
        //응답 객체가 JSON형식으로 되어 있으므로, 이를 deserialization해서 자바 객체에 담음
        GoogleOAuthToken oAuthToken=googleOauth.getAccessToken(accessTokenResponse);

        //액세스 토큰을 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체를 받음
        ResponseEntity<String> userInfoResponse=googleOauth.requestUserInfo(oAuthToken);

        //다시 JSON 형식의 응답 객체를 자바 객체로 역직렬화
        GoogleUser googleUser= googleOauth.getUserInfo(userInfoResponse);
        String user_email=googleUser.getEmail();

        //우리 서버의 db와 대조하여 해당 user가 존재하는 지 확인
        memberRepository.findByEmail(user_email)
                .ifPresent(user -> {
                    String jwtToken=jwtService.createJwt(user.getEmail() ,user.getId());
                    GetSocialOAuthRes getGoogleAuthRes= GetSocialOAuthRes.builder()
                            .jwtToken(jwtToken)
                            .member_idx(user.getId().intValue())
                            .googleAccessToken(oAuthToken.getAccess_token())
                            .build();
                    return getGoogleAuthRes;
                });
        return null;
    }
}
