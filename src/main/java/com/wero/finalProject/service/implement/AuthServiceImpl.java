package com.wero.finalProject.service.implement;

import com.wero.finalProject.dto.request.auth.*;
import com.wero.finalProject.dto.response.auth.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wero.finalProject.Repository.CertificationRepository;
import com.wero.finalProject.Repository.UserRepository;
import com.wero.finalProject.common.CertificationNumber;
import com.wero.finalProject.domain.Certification;
import com.wero.finalProject.domain.UserEntity;
import com.wero.finalProject.dto.response.ResponseDto;
import com.wero.finalProject.provider.EmailProvider;
import com.wero.finalProject.provider.JwtProvider;
import com.wero.finalProject.service.AuthService;

import lombok.RequiredArgsConstructor;

/**
 * @작성자:오현암
 * @작성날짜:2024/04/25
 * @파일명:AuthServiceImpl
 * @기능:AuthService상세구현
 **/
@Service("AuthServiceImpl")
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailProvider emailProvider;
    private final CertificationRepository certificationRepository;
    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 아이디 중복 확인
    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try {
            String userId = dto.getId();
            boolean isExistId = userRepository.existsById(userId);
            if (isExistId)
                return IdCheckResponseDto.duplicateId();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
        return IdCheckResponseDto.success();
    }

    // 이메일 중복 확인

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            String userId = dto.getId();
            String email = dto.getEmail();

            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId)
                return EmailCertificationResponseDto.duplicatedId();

            boolean isExistedEmail = userRepository.existsByEmail(email);
            if (isExistedEmail)
                return EmailCertificationResponseDto.duplicatedEmail();

            String certificationNumber = CertificationNumber.getCertificationNumber();
            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed)
                return EmailCertificationResponseDto.mailSendFail();

            Certification certification = new Certification(userId, email, certificationNumber);
            certificationRepository.save(certification);

        } catch (Exception e) {
            e.printStackTrace();
            ;
            return ResponseDto.dataBaseError();
        }
        return EmailCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try {
            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            Certification certification = certificationRepository.findByUserId(userId);

            if (certification == null)
                return CheckCertificationResponseDto.certificationFail();
            boolean isMatched = certification.getEmail().equals(email)
                    && certification.getCertificationNumber().equals(certificationNumber);
            if (!isMatched)
                return CheckCertificationResponseDto.certificationFail();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super RegisterResponseDto> register(RegisterRequestDto dto) {
        try {

            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId)
                return RegisterResponseDto.duplicatedId();

            String email = dto.getEmail();
            boolean isExistedEmail = userRepository.existsByEmail(email);
            if (isExistedEmail)
                return EmailCertificationResponseDto.duplicatedEmail();

            String nickName = dto.getNickName();
            boolean isExistedNickName = userRepository.existsByNickName(nickName);
            if (isExistedNickName)
                return RegisterResponseDto.existNickName();

            String certificationNumber = dto.getCertificationNumber();
            Certification certification = certificationRepository.findByUserId(userId);
            boolean isMatched = certification.getEmail().equals(email)
                    && certification.getCertificationNumber().equals(certificationNumber);
            if (!isMatched)
                return RegisterResponseDto.certificationFail();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            certificationRepository.deleteByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
        return RegisterResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;
        String userId = dto.getId();
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null)
               return SignInResponseDto.signInFail();

            boolean restriction = userEntity.isRestriction();
            if(restriction)
                return SignInResponseDto.restrictedUser();


            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched)
                return SignInResponseDto.signInFail();

            token = jwtProvider.create(userId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
        return SignInResponseDto.success(token, userId);
    }

}
