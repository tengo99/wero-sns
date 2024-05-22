package com.wero.finalProject.service.implement;

import com.wero.finalProject.domain.CustomOAuth2User;
import com.wero.finalProject.Repository.UserRepository;
import com.wero.finalProject.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @작성자:오현암
 * @작성날짜:2024/04/25
 * @파일명:OAuth2UserServiceImpl
 * @기능:유저_OAuth인증_서비스_로직
 **/
@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);
        String oauth2ClientName = request.getClientRegistration().getClientName();
//        try{
//            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        UserEntity userEntity = null;
        String userId = null;
        String nickName = null;
        String email  = "email@email.com";

        if(oauth2ClientName.equals("kakao")){
            userId = "kakao_"+oAuth2User.getAttributes().get("id");
            nickName =  "kakao_"+oAuth2User.getAttributes().get("id");
            userEntity = new UserEntity(userId, nickName, "email@email.com", "kakao");
        }

        if(oauth2ClientName.equals("naver")){
            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
            userId = "naver_"+responseMap.get("id").substring(0, 14);
            nickName = "naver_"+responseMap.get("id").substring(0, 14);
            email = responseMap.get("email");
            userEntity = new UserEntity(userId, nickName, email,  "naver");
        }

//        if(oauth2ClientName.equals("google")){
//            userId = "google_"+oAuth2User.getAttributes().get("id");
//            userEntity = new UserEntity(userId, "email@email.com", "google");
//        }
        userRepository.save(userEntity);

//        if(oauth2ClientName.equals("google")){
//
//        }

        return new CustomOAuth2User(userId);
    }
}

