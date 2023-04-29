package biblioteke;

import biblioteke.libra.Liber;
import biblioteke.libra.exception.*;
import biblioteke.libra.zhanera.Fantazi;
import biblioteke.libra.zhanera.Histori;
import biblioteke.libra.zhanera.Mister;
import biblioteke.libra.zhanera.Psikologji;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Perdorues implements Comparable<Perdorues> {
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

    @Override
    public String toString() {
        return "Emri: " + getEmer() + "\n"
                + "Mbiemri: " + getMbiemer() + "\n"
                + "Mosha: " + getMosha() + " vjec\n"
                + "ID BBLTK: " + getID_PERDORUES();
    }

    @Override
    public int compareTo(Perdorues perdorues) {
        //nese jane 2 emra dhe mbiemra te njejte, i krahason ata sipas ID me te re
        if ((this.getEmer() + this.getMbiemer()).equals(perdorues.getEmer() + perdorues.getMbiemer())) {
            return (this.getID_PERDORUES().compareTo(perdorues.getID_PERDORUES()));
        } else { //perndryshe i krahason ata sipas emrave/mbiemrave
            return (this.getEmer() + this.mbiemer).compareTo(perdorues.getEmer() + perdorues.getMbiemer());
        }
    }


    public void shtoLiber() {

        Liber liberPerTuShtuarNeListe = krijoObjektinLiber();

        vendosPershkrimePerLibrin(liberPerTuShtuarNeListe);

        Biblioteke.getListeLibrashGjendje().add(liberPerTuShtuarNeListe);

    }

    private Liber krijoObjektinLiber() {
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
                case "fantazi","1":
                    return new Fantazi();
                case "histori","2":
                    return new Histori();
                case "psikologji","3":
                    return new Psikologji();
                case "mister","4":
                    return new Mister();
                default:
                    System.out.println("Nuk ekziston nje zhaner i tille!");
            }

        }
    }

    private void vendosPershkrimePerLibrin(Liber liber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vendos detajet e librit: ");
        System.out.print("--Zhaneri i detajuar:");
        String zhaneriDetajuar = scanner.nextLine().trim();
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

        System.out.print("--Titulli: ");
        String titulli = scanner.nextLine().trim();
        liber.setTitull(titulli);

        String autori;
        //chekon nese ka numer te shkruar gabimisht tek emri i autorit
        while (true) {
            System.out.print("--Autori:");
            autori = scanner.nextLine().trim();
            try {
                for (int i = 0; i < autori.length(); ++i) {
                    if (Character.isDigit(autori.charAt(i))) {
                        throw new AutorException("Emer jo valid!");
                    }
                }
                liber.setAutor(autori);
                break;
            } catch (AutorException e) {
                System.out.println(e.getMessage());
            }
        }

        String ISBN;
        while (true) {
            try {
                System.out.print("--ISBN: ");
                ISBN = scanner.nextLine().trim();
                if (ISBN.length() != 17) {
                    throw new ISBNException("""
                            Gjatesia e ISBN tende nuk eshte e duhur!
                            Gjatesia e ISBN eshte fikse! Ajo eshte 13!""");
                }
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
                if (Integer.toString(vitBotim).length() != 4) {
                    System.out.println("Nuk eshte vit botimi!");
                } else {
                    liber.setVitBotim(vitBotim);
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Nuk eshte vit!");

            }
        }
    }

    public void kerkoNjeLiber() {
        String zgjedhje;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            while (true) {
                System.out.print("""
                        KERKO ME:
                        1. TITULL
                        2. AUTOR
                        3. ISBN
                        Vendos zgjedhjen tende:""");
                try {
                    zgjedhje = scanner.nextLine().trim();
                    break;
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Kujdes inputin!\n");
                }
            }

            switch (zgjedhje.toLowerCase()) {
                case "titull", "1":
                    System.out.print("Shkruaj titullin e librit:");
                    String titullKerkim = scanner.nextLine().trim();
                    for (Liber liberIterues : Biblioteke.getListeLibrashGjendje()) {
                        if (titullKerkim.equals(liberIterues.getTitull())) {
                            System.out.println("Libri me kete titull u gjet!");
                            System.out.println(liberIterues);
                            return;
                        }
                    }
                    System.out.println("Libri me kete titull nuk u gjet!");
                    return;
                case "autor", "2":
                    System.out.print("Shkruaj emrin e autorit:");
                    String autorKerkim = scanner.nextLine().trim();
                    for (Liber liberIterues : Biblioteke.getListeLibrashGjendje()) {
                        if (autorKerkim.equalsIgnoreCase(liberIterues.getAutor())) {
                            System.out.println("Libri i ketij autori u gjet!");
                            System.out.println(liberIterues);
                            return;
                        }
                    }
                    System.out.println("Libri me kete autor nuk u gjet!");
                    return;
                case "isbn", "3":
                    System.out.println("Shkruaj ISBN e librit:");
                    String isbnKerkimi = scanner.nextLine().trim();
                    for (Liber liberIterues : Biblioteke.getListeLibrashGjendje()) {
                        if (isbnKerkimi.equalsIgnoreCase(liberIterues.getISBN())) {
                            System.out.println("Libri me kete ISBN u gjet!");
                            System.out.println(liberIterues);
                            return;
                        }
                    }
                    System.out.println("Libri me kete ISBN nuk u gjet!");
                    return;
                default:
                    System.out.println("Vendos nje zgjedhje te duhur!");
            }
        }

        }



    public void fshiLiberNgaSistemi() {
        Scanner scanner = new Scanner(System.in);
        String ISBN;
        while (true) {
            try {
                System.out.print("--ISBN: ");
                ISBN = scanner.nextLine().trim();
                if (ISBN.length() != 17) {
                    throw new ISBNException("""
                            Gjatesia e ISBN tende nuk eshte e duhur!
                            Gjatesia e ISBN eshte fikse! Ajo eshte 13!""");
                }
                break;
            } catch (ISBNException e) {
                System.out.println(e.getMessage());
            }
        }
        for (Liber liberIterues : Biblioteke.getListeLibrashGjendje()) {
            if (liberIterues.getISBN().equals(ISBN)) {
                Biblioteke.getListeLibrashGjendje().remove(liberIterues);
                System.out.println("Libri me te dhenat e meposhtme u fshi me sukses!");
                System.out.println(liberIterues);
                return;
            }
        }
        System.out.println("Ky liber nuk u gjet!");
    }
}
