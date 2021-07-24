# 피보나치 수 6
문제풀이 방법: 분할정복. 행렬의 거듭제곱을 이용해 피보나치 수를 해결.<br>

Fn = Fn-1 + Fn-2 (n>=2)라는 점에서 활용하면<br>
Fn+1 = Fn + Fn-1 이 성립하게 되고, 이를 변형시키면 
Fn+1 = 2Fn-1 + Fn-2가 된다.<br>
이를 행렬 식으로 변경 후, 패턴을 찾을 수 있다. 행렬의 거듭제곱을 이용해 해당 값을 사용.

주의할 점은 n이 int를 넘어가는 경우가 발생한다는 점을 유의.
#
### Fib 값 구하는 함수, n이 짝수일 때, 홀수일 때 구분 필요
```java
public static long matrixFib(long n) {
    if (n == 0)
        return 0;
    else if (n == 1)
        return 1;
    else {
        long[][] A = matrixPow(n / 2);
        if (n % 2 == 0) {
            return A[0][1];
        } else {
            return A[1][1];
        }
    }
}
```
### 행렬의 거듭제곱 구하는 함수
```java
public static long[][] matrixPow(long n) {
    long[][] A = {{1, 1}, {1, 2}};
    if (n == 1) {
        return A;
    } else {
        long[][] temp = matrixPow(n / 2);
        temp = matrixMultiple(temp, temp);
        if (n % 2 == 1) {
            temp = matrixMultiple(temp, A);
        }
        return temp;
    }
}
```
#

### 노트풀이:
![11444_note](https://user-images.githubusercontent.com/37585417/107178268-2efc2280-6a17-11eb-91bc-8fc76cb69617.jpg)

#
문제: https://www.acmicpc.net/problem/11444