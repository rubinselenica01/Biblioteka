package biblioteke;

import biblioteke.perdorues.Perdorues;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Mire se erdhe!");
            System.out.println("1. Log in");
            System.out.println("2. Regjistrohu");
            System.out.println("3. Dil");

            Scanner scanner = new Scanner(System.in);
            int zgjedhje;

            while (true) {
                try {
                    System.out.print("Vendos zgjedhjen: ");
                    zgjedhje = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Nuk eshte opsion valid!");
                }
            }

            switch (zgjedhje) {
                case 1:
                    Biblioteke.logIn();
                    break;
                case 2:
                    Perdorues perdorues = Biblioteke.regjistroPerdorues();
                    Biblioteke.veproMeMenu(perdorues);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
}
