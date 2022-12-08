Nebula auth
=============
52hertz 메타버스 플랫폼의 인증,인가를 제어/관리하는 서버입니다<br>
RestAPI 로 Unity Client 와 데이터를 송수신하며 유저의 정보와 인증 인가를 관리합니다<br>
<br>
<b>배포 주소 </b> : https://auth.mtvs-nebula.com

### 아키텍처
- github hooks / Jenkins를 이용해 개발 서버, 배포서버 CI/CD

### 구현 목표
<b>사용자의 인증 인가</b><br>
- spring security를 이용하여 인증 인가 구현
- 인증 인가는 JWT 토큰을 이용하여 관리
  - 각 서버는 JWT 토큰을 파싱하는 방법으로 사용자를 인증함
- 인증과정에 대한 로그를 기록하여 악성 공격에 대한 Tracing <br>

<b>클라우드 네이티브 배포</b>
- 설정 파일에 대한 변경은 actuator/refresh 를 통해 서버별 갱신
    - [ ] spring cloud bus를 통해 갱신 자동화
- 운영환경과 테스트 환경을 분리해서 배포
    - 운영환경과 개발환경은 main 브랜치를 ubuntu 상에서 도커이미지를 통해 가동

<b>성능 최적화</b>
- 데이터베이스에서 읽기요청이 많을 것으로 예상됨
    - [ ] CQRS 패턴 적용
    - [ ] 읽기 전용 데이터베이스를 분리하여 읽기 로직처리만 담당
- 인덱스와 쿼리 튜닝 활용
- [ ] JPA N+1 쿼리 문제 해결
  <br><br>

<b>코딩 컨벤션</b>
- Google Java Style Guide
- https://github.com/google/styleguide
- xml 파일을 인텔리제이 code style schema에 적용
  <br><br>

<b>지속 고려 사항</b>
- 중복코드 최소화
- 성능 개선

### CI
Jenkins : PR/Merge 시 테스트 자동화 <br>
### CD
Jenkins : Merge시 도커 이미지를 만들어 배포 <br>
보안 상 도커 허브에는 올라가지 않음 <br>
Build 성공 시 해당 이미지 파일로 서버 대체 실행 <br>


### 테스트
- 테스트 자동화 구현
    - github PR시 테스트 자동화
    - 메인에 머지 시 테스트 자동화
    - maven package 시 테스트 자동화
- [ ] 테스트 코드 작성 예정

### Database
- PostgreSQL<br>
  AmazonRDS 이용하여 배포
  private Subnet을 이용하여 외부 접속 제한
- [ ] MSA 아키텍처에 적합하게끔 추후 각 데이터베이스를 분리하여야 함

### 개발환경
- Java8
- Spring boot
- Maven
- JPA
- Docker
- PostgreSQL
- Jenkins
- AWS
    - Route53
    - LoadBalancer (Reverse proxy를 통한 https 연결을 위해 사용)
      - 차후 API 게이트웨이 등으로 변경이 필요함
    - EC2
    - VPC / Subnet
    - etc ...