package zad3;

import java.io.Serializable;

public class Stanje implements Serializable {
    private static final long serialVersionUID = 1L;

    public final int poeniIgrac;
    public final int poeniServer;
    public final int doPobeda;
    public final boolean gotovo;

    public final Potez potezIgraca;
    public final Potez potezServera;
    public final boolean validanUnos; // false => ne menja se rezultat

    public Stanje(int poeniIgrac, int poeniServer, int doPobeda, boolean gotovo,
                  Potez potezIgraca, Potez potezServera, boolean validanUnos) {
        this.poeniIgrac = poeniIgrac;
        this.poeniServer = poeniServer;
        this.doPobeda = doPobeda;
        this.gotovo = gotovo;
        this.potezIgraca = potezIgraca;
        this.potezServera = potezServera;
        this.validanUnos = validanUnos;
    }
}
