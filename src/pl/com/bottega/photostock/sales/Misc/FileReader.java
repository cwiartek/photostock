package pl.com.bottega.photostock.sales.Misc;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public static void main(String[] args) {
        fileWriter();
        fileReader();
    }

    private static void fileWriter() {
        try {
            try (OutputStream outputStream = new FileOutputStream("D:\\outputFile.txt");
                 PrintStream p = new PrintStream(outputStream)) {
                p.println("Ala ma psa \n");
                p.println("ala ala psa \n");
                p.println("ma \n");
                p.println("ma \n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileReader() {
        try {
            try (InputStream inputStream = new FileInputStream("D:\\outputFile.txt")) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                Map<String,Integer> outputMap = new HashMap<>();
                String line;
                while ((line = bufferedReader.readLine()) != null) {




                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void mapWrods() {
        Map<String,Integer> outputMap = new HashMap<>();

    }
}


