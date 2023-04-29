package biblioteke.libra.zhanera;

import biblioteke.libra.Liber;

public class Fantazi extends Liber {
    private String llojiFantazi;

    public Fantazi(){
        super();
    }

    public Fantazi(String llojiFantazi, String titull, String autor,
                   String ISBN, int vitBotim) {
        super(titull, autor, ISBN, vitBotim);
        this.llojiFantazi = llojiFantazi;
    }

    public void setLlojiFantazi(String llojiFantazi) {
        this.llojiFantazi = llojiFantazi;
    }

    public String getLlojiFantazi() {
        return llojiFantazi;
    }

    public String toString() {
        return "Zhaneri: " + getLlojiFantazi() + super.toString();
    }
}
