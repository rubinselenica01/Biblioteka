package biblioteke;

import biblioteke.libra.Liber;

import java.util.Set;
import java.util.TreeSet;

/**
 * Krijoni një sistem menaxhimi biblioteke duke përdorur konceptet OOP në Java. Biblioteka duhet të ketë funksionalitetin e mëposhtëm:
 * <p>
 * Shtoni një libër në bibliotekë: Përdoruesi duhet të jetë në gjendje të shtojë një libër në bibliotekë duke dhënë titullin e librit,
 * autorin, ISBN dhe vitin e botimit.
 * <p>
 * Fshij një libër nga biblioteka: Përdoruesi duhet të jetë në gjendje të fshijë një libër nga biblioteka duke dhënë ISBN e librit.
 * <p>
 * Kërkoni një libër: Përdoruesi duhet të jetë në gjendje të kërkojë një libër duke dhënë titullin, autorin ose ISBN e librit.
 * Shfaqni të gjithë librat: Përdoruesi duhet të jetë në gjendje të shohë një listë të të gjithë librave në bibliotekë,
 * të renditur alfabetikisht sipas titullit.
 * <p>
 * Merrni një libër: Përdoruesi duhet të jetë në gjendje të marre një libër duke dhënë ISBN e librit.
 * Sistemi duhet të mbajë gjurmë të librave që janë marre dhe kush i ka marre ato.
 * <p>
 * Kthe librin: Përdoruesi duhet të jetë në gjendje të kthejë një libër duke dhënë ISBN e librit.
 * Sistemi duhet të përditësojë statusin e librit për të treguar se është në dispozicion për tu marre serish.
 * <p>
 * Ju duhet të krijoni klasa dhe metoda të përshtatshme për të zbatuar këtë sistem biblioteke duke përdorur konceptet e OOP
 * si mbivendosje, trashëgim, dhe polimorfizëm. Ju gjithashtu duhet të konsideroni se si të trajtoni gabime dhe raste kufizuese,
 * siç është çfarë të bëni nëse një përdorues provon të marre një libër që është marre tashmë nga dikush tjeter.
 */
public class Biblioteke {
    private String emriBiblioteke;
    /*
    Tek metoda e kerkimit te librit shto menyrat e kerkimit te librit
     */
    private static Set<Liber> listeLibrashGjendje = new TreeSet<>();
    private static Set<Perdorues> listePerdoruesish = new TreeSet<>();

    public static Set<Liber> getListeLibrashGjendje() {
        return listeLibrashGjendje;
    }

    public static void main(String[] args) {
        Perdorues rubini = new Perdorues("Rubin", "Selenica", 21);
//        listeLibrashGjendje.add(new Fantazi("Fantazi","Tomka dhe shoket e tij","1-2-12-4","sdas",21));
//
//        listeLibrashGjendje.add(new Fantazi("Fantazi","Djemte e rruges 'Pal'","1-2-12-4","sdas",21));
//        listeLibrashGjendje.add(new Fantazi("Fantazi","Mesnik Vokshi","1-2-12-4","sdas",21));
//        printoListeMeTitujLibrashTeRenditur();
//            listePerdoruesish.add(new Perdorues("Demir","Selenica",21));
//            listePerdoruesish.add(new Perdorues("Demir","Selenicb",43));
//            listePerdoruesish.add(new Perdorues("Rubin","Selenica",43));
//            listePerdoruesish.add(new Perdorues("Demir","Seleniac",21));
//            printoListenMeEmratPerdoruesTeRenditur();
        rubini.shtoLiber();

    }
    public static void printoListeMeTitujLibrashTeRenditur(){
        for(Liber liberIterues : listeLibrashGjendje){
            System.out.println("-"+liberIterues.getTitull());
        }
    }
    public static void printoListenMeEmratPerdoruesTeRenditur(){
        for(Perdorues perdoruesIterues : listePerdoruesish){
            System.out.println("-"+perdoruesIterues.getEmer()+" "+perdoruesIterues.getMbiemer());
        }
    }
    public static void mainPage(){
        System.out.println("**************************************************");
        System.out.println("* Mire se erdhe ne faqen kryesore te bibliotekes *");
        System.out.println("**************************************************\n");
        System.out.println("        MENU");
        System.out.println("""
                1.  SHTO LIBER
                2.  KERKO NJE LIBER NE GJENDJE
                3.  FSHI NJE LIBER
                4.  LISTO TE GJITHE TITUJT E LIBRAVE
                5.  MERR NJE LIBER
                6.  KTHE LIBRIN""");
    }

}
