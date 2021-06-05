import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] s = br.readLine().toCharArray();     // 입력 문자열 -> char 배열
        char[] b = br.readLine().toCharArray();     // 폭탄 문자열 -> char 배열

        int sLen = s.length;
        int bLen = b.length;

        int bIdx = 0;
        int top = -1;

        Element[] st = new Element[sLen];           // stack 사용, top = -1

        for (int i = 0; i < sLen; i++) {
            if (s[i] == b[bIdx]) {                                  // bomb 문자열의 index와 같으면
                st[++top] = new Element(bIdx++, s[i]);              // stack에 해당 문자열 index 값을 넣어줌.
            } else {
                if (s[i] == b[0]) {
                    st[++top] = new Element(0, s[i]);            // 해당 위치의 문자열이 아니지만, 맨 처음 값인 경우 비교해서 0 index 넣어주고, bIdx++
                    bIdx = 1;
                } else {
                    st[++top] = new Element(-1, s[i]);           // 해당 문자열이 아니면 -1.
                    bIdx = 0;
                }
            }

            if (bIdx == bLen) {                                     // stack에 삽입 후, bIdx == bLen, 폭탄 문자열이 다 입력 되었을 때
                boolean status = true;
                for (int j = 0; j < bLen; j++) {                    // 차례대로 해당 폭탄 문자열이 있는지 없는지 확인
                    if (st[top - bLen + 1 + j].i != j) {        
                        status = false;
                        break;
                    }
                }
                if (status) {                                       // 해당 문자열이 다 확인이 되었으면 top값을 업데이트
                    top = top - bLen;
                    if (top > -1 && st[top].i != -1) bIdx = st[top].i + 1;      // 만약 마지막 top 위치의 값이 폭탄 문자열의 일부일 경우, bIdx를 업데이트
                    else {
                        bIdx = 0;                                   // 아닐 경우 다시 0으로 초기화
                    }
                } else {
                    bIdx = 0;
                }
            }
        }

        for (int i = 0; i <= top; i++) {
            sb.append(st[i].c);
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb);
    }

    static class Element {
        int i;
        char c;

        public Element(int i, char c) {
            this.i = i;
            this.c = c;
        }
    }
}