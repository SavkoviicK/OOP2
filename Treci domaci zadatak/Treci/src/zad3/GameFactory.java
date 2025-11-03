package zad3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameFactory extends Remote {
    Game novaPartija() throws RemoteException;
}
