# 회전하는 큐

문제풀이 방법: 큐, ArrayList

<B>키포인트: 회전 큐, 원형 큐라는 점을 이용하여 인덱스 접근. 왼쪽, 오른쪽 카운트 수 비교 후 최소값 이용.</B><br>

접근 방법:
1. ArrayList를 이용해 queue 선언
2. 뽑아야할 수가 위치한 인덱스 값 반환
3. 인덱스 값과 현재 위치의 거리를 모듈러 연산을 이용해 최솟값 계산 후 저장
4. 해당 인덱스의 값 뽑기

```java
while (!num.isEmpty()) {
    temp = queue.indexOf(num.peek());
    a = ((now - temp) % queue.size() >= 0) ? (now - temp) % queue.size() : ((now - temp) % queue.size() + queue.size());
    b = ((-now + temp) % queue.size() >= 0) ? (-now + temp) % queue.size() : ((-now + temp) % queue.size() + queue.size());
    cnt += Math.min(a, b);
    now = temp;
    queue.remove(now);
    num.poll();
}
```
모듈러 연산이 조금 지저분하긴 하나, 음수에 대한 모듈러 연산의 값이 양수로 나오지 않아 직접 선언.

#

문제: https://www.acmicpc.net/problem/1021