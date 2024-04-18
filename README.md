# Core Features
1. 회원 관리
    - 회원 가입 및 로그인
    - 프로필 사진 설정(추후 구현)
    - 닉네임 설정
2. 포트폴리오 관리
    - 회원은 여러개의 포트폴리오를 생성 / 수정 / 삭제할 수 있다
    - 포트폴리오는 다양한 주식들로 이루어질 수 있다
    - ~~포트폴리오 내의 자산분배는 비율로 선택할 수도 있고, 금액을 직접 정할 수도 있다~~
3. 좋아요 종목 관리
    - 회원은 종목에 좋아요를 클릭하여 좋아요 지정, 해제를 할 수 있다
    - 좋아요는 종목 당 한번씩만 할 수 있다
4. 종목 정보 제공
    - 가장 좋아요가 많은 순으로 종목을 조회할 수 있다
    - 포트폴리오에 가장 많이 포함된 순으로 종목을 조회할 수 있다
5. ~~백테스트~~
    - 과거 기록을 토대로 내가 추린 포트폴리오로 백테스트 진행
6. ~~포트폴리오 추천~~
    - 고객의 성향에 맞는 트레이딩 전략을 적용한 포트폴리오 추천
7. ~~모의투자~~
    - 실시간 주식정보를 이용하여 현재 시점에서의 수익률을 알아보는 기능

# Database Modeling
1. USER
    - id(PK)
    - login_id
    - encrypted_password
    - nickname
    - ~~profile_photo~~
2. STOCK
    - id(PK)
    - name
3. ~~STOCK_HISTORY~~
    - id(PK)
    - stock_id
    - standard_price
    - standard_date
4. PORTFOLIO
    - id(PK)
    - user_id
5. PORTFOLIO_ITEM
    - id(PK)
    - portfolio_id
    - user_id
    - stock_id
    - ~~number_of_shares~~
6. LIKE
    - id(PK)
    - user_id
    - stock_id

# Project Plan
- 프로젝트 init
- DB 모델링
- 엔티티 설계
- 회원관리 기능 구현
    - 회원가입
    - 회원탈퇴
    - 로그인 및 인증(회원과 비회원 간의 서비스 이용권한 분리)
- Validation & 예외처리 적용
- api response 공통 형식 적용
- 클라우드에 배포, CI / CD
- 포트폴리오 관리 기능 구현
- 데이터 채우기(api 호출)
- 백테스트 기능 구현