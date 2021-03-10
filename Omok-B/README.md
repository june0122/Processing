# SPOP

- SPOP은 TCP/IP 소켓을 이용해 만든 네트워크 오목게임이다.
- 클라이언트와 서버는 지정된 프로토콜에 따른 메시지를 주고 받는다.
- SPOP은 ① 대기, ② 게임, ③ 종료, 세 가지의 상태를 갖는다.
- ① 대기 : 클라이언트의 정보를 확인할 수 있다.
	- ‘게임’ 상태로 넘어가기 위한 ‘READY’ 버튼이 있다.
	- 서버로 부터 게임 시작 메시지를 받으면 카운트 다운, 게임 시작 문구가 출력된다.
- ② 게임 : 클라이언트의 게임 순서를 확인하기 위한 애니메이션이 출력된다.
	- 각각의 순서에 따라 누구의 차례인지 확인할 수 있다.
  - 마우스 클릭을 이용해 게임을 진행한다.
- ③ 종료 : 게임 결과를 확인하며, 일정 시간 후 '대기' 상태가 된다.

## Document
- https://docs.google.com/presentation/d/e/2PACX-1vSe-kU4wcEAxMBu8TeGI2gRE1Nrr5kM2mU2WSHTAdWPnAEmhj2H8-SjqtLYspdTiVyC6cXruoo9NYtA/pub?start=false&loop=false&delayms=3000
