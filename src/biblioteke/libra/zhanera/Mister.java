package biblioteke.libra.zhanera;

import biblioteke.libra.Liber;


public class Mister extends Liber {
    private String llojiMister;
    public Mister(){

    }
    public Mister(String llojiMister, String titulli, String autor,
                  String ISBN, int vitBotim) {
        super(titulli, autor, ISBN, vitBotim);
        this.llojiMister = llojiMister;
    }

    public void setLlojiMister(String llojiMister) {
        this.llojiMister = llojiMister;
    }

    public String getLlojiMister() {
        return llojiMister;
    }

    public String toString() {
        return "Zhaneri: " + getLlojiMister() + super.toString();
    }
}
