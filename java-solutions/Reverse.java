import java.io.IOException;
import java.util.Arrays;

public class Reverse {
    public static void main(final String[] args) {
        String[] numbers = new String[100];
        int[] num = new int[100];
        int i = 0;
        try {
            Scanner in = new Scanner(System.in);
            while (in.hasNextLine()) {
                numbers[i] = in.nextLine();
                i++;
                if (i == numbers.length) {
                    numbers = Arrays.copyOf(numbers, 2 * numbers.length);
                }
            }
            for (int k = i - 1; k >= 0; k--) {
                int j = 0;
                Scanner hor = new Scanner(numbers[k]);
                while (hor.hasNextInt()) {
                    num[j] = hor.nextInt();
                    j++;
                    if (j == num.length) {
                        num = Arrays.copyOf(num, 2 * num.length);
                    }
                }
                for (int p = j - 1; p >= 0; p--) {
                    System.out.print(num[p] + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("can't read " + e.getMessage());
        }
    }
}
