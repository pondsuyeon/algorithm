# 요세푸스 문제 0
문제 풀이 방법: 큐<br>
인데 실제 풀이 방법에서 자바 라이브러리 Queue를 사용하지는 않았음.<br><br>
K번째에 있는 수를 구하기 위해 인덱스로 접근.<br>
ArrayList 사용. ArrayList의 remove(index), size() 이용<br><br>

```java
while (true) {
            if (arr.size() <= 0)
                break;
            idx = (idx + K - 1) % arr.size();
            sb.append(arr.get(idx)).append(", ");
            arr.remove(idx);
        }
```
#
### 노트 풀이:
![11866_note](https://user-images.githubusercontent.com/37585417/105662557-5a5d1880-5f13-11eb-8a96-f1026c38f2f9.jpg)
# 
문제: https://www.acmicpc.net/problem/11866