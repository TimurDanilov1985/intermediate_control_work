package toyStore;

import java.util.Scanner;

public class Input {
    public String inputValue(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        String value = scanner.nextLine();
        return value;
    }
}
