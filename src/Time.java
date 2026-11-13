import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Time {
    String date = "";

    public void getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1); // 1 миллисекунда после эпохи

        // Создаем форматтер с нужным паттерном и английской локалью
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MMM dd HH:mm:ss", Locale.ENGLISH);

        // Форматируем и записываем в переменную
        date = dateFormat.format(calendar.getTime());

        // Выводим на экран
        System.out.println(date);
    }
}



