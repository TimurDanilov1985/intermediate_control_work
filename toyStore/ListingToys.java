package toyStore;

import java.util.List;
import java.util.Map;

public class ListingToys {
    public List<Map> AddingToList(List<Map> listToys, Map<String,String> toyInf) {
        listToys.add(toyInf);
        return listToys;
    }
}
