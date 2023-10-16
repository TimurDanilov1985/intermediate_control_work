package toyStore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WritingPrizesFile {
    public void writingToFile(String prize) throws IOException {
        try {
            FileWriter fw = new FileWriter("prizes.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(prize);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
