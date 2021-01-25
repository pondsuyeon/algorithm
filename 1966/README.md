# 프린터 큐

문제풀이 방법: 큐 이용(Java 라이브러리), ArrayList sort

<B>키포인트: 맨 앞 문서의 중요도에 따라, 뒤에 현재 보다 높은 중요도 존재시, 맨 뒤로 배치</B><br>

접근 방법:
1. 중요도를 새로운 ArrayList에 넣어 내림차순으로 sort
2. queue의 peek과 중요도 리스트의 0번째 중요도와 일치 여부 확인 후 일치시 poll
3. M번째 수는 index 변수를 부여해 과정이 진행될 때마다 M번째 수의 인덱스 변동을 저장

```java
while (true) {
    if (queue.peek().equals(importance.get(0))) {
        cnt++;
        if (idx == 0) {
            break;
        } else {
            queue.poll();
            importance.remove(0);
            idx--;
        }
    } else {
        if (idx == 0) {
            idx = queue.size() - 1;
        } else {
            idx--;
        }
        queue.offer(queue.poll());
    }
}
sb.append(cnt).append("\n");
```

#

문제: https://www.acmicpc.net/problem/1966
