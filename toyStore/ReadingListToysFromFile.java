package toyStore;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadingListToysFromFile {
    public List<Map> read() {
        List<Map> listToys = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("toys.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            listToys = (ArrayList) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Список игрушек еще не создан. Создайте список\n");
        }
        return listToys;
    }
}
