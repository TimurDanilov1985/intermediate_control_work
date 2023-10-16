package toyStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Map> listToys = new ArrayList<>();
        Show show = new Show();
        Input input = new Input();
        Validation validation = new Validation();
        ListingToys listingToys = new ListingToys();
        ReadingListToysFromFile readingListToysFromFile = new ReadingListToysFromFile();
        WritingListToysToFile writingListToysToFile = new WritingListToysToFile();
        EditingTheList editingTheList = new EditingTheList(show, input, validation, writingListToysToFile);
        RafflePrizes rafflePrizes = new RafflePrizes(writingListToysToFile, show);
        WritingPrizesFile writingPrizesFile = new WritingPrizesFile();
        show.showMessage("Здравствуйте!\n\nВас приветствует приложение разыгрывания призовых игрушек!\n");
        while(true) {
            show.showMessage("Введите:\n1 - Для создания нового списка игрушек\n2 - Загрузить старый список игрушек\n3 - Редактировать список\n" +
                    "4 - Разыграть призы");
            String par;
            while (true) {
                par = input.inputValue("Введите параметр меню: ");
                if (!validation.validationMenu(par)) {
                    show.showMessage("Парметр меню не корректен, необходимо ввести 1, 2, 3 или 4 соответственно меню. Введите параметр снова");
                } else {
                    break;
                }
            }
            switch (par) {
                case ("1"):
                    show.showMessage("Создание списка игрушек, которые участвуют в розыгрыше\n" +
                            "Для создания списка разыгрываемых игрушек необходимо ввести следующие параметры:\n");
                    Integer id;
                    String name;
                    String quantity;
                    String probability;
                    Integer quantityInt;
                    Integer probabilityInt;
                    String parameter;
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
                        if (listToys.size() == 0) {
                            id = 1;
                        } else {
                            id = listToys.size() + 1;
                        }
                        Map<String, String> toyInf = new HashMap<>();
                        Toy toy = new Toy(id, name, quantityInt, probabilityInt, toyInf);
                        toyInf = toy.mappingToy();
                        listToys = listingToys.AddingToList(listToys, toyInf);
                        show.showMessage("Введите \"1\" для введения параметров следующей игрушки\nВведите \"0\" для завершения формирования списка " +
                                "разыгрываемых игрушек");
                        parameter = input.inputValue("Введите число: ");
                        if (!parameter.equals("1")) {
                            break;
                        }
                    }
                    show.showMessage("Создан список разыгрываемых игрушек:");
                    for (int i = 0; i < listToys.size(); i++) {
                        System.out.println("id: " + listToys.get(i).get("id") + " name: " + listToys.get(i).get("name") +
                                " quantity: " + listToys.get(i).get("quantity") + " probability: " + listToys.get(i).get("probability"));
                    }
                    writingListToysToFile.writing(listToys);
                    break;
                case ("2"):
                    List<Map> listLoad;
                    listLoad = readingListToysFromFile.read();
                    if (listLoad.size() != 0) {
                        System.out.println();
                        show.showMessage("Загружен список разыгрываемых игрушек:");
                        for (int i = 0; i < listLoad.size(); i++) {
                            System.out.println("id: " + listLoad.get(i).get("id") + " name: " + listLoad.get(i).get("name") +
                                    " quantity: " + listLoad.get(i).get("quantity") + " probability: " + listLoad.get(i).get("probability"));
                        }
                    }
                    break;
                case ("3"):
                    List<Map> listOld;
                    listOld = readingListToysFromFile.read();
                    if (listOld.size() != 0) {
                        editingTheList.editing(listOld);
                    }
                    break;
                case ("4"):
                    List<Map> playList;
                    playList = readingListToysFromFile.read();
                    if (playList.size() != 0) {
                        String prize = rafflePrizes.raffleToys(playList);
                        writingPrizesFile.writingToFile(prize);
                    }
                    break;
                default:
                    break;
            }
            show.showMessage("Введите \"1\" чтобы продолжить\nВведите \"0\" для выхода");
            String num = input.inputValue("Введите число: ");
            if (!num.equals("1")) {
                break;
            }
        }
    }
}
