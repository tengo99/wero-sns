# We-Ro-SNS
<br><br>


WE-RO
=============
각박한 현대사회를 살아가는 이들을 위한 감성 SNS입니다<br>
Rest API형 서버로써 프론트단은 리액트로 제작하였고 백엔드단은 스프링부트로 제작하였습니다.<br>

## 프로젝트의 전체적인 구조
![WE-RO_README파일 아키텍쳐 drawio](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/b081aa81-c543-4ce6-afc1-481363c45c56)
- github hook을 받아 Jenkins에서 CI/CD를 진행합니다.
- 모든 서버는 Amazon Web Services에 올라가 있습니다.<br>
- 구동중인 서버 : Main We-Ro Server, React Server, MySQL Server


## 프로젝트의 주요 관심사
<b>공통사항</b><br>
- 지속적인 성능 개선
- 지저분한 코드에 대한 리팩토링
<br><br>
<b>코드 컨벤션</b><br>
- Google code Style을 준수
- 노션에 코드 컨벤션을 명시하여 팀원 모두 규칙을 엄수하도록 하였습니다.
- 링크 [https://google.github.io/styleguide/javaguide.html](https://www.notion.so/cf5968376cfc4478a3768e4d8a3a96d1?p=3976ca47a3914d86a0309d0a3bc5110a&pm=c)
<br><br>
<b>성능 최적화</b><br>
- JPA트
- Jenkins CI를 적용하여 테스트 자동화
- 협업하는 동료의 소스코드에 서로 공유하여 서로의 소스코드를 알 수 있도록 하고 있습니다.
<br><br>

### 성능 테스트
Postman을 활용하여 테스트하고있습니다.<br>


## 사용 기술 및 환경
Spring boot, Gradle, JPA, React, Docker, MySQL, Jenkins, Java17, Amazon Web Services
<br>

## Wiki
<https://github.com/f-lab-edu/food-delivery/wiki><br>
Wiki에 기술 이슈에 대한 고민과 해결 방법을 포스팅한 개인 블로그의 url이 포함되어있습니다.<br>
<br>
## CI
Jenkins : 서버 운영을 종료하였습니다.<br>
Amazon Web Services를 사용하고 있습니다.<br>
PR시마다 자동 Build 및 Test 적용<br>
비로그인 상태로도 확인이 가능합니다.<br>

## CD
Docker 이미지를 제작하여 배포합니다.<br>
CI 서버에서 빌드 완료시 Shell script가 작동하여 빌드된 이미지가 docker hub에 저장됩니다.<br>
Push 완료시 Delfood 메인 서버에서 docker hub에 올라간 이미지를 받아 실행시킵니다.<br>


<br>
## Database
- MySQL<br>
cafe24 web hosting 서비스를 사용하고 있습니다.
- Redis<br>
docker 컨테이너를 사용하고 있습니다.
<br>

## 화면 설계
kakao oven - <https://ovenapp.io/view/SaTiTCEQyNfk5FdeOq1lDkJuGsNogCVE/>

### 고객 화면 프로토타입
![image](https://user-images.githubusercontent.com/46917538/66744438-6b694e00-eeb7-11e9-82b7-246f569a74a6.png)

### 사장님 화면 프로토타입
![화면정의서_사장님](https://user-images.githubusercontent.com/46917538/68458111-4f9e6100-0245-11ea-9118-0ca891eab044.png)


## 프로젝트 DB ERD
2024-05-21 수정
![image](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/0eb3d0ad-0439-40c2-b47f-f26d3e563f13)

