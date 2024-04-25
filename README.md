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
- 프로젝트 init V
- DB 모델링 V
- 엔티티 설계 V
- 회원관리 기능 구현
    - 회원가입 V
    - 회원탈퇴 V
- Validation 적용 V
- 세션방식 로그인 구현 V
- 회원정보수정 구현 V
- 인터셉터 방식 인증 구현 V
- @SessionAttribute를 @Login으로 대체 V
- API 예외처리 구현 & api response 공통 형식 적용 V
- 지금까지 구현한 기능들에 대해 테스트 코드 작성
- 클라우드에 배포, CI / CD
- 필요한 곳들에 주석 작성
- 포트폴리오 관리 기능 구현
- 데이터 채우기(api 호출)
- 백테스트 기능 구현
- 메시지가 너무 산발되어 있을 경우, 공통 코드화 작업

# 상세 요구사항(프로젝트 마무리 후 작성)
- 디테일한 요구사항
- 해당 요구사항을 구현하기 위해 어떤 방식을 택했고, 왜 그 방식을 택했는지 간단한 설명
- 코드 스니펫

# 아직 개선할 점
- 예외처리와 api 응답 형식 처리가 현재 프로젝트 단계에 최적의 방식이 맞는지 고민 및 개선
- 인증 기능을 SpringSecurity로 통합시키기
- 보다 더 단위 테스트에 걸맞게 테스트 개편 및 작성하지 못한 테스트코드 보강
