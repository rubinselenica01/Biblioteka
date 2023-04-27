package biblioteke.libra.zhanera;

import biblioteke.libra.Liber;

public class Psikologji extends Liber {
    private String llojiPsikologji;

    public Psikologji(String llojiPsikologji, String titull, String autor,
                      String ISBN, int vitBotim) {
        super(titull, autor, ISBN, vitBotim);
        this.llojiPsikologji = llojiPsikologji;
    }

    public void setLlojiPsikologji(String llojiPsikologji) {
        this.llojiPsikologji = llojiPsikologji;
    }

    public String getLlojiPsikologji() {
        return llojiPsikologji;
    }

    public String toString() {
        return "Zhaneri: " + getLlojiPsikologji() + super.toString();
    }
}
