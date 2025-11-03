package zad3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Game extends Remote {
    // Klijent poziva za SVAKU rundu
    Stanje odigraj(Potez potezIgraca) throws RemoteException;
}
