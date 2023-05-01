package biblioteke.perdorues;

import biblioteke.exceptions.DetajeLibriExceptions;
import biblioteke.exceptions.ISBNException;
import biblioteke.exceptions.NumberNotAllowed;
import biblioteke.libra.Liber;
import biblioteke.libra.zhanera.Fantazi;
import biblioteke.libra.zhanera.Histori;
import biblioteke.libra.zhanera.Mister;
import biblioteke.libra.zhanera.Psikologji;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface InterfacePerdoruesi {

    default Liber krijoObjektinLiber() {
        //pyet perdoruesin se cfare libri do te shtoje dhe rikthen opsionin e zgjedhur
        Scanner scanner = new Scanner(System.in);
        String pergjigjeZhaner;
        while (true) {
            System.out.print("""
                    Mundesite per te shtuar liber:
                    1 -> Fantazi
                    2 -> Histori
                    3 -> Psikologji
                    4 -> Mister
                    Shkruaj ketu: """);
            pergjigjeZhaner = scanner.nextLine().trim();

            switch (pergjigjeZhaner.toLowerCase()) {
                case "fantazi", "1":
                    return new Fantazi();
                case "histori", "2":
                    return new Histori();
                case "psikologji", "3":
                    return new Psikologji();
                case "mister", "4":
                    return new Mister();
                default:
                    System.out.println("Nuk ekziston nje zhaner i tille!");
            }

        }
    }

    default void vendosPershkrimePerLibrin(Liber liber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vendos detajet e librit: ");
        while (true) {
            System.out.print("--Zhaneri i detajuar:");
            String zhaneriDetajuar = scanner.nextLine().trim();
            try {
                DetajeLibriExceptions.permbanNumer(zhaneriDetajuar);
                //kontrollon se ciles klase i perket objekti dhe
                //ne baze te kontrollit perdor metoden perkatese per klasen
                if (liber instanceof Histori) {
                    ((Histori) liber).setLlojiHistorise(zhaneriDetajuar);

                } else if (liber instanceof Psikologji) {
                    ((Psikologji) liber).setLlojiPsikologji(zhaneriDetajuar);

                } else if (liber instanceof Mister) {
                    ((Mister) liber).setLlojiMister(zhaneriDetajuar);

                } else if (liber instanceof Fantazi) {
                    ((Fantazi) liber).setLlojiFantazi(zhaneriDetajuar);
                }
                break;
            } catch (NumberNotAllowed e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("--Titulli: ");
        String titulli = scanner.nextLine().trim();
        liber.setTitull(titulli);

        String autori;
        //chekon nese ka numer te shkruar gabimisht tek emri i autorit
        while (true) {
            System.out.print("--Autori:");
            autori = scanner.nextLine().trim();
            try {
                DetajeLibriExceptions.permbanNumer(autori);
                liber.setAutor(autori);
                break;
            } catch (NumberNotAllowed e) {
                System.out.println(e.getMessage());
            }
        }

        String ISBN;
        while (true) {
            try {
                System.out.print("--ISBN: ");
                ISBN = scanner.nextLine().trim();
                DetajeLibriExceptions.kontrolloISBN(ISBN);
                liber.setISBN(ISBN);
                break;
            } catch (ISBNException e) {
                System.out.println(e.getMessage());
            }
        }
        int vitBotim;
        //chekon nese inputi eshte numer
        //chekon nese eshte vit valid
        while (true) {
            try {
                System.out.print("--Vitbotimi: ");
                vitBotim = scanner.nextInt();
                DetajeLibriExceptions.kontrolloVitinBotimit(vitBotim);
                liber.setVitBotim(vitBotim);
                break;

            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(e.getMessage());

            }
        }
    }

}
