package biblioteke.perdorues;

import biblioteke.Biblioteke;
import biblioteke.exceptions.ISBNException;
import biblioteke.libra.Liber;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Perdorues implements Comparable<Perdorues>, InterfacePerdoruesi {
    private String emer;
    private String mbiemer;
    private int mosha;
    private final String ID_PERDORUES;
    private static int nrPerdoruesash = 0;
    private Set<Liber> listeMeLibraTeMarra = new TreeSet<>();

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

    public Set<Liber> getListeMeLibraTeMarra() {
        return listeMeLibraTeMarra;
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


    public Liber kerkoNjeLiber() {
        Liber liberPerRikthim;
        String zgjedhje;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            
                System.out.print("""
                        KERKO ME:
                        1. TITULL
                        2. AUTOR
                        3. ISBN
                        Vendos zgjedhjen tende:""");

                    zgjedhje = scanner.nextLine().trim();



            switch (zgjedhje.toLowerCase()) {
                case "titull", "1":
                    System.out.print("Shkruaj titullin e librit:");
                    String titullKerkim = scanner.nextLine().trim();
                    for (Liber liberIterues : Biblioteke.getListeLibrashGjendje()) {
                        if (titullKerkim.equals(liberIterues.getTitull())) {
                            System.out.println("Libri me kete titull u gjet!");
                            return liberIterues;
                        }
                    }
                    System.out.println("Titull libri jo valid!");
                    return null;
                case "autor", "2":
                    System.out.print("Shkruaj emrin e autorit:");
                    String autorKerkim = scanner.nextLine().trim();
                    for (Liber liberIterues : Biblioteke.getListeLibrashGjendje()) {
                        if (autorKerkim.equalsIgnoreCase(liberIterues.getAutor())) {
                            System.out.println("Libri i ketij autori u gjet!");
                            return liberIterues;
                        }
                    }
                    System.out.println("Autor jo valid!");
                    return null;
                case "isbn", "3":
                    System.out.print("Shkruaj ISBN e librit:");
                    String isbnKerkimi = scanner.nextLine().trim();
                    for (Liber liberIterues : Biblioteke.getListeLibrashGjendje()) {
                        if (isbnKerkimi.equalsIgnoreCase(liberIterues.getISBN())) {
                            System.out.println("Libri me kete ISBN u gjet!");
                            return liberIterues;
                        }
                    }
                    System.out.println("ISBN jo valid!");
                    return null;
                default:
                    System.out.println("Vendos nje zgjedhje te duhur!");
            }
        }

    }

    public void fshiLiberNgaSistemiMeISBN() {
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

    public void shtoLiberNeRaftPersonal() {
        Liber liberRikthim = kerkoNjeLiber();
        if (liberRikthim == null) {
            System.out.println("Nuk u gjet ky liber!");
            return;
        }

        if (!Biblioteke.getListaLibraveTeMarraBiblioteke().containsKey(this)) {
            Biblioteke.getListaLibraveTeMarraBiblioteke().put(this, Set.of(liberRikthim));
        } else {
            Biblioteke.getListaLibraveTeMarraBiblioteke().get(this).add(liberRikthim);
        }
        Biblioteke.getListeLibrashGjendje().remove(liberRikthim);
        getListeMeLibraTeMarra().add(liberRikthim);
        System.out.println(this.emer + " shtoi \"" + liberRikthim.getTitull() + "\" ne biblioteken e tij!");
    }

    public void ktheLiber() {
        System.out.println("Cilin liber do te kthesh?");
        Scanner scanner = new Scanner(System.in);
        String titullLibri;
        int i = 1;
        System.out.println("Librat qe jane ne raftin tend:");
        for (Liber liberIterues : listeMeLibraTeMarra) {
            System.out.println(i + ". " + liberIterues.getTitull());
            ++i;
        }
        System.out.print("Jep titullin: ");
        titullLibri = scanner.nextLine().trim();

        for (Liber liberIterues : listeMeLibraTeMarra) {
            if (liberIterues.getTitull().equalsIgnoreCase(titullLibri)) {
                Biblioteke.getListeLibrashGjendje().add(liberIterues);
                listeMeLibraTeMarra.remove(liberIterues);
                System.out.println(liberIterues.getTitull() + " u kthye ne bibloteke!");
                return;
            }
        }
        System.out.println("Ky titull libri nuk u gjet!");
    }
}
