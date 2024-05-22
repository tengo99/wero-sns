package com.wero.finalProject.service.implement;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wero.finalProject.Repository.ImageRepository;
import com.wero.finalProject.Repository.UserRepository;
import com.wero.finalProject.domain.ImageEntity;
import com.wero.finalProject.domain.UserEntity;
import com.wero.finalProject.dto.request.user.UserDeleteRequestDto;
import com.wero.finalProject.dto.request.user.UserPostPictureRequestDto;
import com.wero.finalProject.dto.request.user.UserUpdateEmailRequestDto;
import com.wero.finalProject.dto.request.user.UserUpdateRequestDto;
import com.wero.finalProject.dto.response.ResponseDto;
import com.wero.finalProject.dto.response.user.UserDeleteResponseDto;
import com.wero.finalProject.dto.response.user.UserUpdateResponseDto;
import com.wero.finalProject.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * @작성자:오현암
 * @작성날짜:2024/05/03
 * @파일명:UserServiceImpl.class
 * @기능:유저_수정_서비스_로직_상세구현
 **/

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super UserUpdateResponseDto> userUpdate(UserUpdateRequestDto dto, String userId) {
        try {
            UserEntity user = userRepository.findByUserId(userId);

            String nickName = dto.getNickName();
            String email = dto.getEmail();
            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            boolean isSameNickName = nickName.equals(user.getNickName());
            boolean isSameEmail = email.equals(user.getEmail());

            boolean isExistedNickName = !isSameNickName && userRepository.existsByNickName(nickName);
            boolean isExistedEmail = !isSameEmail && userRepository.existsByEmail(email);

            if (user == null)
                return UserUpdateResponseDto.notExistUser();
            if(isExistedEmail)
                return UserUpdateResponseDto.duplicateEmail();
            if (isExistedNickName)
                return UserUpdateResponseDto.duplicateNickName();

            user.patchUserEntity(dto, userId);
            userRepository.save(user);
            return UserUpdateResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
    }

    @Override
    public ResponseEntity<? super UserUpdateResponseDto> userUpdateEmail(UserUpdateEmailRequestDto dto, String userId) {
        try {
            UserEntity user = userRepository.findByUserId(userId);
            if (user == null)
                return UserUpdateResponseDto.notExistUser();

            String email = dto.getEmail();
            boolean isExistEmail = userRepository.existsByEmail(email);
            if(isExistEmail) return  UserUpdateResponseDto.duplicateEmail();
            dto.setEmail(email);

            user.patchUserEmail(dto, userId);

            userRepository.save(user);

            return UserUpdateResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
    }

    @Override
    public ResponseEntity<? super UserUpdateResponseDto> userPicture(UserPostPictureRequestDto dto, String userId) {
        try {
            UserEntity user = userRepository.findByUserId(userId);
            if (user == null)
                return UserUpdateResponseDto.notExistUser();

            List<MultipartFile> profImage = dto.getImage();
            List<ImageEntity> profilePic = new ArrayList<>();

            for (MultipartFile image : profImage) {

                // 파일이름 인코딩
                String encodedFileName = URLEncoder.encode(image.getOriginalFilename(), StandardCharsets.UTF_8);

                // 파일이름 디코딩
                String decodedFileName = URLDecoder.decode(encodedFileName, StandardCharsets.UTF_8.toString());

                // 파일 저장 경로 설정
                Path targetLocation = Paths.get(System.getProperty("user.dir"), "uploads", decodedFileName);

                // uploads 디렉토리가 존재하지 않는 경우 생성
                if (!Files.exists(targetLocation.getParent())) {
                    Files.createDirectories(targetLocation.getParent());
                }

                // 루트디렉토리 uploads폴더에 파일 저장
                Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                ImageEntity imageEntity = new ImageEntity(user, encodedFileName);
                profilePic.add(imageEntity);
            }

            imageRepository.saveAll(profilePic);
            return UserUpdateResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }

    }

    @Override
    public ResponseEntity<? super UserDeleteResponseDto> userDelete(UserDeleteRequestDto dto, String userId) {
        try {
            if (!dto.getUserId().equals(userId)) {
                return UserDeleteResponseDto.notAuthorized();
            }

            UserEntity user = userRepository.findByUserId(userId);
            if (user == null) return UserDeleteResponseDto.notExistUser();

            List<ImageEntity> userImages = imageRepository.findByUserId(user);
            imageRepository.deleteAll(userImages);
            userRepository.delete(user);

            return UserDeleteResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
    }

    @Override
    public UserEntity findUserById(String userId) {
        UserEntity user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

}
