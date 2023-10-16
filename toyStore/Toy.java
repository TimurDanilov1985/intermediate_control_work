package toyStore;

import java.util.Map;

public class Toy {
    private Integer id;
    private String name;
    private Integer quantity;
    private Integer probability;
    private Map<String, String> toyInf;

    public Toy(Integer id, String name, Integer quantity, Integer probability, Map<String, String> toyInf) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.probability = probability;
        this.toyInf = toyInf;
    }
    public Map<String, String> mappingToy() {
        toyInf.put("id", id.toString());
        toyInf.put("name", name);
        toyInf.put("quantity", quantity.toString());
        toyInf.put("probability", probability.toString());
        return toyInf;
    }
}
