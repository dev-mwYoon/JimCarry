## JimCarry
스프링 부트 : 공유 창고 중개 서비스


### :desktop_computer: 프로젝트 소개
개개인의 창고에 다른 사람의 짐을 보관할 수 있는 서비스.
창고 제공자는 방치된 빈 방을 경제적이고 활용성 있게 만들 수 있으며, 창고 사용자는 집의 여유공간을 확보할 수 있다.


### :mantelpiece_clock: 개발 기간
* 23.03.13 ~ 23.04.07


### :people_holding_hands: 맴버구성 - 백엔드 업무
 - 팀장  : 황자현 - 관리자페이지
 - 부팀장 : 문은서 - 창고등록, 문의하기, 공지사항
 - 팀원1 : 강민구 - 마이페이지
 - 팀원2 : 김주연 - 검색 및 상세페이지
 - 팀원3 : 김서현 - 메인페이지, 결제페이지
 - 팀원4 : 정현진 - 로그인, 회원가입, ID찾기, PW찾기
 - 파견 : 윤민우 - 검색 및 상세페이지


### 프로젝트 목적
창고 제공자와 창고 사용자 간의 새로운 사회적 연결망이 형성하여, 창고제공자와, 사용자의 경제성과 공간 효율성을 확보하여 사람들의 삶의 질을 향상시킬 수 있는 서비스 기획


### 기획배경
현대 사회에서 많은 사람들이 도시에 살며 작은 공간에서 살아가고 있음에 따라 집안에 물건을 보관하는 것도 쉽지 않은 문제가 되고 있다. 이러한 문제점을 해결하기 위해, 창고 제공자와 창고 사용자를 연결해주는 서비스를 만들어 창고 제공자는 방치된 빈 방을 경제적이고 활용성 있게 만들 수 있고, 창고 사용자는 집의 여유공간을 확보하여 더 편안한 생활을 할 수 있도록 기획하였습니다.


### 목적 및 기대 효과
- 창고 제공자 : 방치된 빈 방을 경제적으로 활용 가능
- 창고 사용자 : 협소한 주거공간 확보 가능
- 1인 가구 및 이삿짐, 취미용품 등 창고 보관을 통해 공간성 확보


### 프로젝트에서 맡은 역할
- 서비스 기획 및 전반적인 디자인 업무 수행
- 프론트 업무 : 창고등록페이지, FAQ, 문의하기, 서비스 이용안내 페이지
- 백엔드 업무 : 검색페이지, 창고 상세페이지 기능 구현


### :gear: 개발 환경
- java
- jQuery
- Thymeleaf
- Spring Boot
- HTML, CSS, JS
- MySQL
- JDK 11.0.15
- YAML
- JSON
- REST:API
- Sourcetree
- DBeaver
- IntelliJ IDEA
- git, gitHub
- JUnit5
- POSTMAN
- Lombok
- Boot pay
- Naver OAuth
- Kakao OAuth
- Cool SMS api


## :pushpin: 맡은 역할
### 프론트 진행 - <a href="https://github.com/coder-juyeon/JimCarry/wiki/front">상세보기 - WIKI 이동</a>
- 창고등록페이지
- FAQ
- 문의하기
- 서비스 이용안내 페이지
- 주소 API 연동


### 백엔드 진행 - <a href="https://github.com/coder-juyeon/JimCarry/wiki/backend">상세보기 - WIKI 이동</a>
#### 검색페이지
 - 지역별 검색
 - 규모별 검색
 - 정렬별 검색
 - 주소 키워드 검색


#### 창고상세페이지
- 창고 상세 정보
- 후기 목록 


### ERD
![짐캐리 포토폴리오 drawio](https://user-images.githubusercontent.com/122762287/233322002-5be1e3da-90ba-4e2a-ab88-38ad95b48aaf.png)


### 프로젝트에서 느낀점
- 어려웠던 부분
  1. 처음부터 같이 작업하지 않았기 때문에 환경과 코드가 생소해 적응하는데 많은 시간을 소요시켰습니다.
      ㄴ 환경과 코드가 이해 안가는 부분 때문에 기존 작업자분들에게 여쭤보는 시간이 많았고, 여쭤볼 수 없는 상황에서는 혼자 이해하기 위해 시간을 쏟았습니다.
  2. 기존 작업하던 분과 소통의 어려움
      ㄴ 작업환경이 다르기 때문에 제가 궁금한 점(동기 통신으로 작업한 이유 등)을 기존 작업하던 분이 기분 상하지 않도록 전달하는 것이 어려웠습니다.
   
- 문제를 해결했던 부분
  1. 기존 동기 통신으로 작업된 것을 비동기 통신으로 변경.
  2. 다중 조건 동적 쿼리 추가
  3. 페이징 기능 추가
 
- 협업의 중요성
 : 모르는 부분이 있을 때는 바로 물어봐야 한다는 것을 깨달았습니다. 처음 보는 코드를 혼자 이해하기 위해서는 오랜 시간이 걸렸지만,
    코드 작성에게 물어보니 몇분 걸리지도 않았습니다.
    
  
- 총평
: 이 일을 계기로, 제가 앞으로 어떤 업무를 맡게 된다고 했을 때 시간을 효율적으로 사용하는 방법을 배웠습니다.
