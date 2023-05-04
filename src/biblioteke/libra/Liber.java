package biblioteke.libra;

public abstract class Liber implements Comparable<Liber> {
    private String titull;
    private String autor;
    private String ISBN;
    private int vitBotim;

    public Liber() {

    }

    public Liber(String titull, String autor, String ISBN, int vitBotim) {
        this.titull = titull;
        this.autor = autor;
        this.ISBN = ISBN;
        this.vitBotim = vitBotim;
    }

    public void setTitull(String titull) {
        this.titull = titull;
    }

    public String getTitull() {
        return titull;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setVitBotim(int vitBotim) {
        this.vitBotim = vitBotim;
    }

    public int getVitBotim() {
        return vitBotim;
    }

    public String toString() {
        return "\nTitulli i librit: " + getTitull() +
                "\nAutor: " + getAutor() +
                "\nISBN: " + getISBN() +
                "\nViti i botimit: " + getVitBotim();
    }

    public int compareTo(Liber other) {
        return this.titull.compareTo(other.titull);
    }

}
