package biblioteke.libra.zhanera;

import biblioteke.libra.Liber;

public class Histori extends Liber {
    private String llojiHistorise;
    public Histori(){

    }
    public Histori(String llojiHistorise, String titull, String autor, String ISBN,
                   int vitBotim) {
        super(titull, autor, ISBN, vitBotim);
        this.llojiHistorise = llojiHistorise;
    }

    public void setLlojiHistorise(String llojiHistorise) {
        this.llojiHistorise = llojiHistorise;
    }

    public String getLlojiHistorise() {
        return llojiHistorise;
    }

    public String toString() {
        return "Zhaneri: " + getLlojiHistorise() + super.toString();
    }
}
