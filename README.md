# Wanted pre onboarding backend
## Description
원티드 프리온보딩 코스 백엔드 과제입니다.
### 과제 설명
채용 사이트의 REST API를 구성하는 것이 목표이며, Restdocs와 Hateoas를 통해 Restful API 3단계를 충족하는 API를 만드는 것을 목표로 함.
채용 사이트의 주요 기능은 다음과 같습니다.

#### 요구 사항
**회사**

- 회사는 채용공고를 생성할 수 있음
  - 채용공고는 회사ID, 포지션, 보상금, 내용, 사용기술을 포함함
- 채용 공고를 수정할 수 있음
  - 회사 ID를 제외하고 모두 수정이 가능하다
- 채용 공고를 삭제할 수 있음

**사용자**
- 채용공고의 목록을 가져올 수 있음
- 회사명, 사용기술을 통해 채용공고의 검색할 수 있음
- 채용 상세 페이지를 가져올 수 있음
  - 채용 내용이 추가적으로 담긴다
  - 해당 회사가 올린 다른 채용공고가 추가적으로 포함된다.
- 채용공고에 지원할 수 있음
  - 사용자는 1회만 지원이 가능하다
  - 채용공고 id, 사용자 id가 들어간다

## Statcks
### Enviroment
![img](https://img.shields.io/badge/intellij_idea-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![img](https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white)
![img](https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white)
### Development
![img](https://img.shields.io/badge/java-17-007396?style=for-the-badge&logo=java&logoColor=white)
![img](https://img.shields.io/badge/springboot-3.1.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![img](https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

![img](https://img.shields.io/badge/jpa-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![img](https://img.shields.io/badge/querydsl-59666C?style=for-the-badge&logo=querydsl&logoColor=white)
![img](https://img.shields.io/badge/spring_hateoas-6DB33F?style=for-the-badge&logo=querydsl&logoColor=white)
![img](https://img.shields.io/badge/spring_restdocs-6DB33F?style=for-the-badge&logo=querydsl&logoColor=white)

![img](https://img.shields.io/badge/h2_db-59666C?style=for-the-badge&logo=querydsl&logoColor=white)

![img](https://img.shields.io/badge/JUnit5-junit5?style=for-the-badge&logo=junit5&logoColor=white)
## Getting Started
```shell
./gradlew build
cd build/libs
java -jar wanted-preonboarding-0.0.1-SNAPSHOT.jar
```

## Test
```shell
./gradlew test
```
![img.png](src/main/resources/static/img/test_result.png)
### Restful API
해당 API는 Self-descriptive Message와 HATEOAS를 만족하는 RESTful API 3단계를 만족한다.
프로젝트를 배포 후, http://localhost:8080/docs/index.html 로 접속하면 Restdocs를 통해 API 문서를 확인할 수 있다. 혹은,[여기](src/main/resources/static/docs/index.html)를 클릭하여 확인할 수 있다.