
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by a.zinov on 05.09.2017.
 * некоторые задачи тут: https://habrahabr.ru/post/312278/
 */
public class Solution {

    public static void main(String[] args) {

//        rebus();
//        matrixSnakeDiagonal(1013);
//        withoutPolyndromeCounter(13924);
//        remarkableNumberCheck(2700000);
        leastPositiveInteger();


    }

    public static String strValue(Integer n) {
        if (n.toString().length() > 1){
            char[] chars = n.toString().toCharArray();
            Arrays.sort(chars);
            return String.valueOf(chars);
        }else {
            return n.toString();
        }
    }

    public static void leastPositiveInteger() {

        int a = 2, b = 5, founded = 0;
        //double xFirstMax = Math.floor(9/Math.max(a, b));
        String xSorted = "";
        for (Integer x = 1; x <= 100000000; x++) {
            //if (x.toString().toCharArray()[0] > (int)xFirstMax) continue;
            xSorted = strValue(x);
            if (xSorted.equals(strValue(a*x)) && xSorted.equals(strValue(b*x))){
                founded = x;
                break;
            }
        }

        printInt(founded);
    }

    public static void remarkableNumberCheck(int n) {

        int count = 0;
        for (int i = 1; i < n; i++) {
            if (remarkableNumber(i))
                count++;
        }
        printInt(count);
    }

    public static boolean remarkableNumber(Integer p) {

//        if (p == 3523014){
//            printInt(3523014);

            String[] ints = p.toString().split("");
            ArrayList<Integer> arrayList = new ArrayList<>();
            Integer sum = 0;

            for (int i = 0; i < ints.length; i++) {
                Integer buff = Integer.parseInt(ints[i]);
                if (buff > 0) {
                    arrayList.add(buff);
                    sum += buff;
                }
            }

            if (sum == 10) {
                return true;
            }else if (sum < 10) {
                return false;
            }else {
                if ((arrayList.get(0) + arrayList.get(1) > 10)
                        || (arrayList.get(arrayList.size() - 1) + arrayList.get(arrayList.size() - 2)) > 10){
                    return false;
                }

                Integer[] integers = arrayList.toArray(new Integer[arrayList.size()]);

                if ((integers[0] + integers[1] > 10) || (integers[integers.length - 1] + integers[integers.length - 2] > 10)){
                    return false;
                }

                Integer[][] intBuffer = new Integer[integers.length][integers.length];

                for (int i = 0; i < integers.length; i++) {
                    intBuffer[i][i] = integers[i];
                }

                ArrayList<Integer> checkList = new ArrayList<>();
                for (int i = 0; i < arrayList.size(); i++) {
                    checkList.add(0);
                }

                for (int i = 0; i < intBuffer.length - 1; i++) {
                    for (int j = i; j < intBuffer[i].length - 1; j++) {
                        intBuffer[i][j + 1] = intBuffer[i][j] + intBuffer[j + 1][j + 1];
                        if (intBuffer[i][j + 1] == 10) {
                            for (int k = i; k <= j + 1; k++) {
                                checkList.set(k, intBuffer[k][k]);
                            }
                        }else if (intBuffer[i][j + 1] > 10){
                            break;
                        }
                    }
                }

                if (arrayList.equals(checkList)){
                    return true;
                }
            }
        //}

        return false;
    }

    public static void withoutPolyndromeCounter(int n) {

        int count = 0;
        for (int i = 0; i <= n; i++) {
            if (withoutPolyndrome(i)) {
                count++;
            }
        }

        printInt(count);
    }

    public static boolean withoutPolyndrome(Integer p) {

        int isPolyndrome = 1;

        BigInteger bigInteger = new BigInteger(p.toString());


        for (int k = 0; k <= 50; k++) {

            BigInteger reverseBigInteger = new BigInteger(new StringBuilder(bigInteger.toString()).reverse().toString());
            BigInteger sumBigInteger = bigInteger.add(reverseBigInteger);

            String string = sumBigInteger.toString();
            for (int i = 0, j = string.length() - 1; i < j; i++, j--) {
                if (string.charAt(i) != string.charAt(j)) {
                    isPolyndrome = 0;
                    break;
                }
            }

            if (isPolyndrome == 1) {
                return false;
            }

            bigInteger = sumBigInteger;

        }

        return true;
    }

    public static void matrixSnakeDiagonal(int N) {

        int[][] m = new int[N][N];

        int i = N / 2;
        int j = N / 2;

        int min_i = i; int max_i = i; // влево вправо
        int min_j = j; int max_j = j; // вверх вниз
        int d = 2;

        for (int a = 1; a <= N * N; a++) {

            m[i][j] = a;

            switch (d) {
                case 0:
                    j -= 1;  // движение влево
                    if (j < min_j) { // проверка выхода за заполненную центральную часть слева
                        d = 1; // меняем направление
                        min_j = j; // увеличиваем заполненную часть влево
                    }
                    break;
                case 1:  // движение вверх проверка сверху
                    i -= 1;
                    if (i < min_i) {
                        d = 2;
                        min_i = i;
                    }
                    break;
                case 2:  // движение вправо проверка справа
                    j += 1;
                    if (j > max_j) {
                        d = 3;
                        max_j = j;
                    }
                    break;
                case 3:  // движение вниз проверка снизу
                    i += 1;
                    if (i > max_i) {
                        d = 0;
                        max_i = i;
                    }
            }


        }
//
//        for (int k = 0; k < N; k++) {
//            for (int v = 0; v < N; v++)
//                System.out.print(m[k][v] + "\t");
//            System.out.println();
//        }

        int sumMainD = 0;
        for (int i1 = 0; i1 < N; i1++) {
            for (int j1 = 0; j1 < N; j1++) {
                if (i1 == j1) {
                     sumMainD += m[i1][j1];
                }
            }
        }
        //printInt(sumMainD);

        int sumSideD = 0;
        for (int i1 = 0; i1 < N; i1++) {
            sumSideD += m[i1][N - i1 - 1];
        }
        //printInt(sumSideD);
        printInt(sumMainD + sumSideD);

    }

    // задача ребус
    public static void rebus() {
        int count = 0;
        // rzwr + rwtz = twqq
        for (int r = 1; r < 10; r++) {
            for (int z = 0; z < 10; z++) {
                if (z != r) {
                    for (int w = 0; w < 10; w++) {
                        if (w != z && w != r) {
                            for (int t = 0; t < 10; t++) {
                                if (t != w && t != z && t != r) {
                                    for (int q = 0; q < 10; q++) {
                                        if (q != t && q != w && q != z && q != r) {
                                            if ((1000*r + 100*z + 10*w + r) + (1000*r + 100*w + 10*t + z) == (1000*t + 100*w + 10*q + q)) {
                                                count++;
//                                                printInt(1000*r + 100*z + 10*w + r);
//                                                printInt(1000*r + 100*w + 10*t + z);
//                                                printInt(1000*t + 100*w + 10*q + q);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        printInt(count);
    }

    public static void printInt(int N) {
        System.out.println(N);
    }
}
