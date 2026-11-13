import java.io.File;

public class Fail {
    public static void main(String[] args) {
        String path = "C:\\Users\\Svetik\\Desktop\\GitTest"; // Укажите ваш путь
        File folder = new File(path);

        if (folder.exists() && folder.isDirectory()) {
            long sizeInBytes = getFolderSize(folder);
            double sizeInMb = (double) sizeInBytes / (1024 * 1024);
            System.out.printf("Размер папки: %.2f МБ%n", sizeInMb);
        } else {
            System.out.println("Указанный путь не существует или не является папкой.");
        }
    }

    private static long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    length += file.length();
                } else {
                    length += getFolderSize(file);
                }
            }
        }
        return length;
    }
}