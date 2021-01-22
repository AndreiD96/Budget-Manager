
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();
        StateManager state = new StateManager();
        int n;
        state.createFile();
        do {
            menu.showMenu();
            n = Integer.parseInt(input.nextLine());
            menu.menuInput(n);
        } while (n != 0);
    }
}
