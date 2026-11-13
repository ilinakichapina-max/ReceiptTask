public class Cipher {
    private static final char[] ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
    private static final char[] ENCRYPTED = "яюэьыъщшчцхфутсрпонмлкйизжёедгвба".toCharArray();

    public static String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = findIndex(ALPHABET, c);
            if (index != -1) {
                result.append(ENCRYPTED[index]);
            } else {
                result.append(c); // Если символа нет в алфавите (пробел, цифра), оставляем как есть
            }
        }
        return result.toString();
    }

    public static String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = findIndex(ENCRYPTED, c);
            if (index != -1) {
                result.append(ALPHABET[index]);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // Вспомогательный метод для поиска индекса символа в массиве
    private static int findIndex(char[] array, char target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String original = "привет мир";
        String encrypted = encrypt(original);
        String decrypted = decrypt(encrypted);
        System.out.println("Оригинал:    " + original);
        System.out.println("Зашифровано: " + encrypted);
        System.out.println("Расшифровано: " + decrypted);
    }
}

