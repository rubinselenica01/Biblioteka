package biblioteke;

import biblioteke.exceptions.KontrollimTeDhenash;
import biblioteke.exceptions.NumberNotAllowed;
import biblioteke.libra.Liber;
import biblioteke.perdorues.Perdorues;

import java.util.*;

public class Biblioteke {

    private static Set<Liber> listeLibrashGjendje = new TreeSet<>();
    private static Set<Perdorues> listePerdoruesish = new TreeSet<>();
    private static Map<Perdorues, Set<Liber>> listaLibraveTeMarraBiblioteke = new HashMap<>();

    public Biblioteke() {
    }

    public static Set<Liber> getListeLibrashGjendje() {
        return listeLibrashGjendje;
    }

    public static Map<Perdorues, Set<Liber>> getListaLibraveTeMarraBiblioteke() {
        return listaLibraveTeMarraBiblioteke;
    }


    public static void logIn() {
        String emriKerkim;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Shkruaj emrin tend:");
                emriKerkim = KontrollimTeDhenash.kontrolloNumerNeString();
                break;
            } catch (NumberNotAllowed e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.print("Shkruaj ID tende:");
        String idKerkim;
        idKerkim = scanner.next().trim().toUpperCase();

        for (Perdorues perdorues : listePerdoruesish) {
            if (perdorues.getEmer().equals(emriKerkim) && perdorues.getID_PERDORUES().equals(idKerkim)) {
                veproMeMenu(perdorues);
                return;
            }
        }
        System.out.println("Perdoruesi nuk u gjet ne sistem!");
    }

    public static Perdorues regjistroPerdorues() {
        Scanner scanner = new Scanner(System.in);
        String emerKonstruktor;
        String mbiemerKonstruktor;
        int moshaKonstruktor;
        while (true) {
            try {
                System.out.print("Vendos emrin: ");
                emerKonstruktor = KontrollimTeDhenash.kontrolloNumerNeString();
                break;
            } catch (NumberNotAllowed e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Vendos mbiemrin: ");
                mbiemerKonstruktor = KontrollimTeDhenash.kontrolloNumerNeString();
                break;
            } catch (NumberNotAllowed e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Vendos moshen: ");
                moshaKonstruktor = scanner.nextInt();
                if (moshaKonstruktor < 13 || moshaKonstruktor > 95) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Moshe jo valide!");
            }
        }
        Perdorues perdorues = new Perdorues(emerKonstruktor, mbiemerKonstruktor, moshaKonstruktor);
        listePerdoruesish.add(perdorues);
        return perdorues;
    }

    public static void veproMeMenu(Perdorues perdorues) {
        System.out.println("**************************************************");
        System.out.println("* Mire se erdhe ne faqen kryesore te bibliotekes *");
        System.out.println("**************************************************\n");
        String pergjigje;
        do {
            mainPage();
            int zgjedhje;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Veprimi qe do te kryesh:");
                try {
                    zgjedhje = scanner.nextInt();
                    if (zgjedhje < 1 || zgjedhje > 8) {
                        throw new InputMismatchException();
                    }
                    break;
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Nuk eshte opsion valid");
                }
            }
            switch (zgjedhje) {
                case 1:
                    perdorues.shtoLiber();
                    break;
                case 2:
                    if (!kaGjendjeLibrash()) {
                        System.out.println("Nuk u gjet ky liber!");
                        break;
                    }
                    Liber liber = perdorues.kerkoNjeLiber();
                    if (liber != null) {
                        System.out.println("Libri u gjet!");
                        System.out.println(liber);
                    }
                    break;
                case 3:
                    if (!kaGjendjeLibrash()) {
                        break;
                    }
                    perdorues.fshiLiberNgaSistemiMeISBN();
                    break;
                case 4:
                    printoListeMeTitujLibrashGjendjeTeRenditur();
                    break;
                case 5:
                    if (!kaGjendjeLibrash()) {
                        break;
                    }
                    perdorues.shtoLiberNeRaftPersonal();
                    break;
                case 6:
                    if (!Biblioteke.listaLibraveTeMarraBiblioteke.containsKey(perdorues)) {
                        System.out.println("Nuk ke libra ne raftin tend!");
                        break;
                    }
                    perdorues.ktheLiber();
                    break;
                case 7:
                    return;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
            }

            System.out.println("Deshiron te vazhdosh perseri? PO / JO");
            pergjigje = scanner.next().trim();
        } while (pergjigje.equalsIgnoreCase("po"));
        System.out.println("Exiting...");
        System.exit(0);
    }

    private static void printoListeMeTitujLibrashGjendjeTeRenditur() {
        if (listeLibrashGjendje.isEmpty()) {
            System.out.println("Biblioteka s'ka libra!");
            return;
        }
        for (Liber liberIterues : listeLibrashGjendje) {
            System.out.println("-" + liberIterues.getTitull());
        }
    }

    private static void printoListenMeEmratPerdoruesTeRenditur() {
        for (Perdorues perdoruesIterues : listePerdoruesish) {
            System.out.println("-" + perdoruesIterues.getEmer() + " " + perdoruesIterues.getMbiemer());
        }
    }

    private static void printoListenMeTitujtLibraveTeMarre() {
        for (Map.Entry<Perdorues, Set<Liber>> s : listaLibraveTeMarraBiblioteke.entrySet()) {
            System.out.println("|" + s.getKey().getEmer() + " " + s.getKey().getMbiemer());
            System.out.println("Titujt ne raft:");
            for (Liber liberIterues : s.getValue()) {
                System.out.println("- " + liberIterues.getTitull());
            }
        }
    }

    private static void mainPage() {


        System.out.println("""
                            MENU
                ------------------------------------           
                1.  SHTO LIBER
                2.  KERKO NJE LIBER 
                3.  FSHI NJE LIBER
                4.  LISTO TE GJITHE TITUJT E LIBRAVE
                5.  MERR NJE LIBER
                6.  KTHE LIBRIN
                7.  LOG OUT
                8.  DIL
                ------------------------------------""");
    }

    private static boolean kaGjendjeLibrash() {
        if (Biblioteke.listeLibrashGjendje.isEmpty()) {
            System.out.println("Biblioteka s'ka libra!");
            return false;
        }
        return true;
    }
}
