package zad3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerProgramRmi {

    public static final String BIND_NAME = "RPSFactory";
    public static final int RMI_PORT = 1099;

    public static void main(String[] args) {
        try {
            // pokreni (ili dohvati) RMI registry u procesu servera
            Registry reg;
            try {
                reg = LocateRegistry.createRegistry(RMI_PORT);
                System.out.println("RMI registry startovan na portu " + RMI_PORT);
            } catch (Exception already) {
                reg = LocateRegistry.getRegistry(RMI_PORT);
                System.out.println("RMI registry vec postoji na portu " + RMI_PORT);
            }

            GameFactory factory = new GameFactoryImpl();
            reg.rebind(BIND_NAME, factory);

            System.out.println("Server spreman. Bind ime: \"" + BIND_NAME + "\"");
            System.out.println("Cekam klijente... (CTRL+C za prekid)");

        } catch (Exception e) {
            System.err.println("Greska pri startovanju servera: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
