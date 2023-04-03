
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        try (Reader fileReader = new FileReader("C:\\Users\\ssabi\\Desktop\\Task\\Анализ преступлений\\Crimes.csv");
             Scanner scr = new Scanner(fileReader);
             ) {

            Map<String, Integer> crimeType = new HashMap<>();
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy");
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
            scr.nextLine();

            while (scr.hasNextLine()) {
                String[] array = scr.nextLine().split(",");
                Date crimeDate = formatter2.parse(array[2]);
                if (formatter1.format(crimeDate.getTime()).equals("2006")) {
                    Integer count = crimeType.get(array[5]);
                    crimeType.put(array[5], count == null ? 1 : count + 1);
                }

            }

            Map.Entry<String, Integer> maxEntry = null;

            for (Map.Entry<String, Integer> entry : crimeType.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
                if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                    maxEntry = entry;
                }
            }

            System.out.println("The most common crime is " + maxEntry.getKey() + ", it was committed " + maxEntry.getValue() + " times");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}