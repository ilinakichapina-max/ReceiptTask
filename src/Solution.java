import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public void writeOrder() throws IOException {
        Scanner scanner = new Scanner(new FileInputStream("numbers.txt"));
        int index = 1;
        String resString = "";

        while (scanner.hasNextLine()) {
            String nowNumber = scanner.nextLine();
            if (Integer.parseInt(nowNumber) == index) {
                resString += nowNumber + " ";
            }
            index++;
        }

        if (resString.equals("")) {
            resString = "0";
        }

        FileWriter writer = new FileWriter("output.txt", false);
        writer.write(resString.trim());
        writer.flush();
    }

    // Метод main для запуска проверки
    public static void main(String[] args) {
        try {
            Solution solution = new Solution();
            solution.writeOrder();
            System.out.println("Файл output.txt успешно создан!");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}