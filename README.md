# 사용한 기능(내가 다시 꺼내 쓰기 위한 정리)
* Board -> 정적 팩토리 메서드
* Comment -> 빌더 패턴
* getBoards() -> 페이징, 페이저블
* GlobalExceptionHandler -> API, HttpMethod, RequestParam 잘못입력 예외, GlobalExceptionHandler에 정의하지 않은 Exception 발생 예외(중요 나중에 꼭 공부할것)

## 느낀점
* 빌더 패턴은 너무 긴거 같다 내가 느끼기엔 정적 팩토리 메서드가 좋은거 같다.
* 페이징 페이저블 공부한지 시간이 조금 지나 이번에 사용했는데 헷갈리는 부분이 많았다 다시 복습할때가 된것같다.
* GlobalExceptionHandler 익셉션 발생시 상태코드를 반환을 어떤 값으로 해야할지에 대해 잘 모르겠다. NullPointerException을 500, 404, 400 어떤걸 써야하는지에 대해 공부를 더 해봐야겠다.
