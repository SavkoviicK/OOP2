package zad3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameFactoryImpl extends UnicastRemoteObject implements GameFactory {
    private static final long serialVersionUID = 1L;

    protected GameFactoryImpl() throws RemoteException { super(); }

    @Override
    public Game novaPartija() throws RemoteException {
        return new GameImpl();
    }
}
