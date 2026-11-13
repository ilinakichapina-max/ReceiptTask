import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FileAnalyzer {
    public void getOutput() throws IOException {
        String[] bmp = new String[100];
        String[] txt = new String[100];
        String[] zip = new String[100];
        int bmpN = 0, txtN = 0, zipN = 0;

        long bmpBytes = 0, txtBytes = 0, zipBytes = 0;

        try (Scanner in = new Scanner(new FileInputStream("files.txt"))) {
            while (in.hasNextLine()) {
                String line = in.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] p = line.split(" ");
                String name = p[0];
                long size = Long.parseLong(p[1]);
                String unit = p[2];

                long bytes = size;
                if (unit.equals("KB")) bytes = size * 1024L;
                else if (unit.equals("MB")) bytes = size * 1024L * 1024L;

                int dot = name.lastIndexOf('.');
                String ext = (dot >= 0) ? name.substring(dot + 1) : "";
                ext = ext.toUpperCase();

                if (ext.equals("BMP")) { bmp[bmpN++] = name; bmpBytes += bytes; }
                else if (ext.equals("TXT")) { txt[txtN++] = name; txtBytes += bytes; }
                else if (ext.equals("ZIP")) { zip[zipN++] = name; zipBytes += bytes; }
            }
        }

        Arrays.sort(bmp, 0, bmpN);
        Arrays.sort(txt, 0, txtN);
        Arrays.sort(zip, 0, zipN);

        StringBuilder out = new StringBuilder();
        appendGroup(out, bmp, bmpN, bmpBytes);
        appendGroup(out, txt, txtN, txtBytes);
        appendGroup(out, zip, zipN, zipBytes);

        try (FileWriter writer = new FileWriter("output.txt", false)) {
            writer.write(out.toString().trim());
        }
    }

    private static void appendGroup(StringBuilder out, String[] arr, int n, long totalBytes) {
        if (n == 0) return;

        for (int i = 0; i < n; i++) {
            out.append(arr[i]).append('\n');
        }

        String[] pretty = toLargestUnit(totalBytes);
        out.append("----------\n")
                .append("Summary: ").append(pretty[0]).append(' ').append(pretty[1]).append("\n\n");
    }

    private static String[] toLargestUnit(long bytes) {
        double kb = bytes / 1024.0;
        double mb = kb / 1024.0;

        if (mb >= 1.0) return new String[]{String.valueOf(Math.round(mb)), "MB"};
        if (kb >= 1.0) return new String[]{String.valueOf(Math.round(kb)), "KB"};
        return new String[]{String.valueOf(bytes), "B"};
    }

    public static void main(String[] args) {
        try {
            FileAnalyzer analyzer = new FileAnalyzer();
            analyzer.getOutput();
            System.out.println("Файл output.txt успешно создан!");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}