package com.wero.finalProject.domain;

import java.util.HashSet;
import java.util.Set;

import com.wero.finalProject.dto.request.auth.RegisterRequestDto;
import com.wero.finalProject.dto.request.user.UserUpdateEmailRequestDto;
import com.wero.finalProject.dto.request.user.UserUpdateRequestDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @작성자:오현암
 * @작성날짜:2024/04/25
 * @파일명:User.class
 * @기능:User엔티티
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "userId", updatable = false)
    private String userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password", updatable = true)
    private String password;

    @Column(name = "type", updatable = false)
    private String type;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "gender", updatable = true)
    private String gender;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MainFeedEntity> mainFeeds = new HashSet<>();

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LikeEntity> likes = new HashSet<>();

    // TODO: Enum화 시키기
    @Column(name = "role", updatable = false)
    private String role;

    @Column(name = "restriction", updatable = true)
    private boolean restriction;

    public UserEntity(RegisterRequestDto dto) {
        this.userId = dto.getId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.gender = dto.getGender();
        this.nickName = dto.getNickName();
        this.type = "app";
        this.role = "ROLE_USER";
        this.restriction = false;
    }

    public UserEntity(String userId, String nickName, String email, String type) {
        this.userId = userId;
        this.nickName = nickName;
        this.password = "password";
        this.email = email;
        this.type = type;
        this.role = "ROLE_USER";
        this.restriction = false;
    }

    public void patchUserEntity(UserUpdateRequestDto dto, String userId) {
        this.userId = userId;
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.gender = dto.getGender();
        this.nickName = dto.getNickName();
    }

    public void patchUserEmail(UserUpdateEmailRequestDto dto, String userId) {
        this.userId = userId;
        this.email = dto.getEmail();
    }

    public void suspensionUserEntity(Boolean isRestriction) {
        this.restriction = isRestriction;
    }

    public UserSuspensionDto toSuspensionDto() {
        return new UserSuspensionDto(this.userId, this.email, this.restriction);
    }

    public static class UserSuspensionDto {
        private String userId;
        private String email;
        private boolean restriction;

        public UserSuspensionDto(String userId, String email, boolean restriction) {
            this.userId = userId;
            this.email = email;
            this.restriction = restriction;
        }

        public String getUserId() {
            return userId;
        }

        public String getEmail() {
            return email;
        }

        public boolean isRestriction() {
            return restriction;
        }
    }

}
