# 패션왕 신해빈

의상 종류를 저장하고, 해당 의상 종류가 저장되어 있으면 카운트를 늘리는 방식.<br>
ArrayList를 사용해 string을 저장, indexof를 이용해 index 가져와서 카운트 저장.

```java
 int index = kinds.indexOf(kind);           // 해당하는 의상 종류명이 있으면 해당 index 반환 없으면 -1
    if (index == -1) {                      // 없는 경우 리스트에 추가
        kinds.add(kind);
        index = kinds.size() - 1;
    }
    cnt[index]++;
```
```java
int ans = 1;
for (int i = 0; i < kinds.size(); i++) {
    ans *= cnt[i] + 1;                      // 전체 경우의 수: 각 의상의 개수 + 1(해당 종류의 의상을 입지 않는 경우) * 전체 - 1 (모두 입지 않는 경우)
}
ans--;
```

#

문제: https://www.acmicpc.net/problem/9375