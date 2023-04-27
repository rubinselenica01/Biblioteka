package biblioteke;

import biblioteke.libra.Liber;
import biblioteke.libra.zhanera.Fantazi;
import biblioteke.libra.zhanera.Histori;
import biblioteke.libra.zhanera.Mister;
import biblioteke.libra.zhanera.Psikologji;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Perdorues {
    private String emer;
    private String mbiemer;
    private int mosha;
    private final String ID_PERDORUES;
    private static int nrPerdoruesash = 0;

    public Perdorues(String emer, String mbiemer, int mosha) {
        this.emer = emer;
        this.mbiemer = mbiemer;
        this.mosha = mosha;
        ID_PERDORUES = "BBLTK0000" + (++nrPerdoruesash);
    }

    private void setEmer(String emer) {
        this.emer = emer;
    }

    public String getEmer() {
        return emer;
    }

    public void setMbiemer(String mbiemer) {
        this.mbiemer = mbiemer;
    }

    public String getMbiemer() {
        return mbiemer;
    }

    public void setMosha(int mosha) {
        this.mosha = mosha;
    }

    public int getMosha() {
        return mosha;
    }

    public String getID_PERDORUES() {
        return this.ID_PERDORUES;
    }

    private int merrPergjigje() {
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
            pergjigjeZhaner = scanner.nextLine();

            switch (pergjigjeZhaner.toLowerCase()) {
                case "fantazi":
                    return 1;
                case "histori":
                    return 2;
                case "psikologji":
                    return 3;
                case "mister":
                    return 4;
                default:
                    System.out.println("Nuk ekziston nje zhaner i tille!");
            }

        }
    }

    public Liber shtoLiber() {
        int numer = merrPergjigje();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vendos detajet e librit: ");
        System.out.print("--Zhaneri i detajuar:");
        String zhaneriDetajuar = scanner.nextLine();

        System.out.print("--Titulli: ");
        String titulli = scanner.nextLine();

        String autori;

        //chekon nese ka numer te shkruar gabimisht tek emri i autorit
        while (true) {
            System.out.print("--Autori:");
            autori = scanner.nextLine();
            try {
                for (int i = 0; i < autori.length(); ++i) {
                    if (Character.isDigit(autori.charAt(i))) {
                        throw new InputMismatchException("Emer jo valid!");
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("--ISBN: ");
        String ISBN = scanner.nextLine().trim();


        int vitBotim;
        //chekon nese inputi eshte numer
        //chekon nese eshte vit valid
        while (true) {
            try {
                System.out.print("--Vitbotimi: ");
                vitBotim = scanner.nextInt();
                if (Integer.toString(vitBotim).length() != 4) {
                    System.out.println("Nuk eshte vit botimi!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Nuk eshte vit!");

            }
        }
        Liber liberKthimMetode = null;
        switch (numer) {
            case 1:
                liberKthimMetode = new Fantazi(zhaneriDetajuar, titulli, autori, ISBN, vitBotim);
                break;
            case 2:
                liberKthimMetode = new Histori(zhaneriDetajuar, titulli, autori, ISBN, vitBotim);
                break;
            case 3:
                liberKthimMetode = new Psikologji(zhaneriDetajuar, titulli, autori, ISBN, vitBotim);
                break;
            case 4:
                liberKthimMetode = new Mister(zhaneriDetajuar, titulli, autori, ISBN, vitBotim);
                break;
            default:
        }
        return liberKthimMetode;
    }
}
