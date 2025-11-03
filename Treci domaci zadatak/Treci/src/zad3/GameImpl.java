package zad3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class GameImpl extends UnicastRemoteObject implements Game {
    private static final long serialVersionUID = 1L;

    private static final int DO_POBEDA = 3;

    private final Random rnd = new Random();
    private int poeniIgrac = 0;
    private int poeniServer = 0;

    protected GameImpl() throws RemoteException { super(); }

    @Override
    public synchronized Stanje odigraj(Potez potezIgraca) throws RemoteException {
        // server bira PRE provere unosa klijenta
        Potez potezServera = Potez.random(rnd);

        boolean validan = (potezIgraca != null);
        if (validan) {
            if (potezServera == potezIgraca) {
                // nereseno – nista
            } else if (potezServera.beats(potezIgraca)) {
                poeniServer++;
            } else {
                poeniIgrac++;
            }
        }
        boolean gotovo = (poeniIgrac >= DO_POBEDA || poeniServer >= DO_POBEDA);

        return new Stanje(poeniIgrac, poeniServer, DO_POBEDA, gotovo,
                potezIgraca, potezServera, validan);
    }
}
