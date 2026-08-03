# We-Ro-SNS

## WE-RO

현대사회를 살아가는 사람들을 위한 **감정 공유형 SNS**입니다.

사용자가 자신의 감정과 고민을 기록하고, 다른 사용자들과 공감과 의견을 나눌 수 있도록 설계했습니다.  
프론트엔드는 React, 백엔드는 Spring Boot를 사용했으며, REST API 기반으로 서비스를 구현했습니다.

---

## Project Overview

- **프로젝트명**: WE-RO
- **프로젝트 형태**: 팀 프로젝트
- **주제**: 감정 공유형 SNS
- **아키텍처**: REST API 기반 웹 서비스
- **Frontend**: React
- **Backend**: Spring Boot
- **Database**: MySQL
- **배포 환경**: Amazon Web Services

---

## Service Goal

- 사용자가 자신의 감정과 고민을 자유롭게 기록할 수 있는 공간 제공
- 다른 사용자와 공감과 의견을 나눌 수 있는 커뮤니티 구현
- 단순 게시판을 넘어 감정 중심의 사용자 경험 설계
- 게시글 작성, 공감, 댓글로 이어지는 자연스러운 서비스 흐름 구현
- 안정적으로 운영할 수 있는 백엔드 구조와 배포 환경 구축

---

## Project Architecture

![Sitemap Whiteboard in Green Purple Basic Style (2)](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/3f1777d7-7ab3-4ebb-99bf-31f36b172a0e)

- React 기반 프론트엔드 서버
- Spring Boot 기반 메인 API 서버
- MySQL 데이터베이스 서버
- AWS 기반 서비스 배포
- GitHub Actions 기반 CI 구성
- Docker 기반 애플리케이션 배포

> 프론트엔드와 백엔드를 분리하고 REST API를 통해 통신하도록 구성했습니다.

---

## Key Features

### User

- 회원가입
- 로그인
- 사용자 프로필 관리
- 사용자 정보 조회 및 수정

### Post

- 게시글 작성
- 게시글 조회
- 게시글 수정
- 게시글 삭제
- 감정 및 고민 중심의 콘텐츠 작성

### Like

- 게시글 공감 기능
- 사용자별 공감 여부 관리
- 중복 공감 방지

### Bookmark

- 게시글 북마크
- 사용자별 저장 게시글 관리

### Comment

- 게시글 댓글 작성
- 댓글 조회
- 댓글 수정 및 삭제
- 사용자 간 의견 공유

---

## Main Development Focus

### 공통 개발 방향

- 지속적인 성능 개선
- 가독성과 유지보수성을 고려한 리팩터링
- 계층별 책임 분리
- 협업을 고려한 코드 구조 설계
- REST API 명세를 기반으로 한 프론트엔드 연동

### Code Convention

- Google Java Style Guide 기반 코드 작성
- Notion에 코드 규칙 문서화
- 팀원 간 통일된 네이밍과 코드 형식 적용
- Pull Request 기반 코드 리뷰 진행

### Performance and Quality

- GitHub Actions를 활용한 빌드 및 테스트 자동화
- Postman 기반 API 동작 테스트
- 팀 내 코드 리뷰를 통한 구현 내용 공유
- 반복되는 로직 리팩터링
- API 요청 및 응답 구조 점검

---

## Tech Stack

### Backend

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Gradle
- REST API

### Frontend

- React

### Database / Cache

- MySQL
- Redis

### Infrastructure

- Amazon Web Services
- Docker
- Jenkins
- GitHub Actions
- Docker Hub

### Testing / Collaboration

- Postman
- GitHub
- Notion
- Kakao Oven

---

## My Role

저는 WE-RO 프로젝트에서 **서비스 기획과 백엔드 개발**을 담당했습니다.

### Service Planning

- 사용자 페르소나 정의
- 서비스 이용 시나리오 설계
- 사용자가 게시글을 작성하고 공감과 댓글을 통해 소통하는 흐름 설계
- 감정 공유형 SNS의 주요 기능과 사용자 경험 기획
- 팀원들과 기능 우선순위 및 서비스 방향 논의

### Backend Development

- Spring Boot 기반 REST API 구현
- Controller, Service, Repository 계층 구성
- JPA 기반 데이터 접근 로직 구현
- 사용자, 게시글, 좋아요, 북마크 관련 기능 개발
- 요청 및 응답 DTO 설계
- 엔티티 간 연관관계 구성
- 예외 처리와 데이터 검증 로직 적용

### Database Design

- User, Post, Like, Bookmark 중심의 ERD 설계
- 사용자와 게시글 간 관계 설계
- 사용자별 좋아요 및 북마크 데이터 구조 설계
- 중복 데이터 방지를 고려한 데이터 모델 구성

### Collaboration

- 프론트엔드 개발자와 API 명세 협의
- 팀원에게 JPA와 REST API 구조 공유
- Pull Request 기반 코드 리뷰 참여
- Notion을 활용한 API와 기술 문서 작성
- GitHub를 활용한 버전 관리와 협업

---

## Technical Challenges

### Spring Boot와 JPA 학습 및 적용

프로젝트 당시 Spring Boot와 JPA를 처음 본격적으로 사용했기 때문에,  
새로운 기술을 학습하면서 동시에 실제 기능을 구현해야 했습니다.

단순히 예제를 따라 구현하는 데 그치지 않고 다음 내용을 직접 확인하며 개발했습니다.

- 엔티티 생명주기
- 연관관계 매핑
- 지연 로딩
- Repository 기반 데이터 접근
- 트랜잭션 처리
- DTO와 엔티티의 역할 분리

이를 통해 기능 구현과 학습을 병행하는 경험을 했으며,  
새로운 기술을 빠르게 습득해 프로젝트에 적용하는 역량을 키울 수 있었습니다.

### 엔티티 연관관계 설계

사용자, 게시글, 좋아요, 북마크가 서로 연결되는 구조이기 때문에  
엔티티 간 연관관계를 잘못 구성하면 불필요한 조회나 순환 참조 문제가 발생할 수 있었습니다.

이를 해결하기 위해 다음 사항을 고려했습니다.

- 엔티티 간 책임 분리
- 단방향과 양방향 관계 선택
- DTO를 통한 응답 구조 분리
- 사용자별 좋아요 및 북마크 중복 방지
- 필요한 데이터만 조회하는 API 설계

### 프론트엔드와 백엔드 간 협업

프론트엔드와 백엔드가 분리된 구조에서는 API 명세가 변경되면  
양쪽 개발 일정에 모두 영향을 줄 수 있었습니다.

이를 줄이기 위해 요청과 응답 형식을 미리 정의하고,  
변경 사항을 Notion과 GitHub를 통해 공유했습니다.

---

## CI

- GitHub Actions 기반 빌드 및 테스트 자동화
- Pull Request 생성 시 자동 Build 및 Test 수행
- 코드 병합 전 오류 확인
- 비로그인 상태에서도 CI 결과 확인 가능
- Jenkins는 초기 배포 과정에서 사용했으며 현재는 운영 종료

---

## CD

- Docker 이미지 기반 배포
- CI 서버에서 빌드 완료 후 Docker 이미지 생성
- Shell Script를 활용해 Docker Hub에 이미지 업로드
- 배포 서버에서 Docker Hub 이미지를 내려받아 실행
- AWS 환경에서 애플리케이션 서버 운영

---

## Database

### MySQL

- 서비스의 주요 데이터 저장
- 사용자, 게시글, 댓글, 좋아요, 북마크 데이터 관리
- cafe24 Web Hosting 환경 활용

### Redis

- Docker 컨테이너 기반 운영
- 캐시 및 일부 상태 데이터 관리

---

## API Testing

Postman을 활용해 다음 항목을 테스트했습니다.

- API 요청 및 응답
- 정상 및 예외 상황
- 사용자 인증 여부
- 게시글 CRUD
- 좋아요와 북마크 처리
- 잘못된 요청 데이터
- 서버 응답 시간 및 기본 성능

---

## Screen Design

Kakao Oven을 활용해 화면 흐름과 UI 구조를 설계했습니다.

https://ovenapp.io/view/SaTiTCEQyNfk5FdeOq1lDkJuGsNogCVE/

### 화면 예시 1

![리드미 - 화면 예시 1](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/b0c5b327-4567-44fa-8e0a-47fddf03131a)

### 화면 예시 2

![리드미 - 화면 예시 2](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/a6d343da-bbcf-47c0-b386-5a0122641d9a)

---

## ERD

2024년 6월 3일 기준 ERD입니다.

![ERD](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/47d53569-b0ac-44de-b26f-d2a5804a9a54)

---

## Documentation

프로젝트에서 사용한 기술, ERD, API 명세와 협업 규칙을 Notion에 정리했습니다.

- **프로젝트 문서**  
  https://www.notion.so/ec4700e35f214a2aa95e8ecf51b506ee

- **코드 규칙**  
  https://www.notion.so/cf5968376cfc4478a3768e4d8a3a96d1

---

## What I Learned

- Spring Boot 기반 웹 애플리케이션 개발
- JPA와 Hibernate를 활용한 데이터 처리
- REST API 설계 및 프론트엔드 연동
- 엔티티 연관관계와 ERD 설계
- GitHub 기반 팀 협업
- Pull Request와 코드 리뷰
- CI/CD 자동화 경험
- Docker 기반 배포
- AWS 환경에서의 서비스 운영
- 기능 구현뿐 아니라 사용자 흐름을 고려한 서비스 기획
- 새로운 기술을 학습하면서 실제 프로젝트에 적용하는 방법

---

## Future Work

- Spring Security와 JWT 기반 인증 구조 고도화
- 테스트 코드 확대
- API 문서 자동화
- 게시글 검색 기능 개선
- 알림 기능 추가
- 이미지 업로드 기능 개선
- Redis 캐시 적용 범위 확대
- 쿼리 성능 최적화
- Docker Compose 기반 통합 실행 환경 구성
- 모니터링 및 로그 수집 환경 구축

---

## Repository

https://github.com/Path-Finder-Org/We-Ro-SNS

---

## Contact

- **Email**: [longvaca0213@gmail.com](mailto:longvaca0213@gmail.com)
- **GitHub**: https://github.com/tengo99

---

> 단순한 SNS 기능 구현을 넘어,  
> 사용자의 감정과 행동 흐름을 고려한 서비스를 기획하고  
> Spring Boot와 JPA를 활용해 실제로 동작하는 백엔드를 구현했습니다.
