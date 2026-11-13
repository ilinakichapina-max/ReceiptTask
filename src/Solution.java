import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
    public BigInteger getMul() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("data.txt"))) {
            int limit = Integer.parseInt(scanner.nextLine().trim());
            String[] strings = scanner.nextLine().trim().split("\\s+");

            int[] delimiters = new int[strings.length];

            for (int i = 0; i < strings.length; i++) {
                delimiters[i] = Integer.parseInt(strings[i]);
            }

            BigInteger mul = BigInteger.ONE;

            for (int i = 1; i <= limit; i++) {
                for (int delimiter : delimiters) {
                    if (delimiter == 0) continue; // защита от деления на 0
                    if (i % delimiter == 0) {
                        mul = mul.multiply(BigInteger.valueOf(i));
                        break;
                    }
                }
            }

            return mul;
        }
    }

    // Метод main для проверки работы
    public static void main(String[] args) {
        try {
            Solution solution = new Solution();
            System.out.println(solution.getMul());
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл data.txt не найден!");
        }
    }
}