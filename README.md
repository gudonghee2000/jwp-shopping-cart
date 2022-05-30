# 🛒 장바구니
장바구니 미션 저장소

## 💻 구현 기능 목록

### 토큰
- [ ] 로그인 시 토큰을 발급한다.
- [ ] 서버로 토큰이 받으면 토큰이 유효한지 판단한다.
  - [ ] 만료되지 않은 토큰인지 판단한다.
  - [ ] 토큰에 담긴 정보가 외부에서 수정되었는지 판단한다.

### 사용자
- [ ] 사용자 이름, 비밀번호를 입력하여 회원가입을 한다.
  - [x] [예외처리] 사용자 이름, 비밀번호는 빈값또는 null 을 입력하면 안된다.
  - [ ] [예외처리] 사용자 이름은 중복되면 안된다.
  - [ ] [예외처리] 비밀번호는 `대소문자 포함한 영어, 숫자, 특수문자` 만 입력할 수 있다.
- [ ] 로그인을 한다.
  - [ ] [예외처리] 사용자 이름, 비밀번호는 빈값또는 null 을 입력하면 안된다.
  - [ ] [예외처리] 회원가입한 사용자 정보로 로그인해야 한다.
- [ ] 회원정보를 조회한다.
  - [ ] [예외처리] 본인 이외의 다른 사용자 정보를 조회할 수 없다.
  - [ ] [예외처리] 회원가입한 사용자 정보를 조회해야 한다.
  - [ ] [예외처리] 탈퇴한 사용자 정보를 조회하면 안된다.
- [ ] 회원정보를 수정한다.
  - [ ] [예외처리] 본인 이외의 다른 사용자 정보를 수정할 수 없다.
  - [ ] [예외처리] 회원가입한 사용자 정보를 수정해야 한다.
  - [ ] [예외처리] 탈퇴한 사용자 정보를 수정하면 안된다.
  - [ ] [예외처리] 사용자 이름은 빈값또는 null 을 입력하면 안된다.
  - [ ] [예외처리] 사용자 이름은 중복되면 안된다.
- [ ] 비밀번호를 변경한다.
  - [ ] [예외처리] 본인 이외의 다른 사용자 정보를 수정할 수 없다.
  - [ ] [예외처리] 회원가입한 사용자 정보를 수정해야 한다.
  - [ ] [예외처리] 탈퇴한 사용자 정보를 수정하면 안된다.
  - [ ] [예외처리] 비밀번호는 빈값또는 null 을 입력하면 안된다.
  - [ ] [예외처리] 비밀번호는 `대소문자 포함한 영어, 숫자, 특수문자` 만 입력할 수 있다.
- [ ] 탈퇴한다.
  - [ ] [예외처리] 본인 이외의 다른 사용자 정보를 수정할 수 없다.
  - [ ] [예외처리] 회원가입한 사용자 정보를 수정해야 한다.
  - [ ] [예외처리] 탈퇴한 사용자 정보를 수정하면 안된다.

## ✏️ Code Review Process
[텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)