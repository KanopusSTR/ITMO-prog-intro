import java.io.IOException;
import java.util.Arrays;

public class ReverseTranspose {
    public static void main(final String[] args) {
        int[] numbers = new int[100];
        int[] stringNumber = new int[100];
        int countOfNumber = 0;
        int countOfString = 0;
        int lenN = 100;
        int lenS = 100;
        try {
            Scanner in = new Scanner(System.in);
            while (in.hasNextLine()) {
                if (lenS == countOfString) {
                    stringNumber = Arrays.copyOf(stringNumber, 2 * lenS);
                    lenS = lenS * 2;
                }
                Scanner string = new Scanner(in.nextLine());
                while (string.hasNextInt()) {
                    if (lenN == countOfNumber) {
                        numbers = Arrays.copyOf(numbers, 2 * lenN);
                        lenN = lenN * 2;
                    }
                    numbers[countOfNumber] = string.nextInt();
                    countOfNumber++;
                }
                stringNumber[countOfString] = countOfNumber - 1;
                countOfString++;
            }
        } catch (IOException e) {
            System.out.println("Can not read " + e.getMessage());
        }
        boolean checker = true;
        int level = 0;
        while (checker) {
            if (level != 0) {
                System.out.println();
            }
            checker = false;
            if (stringNumber[0] + 1 > level) {
                System.out.print(numbers[level] + " ");
                checker = true;
            } 
            for (int i = 1; i < countOfString; i++) {
                if (stringNumber[i] - stringNumber[i-1] > level) {
                    System.out.print(numbers[stringNumber[i-1] + level + 1] + " ");
                    checker = true;
                }
            }
            level += 1;
        }
    }
}
