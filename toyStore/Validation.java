package toyStore;

public class Validation {
    public boolean validationName(String name) {
        if (name == null) {
            return false;
        }
        return true;
    }
    public boolean validationQuantity(String quantity) {
        if (quantity == null) {
            return false;
        }
        try {
            Integer.parseInt(quantity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean validationProbability(String probability) {
        Integer p;
        if (probability == null) {
            return false;
        }
        try {
            p = Integer.parseInt(probability);
            if (p > 0 && p <= 100) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    public boolean validationMenu(String menu) {
        if(menu.equals("1") | menu.equals("2") | menu.equals("3") | menu.equals("4")) {
            return true;
        }
        return false;
    }
    public boolean validationId(String id) {
        if (id == null) {
            return false;
        }
        try {
            Integer.parseInt(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
