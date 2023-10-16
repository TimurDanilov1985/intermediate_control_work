package toyStore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import  java.util.List;

public class RafflePrizes {
    private WritingListToysToFile writingListToysToFile;
    private Show show;

    public RafflePrizes(WritingListToysToFile writingListToysToFile, Show show) {
        this.writingListToysToFile = writingListToysToFile;
        this.show = show;
    }

    public String raffleToys(List<Map> playList) {
        Map<String,String> newToy;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String prize;
        Integer quantity;
        String quantityS;
        int j = 0;
        int num = (int) (Math.random() * 100);
        for (int i = 0; i < playList.size(); i++) {
            if (Integer.parseInt((String) playList.get(i).get("probability")) >= num) {
                newToy = playList.get(i);
                j = i;
                quantity = Integer.parseInt(newToy.get("quantity")) - 1;
                if (quantity == 0){
                    prize = String.format("Поздравляем! Вы Выиграли %s %s!", playList.get(j).get("name"), formatter.format(date));
                    show.showMessage(prize);
                    playList.remove(i);
                    writingListToysToFile.writing(playList);
                    return prize;
                } else {
                    quantityS = Integer.toString(quantity);
                    newToy.put("quantity", quantityS);
                    playList.set(i, newToy);
                    writingListToysToFile.writing(playList);
                    break;
                }
            }
        }
        if (j != 0) {
            prize = String.format("Поздравляем! Вы Выиграли %s %s!", playList.get(j).get("name"), formatter.format(date));
            show.showMessage(prize);
        } else {
            prize = "К сожалению вы ничего не выиграли. Попробуйте снова " + formatter.format(date);
            show.showMessage(prize);
        }
        return prize;
    }
}
