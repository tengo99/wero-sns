package com.wero.finalProject.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.wero.finalProject.domain.CustomOAuth2User;
import com.wero.finalProject.provider.JwtProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * @작성자:오현암
 * @작성날짜:2024/04/25
 * @파일명:OAuth2SuccessHandler.class
 * @기능:OAuth2_인증_성공_처리
 **/
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();

        String userId = oauth2User.getName();
        String token = jwtProvider.create(userId);
        System.out.println("유저아이디: " + userId);

        response.sendRedirect("https://werosns.life/auth/oauth2-response/" +token+"/3600");

    }

}
