# We-Ro-SNS
<br><br>


WE-RO
=============
각박한 현대사회를 살아가는 이들을 위한 감성 SNS입니다<br>
Rest API형 서버로써 프론트단은 리액트로 제작하였고 백엔드단은 스프링부트로 제작하였습니다.<br>

## 프로젝트의 전체적인 구조
![Sitemap Whiteboard in Green Purple Basic Style (2)](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/3f1777d7-7ab3-4ebb-99bf-31f36b172a0e)
- GitHub Action을 사용하여 CI/CD를 진행합니다.
- 모든 서버는 Amazon Web Services에 올라가 있습니다.<br>
- 구동중인 서버 : Main We-Ro Server, React Server, MySQL Server


## 프로젝트의 주요 관심사
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>공통사항</b><br>
- 지속적인 성능 개선
- 지저분한 코드에 대한 리팩토링
<br><br>
<b>코드 컨벤션</b><br>
- Google code Style을 준수
- 노션에 코드 컨벤션을 명시하여 팀원 모두 규칙을 엄수하도록 하였습니다.
- 링크 <https://www.notion.so/cf5968376cfc4478a3768e4d8a3a96d1>
<br><br>
<b>성능 최적화</b>
- GitHub Action을 적용하여 테스트 자동화
- 협업하는 동료의 소스코드에 서로 공유하여 서로의 소스코드를 알 수 있도록 하고 있습니다.
<br><br>

### 성능 테스트
Postman을 활용하여 테스트하고있습니다.<br>


## 사용 기술 및 환경
Spring boot, Gradle, JPA, React, Docker, MySQL, Jenkins, Java17, Amazon Web Services
<br>

## Notion
<https://www.notion.so/ec4700e35f214a2aa95e8ecf51b506ee><br>
Notion에 프로젝트에 사용된 기술, erd, api 명세등의 정보가 포함되어있습니다.<br>
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

### S/W 화면 실사 1
![리드미 - 화면 실사 (1)](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/b0c5b327-4567-44fa-8e0a-47fddf03131a)

### S/W 화면 실사 2
![리드미 - 회면실사2](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/a6d343da-bbcf-47c0-b386-5a0122641d9a)

## 프로젝트 DB ERD
2024-06-03 수정
![스크린샷 2024-06-03 153847](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/47d53569-b0ac-44de-b26f-d2a5804a9a54)




