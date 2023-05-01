package biblioteke;

import biblioteke.libra.Liber;
import biblioteke.perdorues.Perdorues;

import java.util.*;

/**
 * siç është çfarë të bëni nëse një përdorues provon të marre një libër që është marre tashmë nga dikush tjeter.
 */
public class Biblioteke {
    private String emriBiblioteke;
    /*
    Tek metoda e kerkimit te librit shto menyrat e kerkimit te librit
     */
    private static Set<Liber> listeLibrashGjendje = new TreeSet<>();
    private static Set<Perdorues> listePerdoruesish = new TreeSet<>();
    private static Map<Perdorues, Set<Liber>> listaLibraveTeMarraBiblioteke = new HashMap<>();

    public Biblioteke(String emriBiblioteke) {
        this.emriBiblioteke = emriBiblioteke;
    }

    public static Set<Liber> getListeLibrashGjendje() {
        return listeLibrashGjendje;
    }

    public static Map<Perdorues, Set<Liber>> getListaLibraveTeMarraBiblioteke() {
        return listaLibraveTeMarraBiblioteke;
    }

    public static void main(String[] args) {
        Perdorues perdorues = new Perdorues("Rubin", "Selenica", 21);
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
                    if (zgjedhje < 1 || zgjedhje > 7) {
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
                    if(!kaGjendjeLibrash()) {
                        break;
                    }
                    Liber liber = perdorues.kerkoNjeLiber();
                    if (liber != null) {
                        System.out.println(liber);
                    }
                    break;
                case 3:
                    if(!kaGjendjeLibrash()) {
                        break;
                    }
                    perdorues.fshiLiberNgaSistemiMeISBN();
                    break;
                case 4:
                    printoListeMeTitujLibrashGjendjeTeRenditur();
                    break;
                case 5:
                    if(!kaGjendjeLibrash()) {
                        break;
                    }
                    perdorues.shtoLiberNeRaftPersonal();
                    break;
                case 6:
                    if (perdorues.getListeMeLibraTeMarra().isEmpty()) {
                        System.out.println("Nuk ke libra ne biblioteken tende!");
                        break;
                    }
                    perdorues.ktheLiber();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
            }

            System.out.println("Deshiron te vazhdosh perseri? PO / JO");
            pergjigje = scanner.next().trim();
        } while (pergjigje.equalsIgnoreCase("po"));
        System.out.println("Exiting...");
    }


    public static void printoListeMeTitujLibrashGjendjeTeRenditur() {
        if (listeLibrashGjendje.isEmpty()) {
            System.out.println("Biblioteka s'ka libra!");
            return;
        }
        for (Liber liberIterues : listeLibrashGjendje) {
            System.out.println("-" + liberIterues.getTitull());
        }
    }

    public static void printoListenMeEmratPerdoruesTeRenditur() {
        for (Perdorues perdoruesIterues : listePerdoruesish) {
            System.out.println("-" + perdoruesIterues.getEmer() + " " + perdoruesIterues.getMbiemer());
        }
    }

    public static void printoListenMeTitujtLibraveTeMarre() {
        for (Map.Entry<Perdorues, Set<Liber>> s : listaLibraveTeMarraBiblioteke.entrySet()) {
            System.out.println("|" + s.getKey().getEmer() + " " + s.getKey().getMbiemer());
            System.out.println("Titujt ne raft:");
            for (Liber liberIterues : s.getValue()) {
                System.out.println("- " + liberIterues.getTitull());
            }
        }
    }

    public static void mainPage() {


        System.out.println("""
                            MENU
                ------------------------------------           
                1.  SHTO LIBER
                2.  KERKO NJE LIBER NE GJENDJE
                3.  FSHI NJE LIBER
                4.  LISTO TE GJITHE TITUJT E LIBRAVE
                5.  MERR NJE LIBER
                6.  KTHE LIBRIN
                7.  DIL
                ------------------------------------""");
    }
 public static boolean kaGjendjeLibrash(){
        if (Biblioteke.listeLibrashGjendje.isEmpty()) {
            System.out.println("Biblioteka s'ka libra!");
            return false;
        }
        return true;
    }
}
