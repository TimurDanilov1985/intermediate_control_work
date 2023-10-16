package toyStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditingTheList {
    private Show show;
    private Input input;
    private Validation validation;
    private WritingListToysToFile writingListToysToFile;

    public EditingTheList(Show show, Input input, Validation validation, WritingListToysToFile writingListToysToFile) {
        this.show = show;
        this.input = input;
        this.validation = validation;
        this.writingListToysToFile = writingListToysToFile;
    }

    public void editing(List<Map> oldList) {
        String id;
        String id1;
        Integer newId;
        String name;
        String quantity;
        String probability;
        Integer quantityInt;
        Integer probabilityInt;
        String par;
        String parameter;
        int a = 0;
        show.showMessage("Загружен список:");
        for (int i = 0; i < oldList.size(); i++) {
            System.out.println("id: " + oldList.get(i).get("id") + " name: " + oldList.get(i).get("name") +
                    " quantity: " + oldList.get(i).get("quantity") + " probability: " + oldList.get(i).get("probability"));
        }
        while (true) {
            show.showMessage("Введите \"1\" для редактирования элемента списка по id\nВведите \"2\" для добавления нового элемента " +
                    "в список\nВведите \"3\" для удаления элемента из списка по id");
            par = input.inputValue("Введите параметр: ");
            switch (par) {
                case ("1"):
                    show.showMessage("Введите id элемента списка для редактирования");
                    id = input.inputValue("Введите id: ");
                    if (!validation.validationId(id)) {
                        show.showMessage("Введите целое число соответствующее номеру элемента в списке");
                    } else {
                        for (int i = 0; i < oldList.size(); i++) {
                            if (oldList.get(i).get("id").equals(id)) {
                                a = i;
                                break;
                            }
                        }
                        if (a == 0) {
                            show.showMessage("Нет элемента в списке под таким id. Введите id снова");
                        } else {
                            while (true) {
                                name = input.inputValue("Введите название игрушки: ");
                                if (!validation.validationName(name)) {
                                    show.showMessage("Название не может быть пустой строкой, введите название снова");
                                } else {
                                    break;
                                }
                            }
                            while (true) {
                                quantity = input.inputValue("Введите количество игрушек данного типа (целое число): ");
                                if (!validation.validationQuantity(quantity)) {
                                    show.showMessage("Введенный параметр должен быть целым числом. Введите параметр снова");
                                } else {
                                    quantityInt = Integer.parseInt(quantity);
                                    break;
                                }
                            }
                            while (true) {
                                probability = input.inputValue("Введите вероятность выпадения игрушки при розыгрыше (целое число от 1 до 100): ");
                                if (!validation.validationProbability(probability)) {
                                    show.showMessage("Введенный параметр должен быть целым числом от 1 до 100. Введите параметр снова");
                                } else {
                                    probabilityInt = Integer.parseInt(probability);
                                    break;
                                }
                            }
                            newId = Integer.parseInt(id);
                            Map<String, String> toyInf = new HashMap<>();
                            Toy toy = new Toy(newId, name, quantityInt, probabilityInt, toyInf);
                            toyInf = toy.mappingToy();
                            oldList.set(a, toyInf);
                            writingListToysToFile.writing(oldList);
                            show.showMessage("Новый спиок");
                            for (a = 0; a < oldList.size(); a++) {
                                System.out.println("id: " + oldList.get(a).get("id") + " name: " + oldList.get(a).get("name") +
                                        " quantity: " + oldList.get(a).get("quantity") + " probability: " + oldList.get(a).get("probability"));
                            }
                        }
                    }
                    break;
                case ("2"):
                    while (true) {
                        while (true) {
                            name = input.inputValue("Введите название игрушки: ");
                            if (!validation.validationName(name)) {
                                show.showMessage("Название не может быть пустой строкой, введите название снова");
                            } else {
                                break;
                            }
                        }
                        while (true) {
                            quantity = input.inputValue("Введите количество игрушек данного типа (целое число): ");
                            if (!validation.validationQuantity(quantity)) {
                                show.showMessage("Введенный параметр должен быть целым числом. Введите параметр снова");
                            } else {
                                quantityInt = Integer.parseInt(quantity);
                                break;
                            }
                        }
                        while (true) {
                            probability = input.inputValue("Введите вероятность выпадения игрушки при розыгрыше (целое число от 1 до 100): ");
                            if (!validation.validationProbability(probability)) {
                                show.showMessage("Введенный параметр должен быть целым числом от 1 до 100. Введите параметр снова");
                            } else {
                                probabilityInt = Integer.parseInt(probability);
                                break;
                            }
                        }
                        int maxID = Integer.parseInt((String) oldList.get(0).get("id"));
                        for (int i = 1; i < oldList.size(); i++) {
                            if (Integer.parseInt((String) oldList.get(i).get("id")) > maxID) {
                                maxID = Integer.parseInt((String) oldList.get(i).get("id"));
                            }
                        }
                        newId = maxID + 1;
                        Map<String, String> toyInf = new HashMap<>();
                        Toy toy = new Toy(newId, name, quantityInt, probabilityInt, toyInf);
                        toyInf = toy.mappingToy();
                        oldList.add(toyInf);
                        writingListToysToFile.writing(oldList);
                        show.showMessage("Новый спиок");
                        for (a = 0; a < oldList.size(); a++) {
                            System.out.println("id: " + oldList.get(a).get("id") + " name: " + oldList.get(a).get("name") +
                                    " quantity: " + oldList.get(a).get("quantity") + " probability: " + oldList.get(a).get("probability"));
                        }
                        show.showMessage("Введите \"1\" для введения параметров следующей игрушки\nВведите \"0\" для завершения формирования списка " +
                                "разыгрываемых игрушек");
                        parameter = input.inputValue("Введите число: ");
                        if (!parameter.equals("1")) {
                            break;
                        }
                    }
                    break;
                case ("3"):
                    show.showMessage("Введите id элемента списка для редактирования");
                    id1 = input.inputValue("Введите id: ");
                    if (!validation.validationId(id1)) {
                        show.showMessage("Введите целое число соответствующее номеру элемента в списке");
                    } else {
                        for (int i = 0; i < oldList.size(); i++) {
                            if (oldList.get(i).get("id").equals(id1)) {
                                a = i;
                                break;
                            }
                        }
                        if (a == 0) {
                            show.showMessage("Нет элемента в списке под таким id. Введите id снова");
                        } else {
                            oldList.remove(a);
                            writingListToysToFile.writing(oldList);
                            show.showMessage("Новый спиок");
                            for (a = 0; a < oldList.size(); a++) {
                                System.out.println("id: " + oldList.get(a).get("id") + " name: " + oldList.get(a).get("name") +
                                        " quantity: " + oldList.get(a).get("quantity") + " probability: " + oldList.get(a).get("probability"));
                            }
                        }
                    }
                    break;
                default:
                    show.showMessage("Введите корректный параметр в соответсвии с меню");
                    break;
            }
            show.showMessage("Для дальнейшего редактирования списка введите \"1\"\nДля завершения редактирования списка введите \"0\"");
            parameter = input.inputValue("Введите параметр: ");
            if (!parameter.equals("1")) {
                break;
            }

        }
    }
}
