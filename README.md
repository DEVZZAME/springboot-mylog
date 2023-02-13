# 소셜 미디어 서비스(My Log)

## 프로젝트 개요

| Front-end | HTML5, CSS3, Javascript, JSP |
| --- | --- |
| Back-end | Spring Boot, Spring Data JPA (Hibernate), Spring Security(OAuth2.0, JWT) |
| Database | MySQL |
| Deployment | AWS EC2, AWS RDS, AWS Route53, CloudFlare |
| 개발 기간 | 2022.01.11 ~ 2022.03.02 (약 7주) |
| 참여 인원 | 1인 개발 |
| 담당 구현 파트 | - 프로젝트 개발환경 구축, 설계 참여
- 로그인, 회원가입, 소셜 로그인 구현
- 해더(홈, 태그 및 계정 검색, 돋보기, 마이페이지)
- 메인 피드 페이지(구독 대상 게시물 노출, 페이징)
- 마이 페이지(프로필 및 내정보 변경, 게시물 업로드, 로그아웃, 구독 관리)
- 돋보기 페이지(Likes 기준 노출, 상대 페이지 링크)
- GitHub 레포지토리 전체 관리 |

## 프로젝트 소개

- Facebook, Instagram 을 넘어 최근엔 Bondee 까지 개인의 아이덴티티를 가장 잘 나타낼 수 있는 수단으로 SNS가 자리 잡은 지는 많은 시간이 흘렀습니다. 그동안 제가 가장 많이 이용해왔던 Instagram을 모티브로 해당 서비스가 가지고 있는 기능들을 구현하며 Spring boot 의 많은 기능들을 리뷰할 수 있는 프로젝트였습니다.
- 가장 기본적인 SNS 플랫폼의 기능을 포함하고 있는 프로젝트이지만, **이전 프로젝트에서 Spring framework로 구현하려다 실패했었던 구독과 페이징 기능을 구현했다는 점만으로도 큰 의미**가 있었습니다. **Spring framework를 가지고 의존성과 톰캣 버전 관리 등 초기에 오랜 시간동안 설정하며 버려지던 시간들을 Spring boot에서는 더 편리하게 사용하며 시간 단축할 수 있다는 점에서 큰 수확**이 있었습니다.
- 특정 회원이 특정 페이지에 접근하려고 할 때, 접근 가능 여부를 판가름 하는 로직을 짜고 싶었습니다. 각 회원에게 회원 등급을 부여하고, 접근하려고 할 때마다 해당 로직을 발동시켜서 요구되는 등급의 회원이면 접근 하도록 허락해주면 되겠다 라고 생각을 했었습니다. 하지만, 개발해야 할 페이지가 많아질 수록 각각 로직을 적용시켜주어야 하는 불편함이 생겨 해당 방법을 제외하고 어떤 방법이 있을 지 고민하게 되었습니다. 그러다 Spring Security를 알게 되었고, 보안과 관련하여 체계적으로 많은 옵션을 제공해주는 **Spring Security 을 본격적으로 사용**해보게 되었습니다. WebSecurityConfigurerAdapter 를 상속 받는 클래스에서, 특정 페이지로 접근하려면 특정 회원 등급이 필요하도록 설정하고 Configuration 어노테이션을 붙여주니 각각 설정할 필요 없이 한 클래스 내에서 모든 게 해결 되었습니다.
- 특히 Junit 을 이용한 테스트 주도 개발(TDD)에 입문해보았습니다. 이를 통해 재사용 되는 코드들을 보며 OOP 에 조금 더 이해할 수 있었습니다. 또한 향후에 발생할 수 있을 법한 오류나 이슈를 예측해보며 미리 컨트롤 해볼 수 있는 유익한 경험을 했습니다.

## 개발 중 이슈와 해결

| 문제점 | 해결책 |
| --- | --- |
| Spring 의 경우에는 톰캣을 직접 프로젝트 내부에서 서버 설정 및 버전 관리를 해주어야 하는 불편함이 있었음. | Spring Boot 의 경우에는 이미 자체내 톰캣 내장 서버가 존재하기 때문에 설치와 버전관리에 신경쓰지 않고 개발만 할 수 있다는 점을 통해 불편 해소함. |
| 특정 회원이 특정 페이지에 접근하려고 할 때, 접근 가능 여부를 판가름 하는 로직을 짜고 싶었음. 각 회원에게 회원 등급을 부여하고, 접근하려고 할 때마다 해당 로직을 발동시켜서 요구되는 등급의 회원이면 접근 하도록 허락해줬음. 하지만, 개발해야 할 페이지가 많아질 수록 각각 로직을 적용시켜주어야 하는 불편함이 생겼음. | 보안과 관련하여 체계적으로 많은 옵션을 제공해주는 Spring Security 을 사용했음. WebSecurityConfigurerAdapter 를 상속 받는 클래스에서, 특정 페이지로 접근하려면 특정 회원 등급이 필요하도록 설정하고 Configuration 어노테이션을 붙여주니 각각 설정할 필요 없이 한 클래스 내에서 모든 게 해결 되었음. |
| 로컬에서 테스트할 때는 OAuth2.0을 통한 페이스북 소셜 회원가입과 로그인이 가능했지만, 배포 이후에 http로 접근되는 url에서는 페이스북의 보안 정책 변화로 인해, http 접근이 불가하다는 사실을 알게 되었음. 결국 배포까지 다 했지만, 구현한 기능을 사용하지 못하고 있는 상황에 놓임. | 관련된 정보를 찾다보니 https를 적용 시켜야 하는데, 그러기 위해서는 SSL 인증서 발급을 받아야만 했음. Cloud Flare라는 플랫폼을 통해서 무료 SSL 인증서 발급이 가능하다는 사실을 캐치하고, 내가 배포생성한 AWS EC2 PUBLIC IP를 인증받으려 했으나, Cloud Flare에서 인가한 DNS 설정을 해주어야 했기 때문에 정상적인 도메인 주소가 필요로 했음. 그리하여 호스팅 업체를 통해 도메인을 구매 후, DNS 설정을 기존 AWS에서 Cloud Flare로 변경해주고 수시간 후에 DNS 전파가 완료되었다는 메시지를 받음. 이후 해당 도메인으로 접근해보니 https가 적용되고, 페이스북에서도 안전한 사이트라 판단하여 OAuth2.0 로그인이 가능하게 되었음. |

# Spring Boot(API Server)

### JSP(MAP)에서 Data Request → JSON으로 Response

> 
> 
- config : project configurations 관리
- domain : DB에서 Table과 Mapping
- handler : custom exception message 관리, aop를 이용해 validation
- service : business logic 처리
- util : 공통응답을 위한 util 기능 관리
- web
    - dto : 일반적인 request/response dto와 공통응답을 위한 dto를 관리
    - api : data를 응답할 controller를 관리

# JPA+QLRM(ORM)

### 반복적인 CRUD 작성과 쿼리 작성을 자동화

> 
> 
- JPA : 반복적인 CRUD 작업을 대체해 간단히 DB에서 데이터를 조회
- QLRM : JPA Native Query 결과값을 Entity가 아닌 특정 DTO로 매핑

# OAuth2.0(Login)

### HTTP Session을 통한 소셜 로그인

> 
> 
- oauth2Login() : ouath2 로그인이 가능하도록 처리
- userInfoEndPoint() : 인증에 성공하면 oAuth2DetailsService에서 해당 USER 정보에 대해서 후처리

### 의존성

- Sring Boot DevTools
- Lombok
- Spring Data JPA
- MariaDB Driver
- Spring Security
- Spring Web
- oauth2-client

# Views

- **회원가입 및 로그인**

  <p align="center"><img src="https://github.com/DEVZZAME/photogram/blob/master/login.gif?raw=true"/></p>





- **메인 페이지 및 피드** 

  <p align="center"><img src="https://github.com/DEVZZAME/photogram/blob/master/feed.gif?raw=true"/></p>





- **마이 페이지**

  <p align="center"><img src="https://github.com/DEVZZAME/photogram/blob/master/mypage.gif?raw=true"/></p>





- **돋보기 페이지**

  <p align="center"><img src="https://github.com/DEVZZAME/photogram/blob/master/seach.gif?raw=true"/></p>





- **검색 페이지**

  <p align="center"><img src="https://github.com/DEVZZAME/photogram/blob/master/searching.gif?raw=true"/></p>
