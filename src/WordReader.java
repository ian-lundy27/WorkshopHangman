import java.io.*;
import java.util.ArrayList;
public class WordReader {

    public static ArrayList<String> readWordFile(String path) {

        try {

            ArrayList<String> words = new ArrayList<>();

            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {
                words.add(line);
            }

            br.close();

            return words;

        } catch (Exception e) {

            return null;

        }

    }

}
