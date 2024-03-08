# 기간
- 2/17 (erd modeling, UI)
- 2/18 (환경설정, check list, 화면)
- 2/19 (erd modeling 수정, 화면)
- 2/20 ~ (구현)
- 2/25 ~ 로그인시 예외처리
  ---
- 재설계 db , entity 부터 다시 설계해서 구현하려고 한다..
  기존의 데이터베이스 설계, dto 구현했을 때 필요없는 정보를 많이 전달받거나, 전달 하고 있다고 생각된다.
  
- 망했음.
  와이어프레임 없이 구현할 수 있을 것이라고 생각했다.
  화면을 구현하면서 없던 버튼이 생기고 그러면 다시 dto 부터 다시 수정하고 있던 화면이 사라지면 또 다시 앞부터 뒤까지 수정 관련된 변수,함수 수정해야한다.
  수정을 한다고 해도 다시 또 뭔가 추가되어야 할 것 같고 혹은 필요없을 것 같고 끝이 없다.

  해당 프로젝트에서 파일 업로드랑 몇가지 정도만 연습하고 다음 프로젝트에 와이어프레임 추가해서 진행해 보겠다.

# 목표
project1 에서 부족했던 부분을 보충

# ERD Modeling
<img src="https://github.com/hiyigh/project2/assets/112844031/4703e732-c795-418a-b40b-0e3539e14ea5">

# 화면
[check list](https://tricolor-havarti-76a.notion.site/check-list-6ec5d91d92dc4bd0b556a20d708ae885?pvs=4)
# 환경설정
- 기존 project 1 과 동일
- 
# 개선사항
- requset, response dto 생각해보기
- 필요한 데이터만 db 에서 가져오거나 저장할 수 있도록
- 글 입력시 text 말고 이미지를 입력하려면?
- 예외처리 
