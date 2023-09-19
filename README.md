# 미션 기반 코드리뷰 매칭 플랫폼, LGTM

**'LGTM'은 SW 마에스트로 14기 프로젝트입니다.**

해당 LGTM-Android 레파지토리는 **[@kxxhyorim](https://github.com/KxxHyoRim)** 1인 개발로 이루어지고 있습니다. (팀 구성 = 백엔드 2명, 안드로이드 1명)

![image](https://github.com/hellokitty-coding-club/LGTM-Android/assets/59546818/4cff9177-ac15-4ddf-9a74-3a0654c8a502)




## ⚒️ Tech Stack

| Category            | Details                                      |
| ------------------- | -------------------------------------------- |
| Architecture        | MVVM, Multi Module, Clean Architecture, Hilt |
| AAC                 | Databinding, Livedata, ViewModel, Navigation |
| Concurrency         | Coroutine                                    |
| Third Party Library | OkHttp3, Retrofit2, Glide                    |
| CI/CD               | Github Actions                               |
| Language            | Kotlin(100%)                                 |



## ⛏️ Multi Module Architecutre

![project dot](https://github.com/hellokitty-coding-club/LGTM-Android/assets/59546818/f5e9f83b-8178-4afe-93e7-ad3733cfe9ab)



## ⛏️ Clean Architecutre

![image](https://github.com/hellokitty-coding-club/LGTM-Android/assets/59546818/164234cb-32e1-4d6b-91d0-a2e9e3801128)



## 🖌️ Server Drivne UI

- **Home 화면의 경우, 앱 배포 없이 UI를 변경할 수 있는 `Server Driven UI`를 활용했습니다.**

- **도입 이유**

  - 안드로이드 앱은 유저의 앱 업데이트를 강제하기 어려워서 유연한 변경사항 제공이 어렵다.
  - 새로운 기능 & 수정사항을 모두에게 빠르게 반영하지 못한다.
  - 클라이언트 코드 변경 없이 UI를 조정할 수 있어서 앱 배포 없이 A/B 테스팅을 유연하게 수행할 수 있다.

- **개괄적인 방식**

  1. 클라이언트-백엔드 ViewType 정의 및 Json 설계

  2. 서버가 API 응답에 UI 정보를 담아 클라이언트에 제공

  3. 안드로이드에서는 동적으로 ViewType을 보고 홈화면의 UI 구성

- **Response 설계**

  ![image](https://github.com/hellokitty-coding-club/LGTM-Android/assets/59546818/e1e12a75-9580-46c7-a981-423f1a0da17f)

- **안드로이드 구현**

  ![image](https://github.com/hellokitty-coding-club/LGTM-Android/assets/59546818/7d360f52-3aee-4b56-8be2-45fa6ae879be)

## 🖼️ 구현 화면

| 화면 분류          | 이미지                                                       |
| ------------------ | ------------------------------------------------------------ |
| 로그인 / 회원가입  | <img width=600 src="https://github.com/hellokitty-coding-club/LGTM-Android/assets/59546818/65caad40-0866-499a-90e4-80ef942c38c2"/> |
| 홈화면           | <img width=600 src="https://github.com/hellokitty-coding-club/LGTM-Android/assets/59546818/94a1cbc2-66fd-48df-8d4f-3cb03729f86c"/> |
| 미션 조회 (리뷰어) | <img width =600 src="https://github.com/hellokitty-coding-club/LGTM-Android/assets/59546818/d2fee2fb-5559-454b-a3b3-787874cd4478"/> |
| 미션 조회 (리뷰이) | <img width=600 src="https://github.com/hellokitty-coding-club/LGTM-Android/assets/59546818/33cccfdd-c43c-4faf-8d5e-87b3dcdae316"/> |

 
