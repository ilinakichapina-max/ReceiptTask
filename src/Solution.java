import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public String translate() throws FileNotFoundException {
        String[] bin = {
                "1000001","1000010","1000011","1000100","1000101","1000110","1000111",
                "1001000","1001001","1001010","1001011","1001100","1001101","1001110",
                "1001111","1010000","1010001","1010010","1010011","1010100","1010101",
                "1010110","1010111","1011000","1011001","1011010"
        };
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder result = new StringBuilder();

        try (Scanner in = new Scanner(new FileInputStream("message.txt"))) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    int idx = letters.indexOf(ch);
                    if (idx >= 0) {
                        result.append(bin[idx]);
                    } else {
                        result.append(ch);
                    }
                }
                result.append('\n');
            }
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        try {
            Solution solution = new Solution();
            System.out.println(solution.translate());
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл message.txt не найден!");
        }
    }
}