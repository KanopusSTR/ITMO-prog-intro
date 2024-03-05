import java.util.Scanner;
import java.lang.Math;

public class B {
    public static void main(final String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = 0;
        for (int i = -25000; i < 25001; i++) {
            System.out.println(710 * i);
            m++;
            if (m == n) {
                break;
            }
        }
    }
}
