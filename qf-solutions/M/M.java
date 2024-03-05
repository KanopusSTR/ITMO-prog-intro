import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
public class M {
    public static void main(final String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a = 0; a < t; a++) {
            int sum = 0;
            int n = in.nextInt();
            int[] numbers = new int[n];
            for (int b = 0; b < n; b++) {
                numbers[b] = in.nextInt();
            }
            Map<Integer, Integer> c = new HashMap<>();
            for (int j = n - 1; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    sum += c.getOrDefault(2 * numbers[j] - numbers[i], 0);
                }
                c.put(numbers[j], c.getOrDefault(numbers[j], 0) + 1);
            }
            System.out.println(sum);
        }
    }
}
