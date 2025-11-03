package zad3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class KlijentProgramRmi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Unesi host (prazno = localhost): ");
            String host = sc.nextLine().trim();
            if (host.isEmpty()) host = "localhost";

            System.out.print("Unesi RMI ime (prazno = " + ServerProgramRmi.BIND_NAME + "): ");
            String ime = sc.nextLine().trim();
            if (ime.isEmpty()) ime = ServerProgramRmi.BIND_NAME;

            Registry reg = LocateRegistry.getRegistry(host, ServerProgramRmi.RMI_PORT);
            GameFactory factory = (GameFactory) reg.lookup(ime);

            Game game = factory.novaPartija();
            System.out.println("Nova partija pokrenuta! Igra se do 3 pobede.");

            boolean gotovo = false;
            while (!gotovo) {
                System.out.print("Potez [K/P/M/G/S] (kamen/papir/makaze/guster/spok): ");
                String unos = sc.nextLine();
                Potez moj = Potez.parse(unos);

                Stanje st = game.odigraj(moj);

                if (!st.validanUnos) {
                    System.out.println("Nevazeci potez! (dozvoljeno: K, P, M, G, S) Rezultat ostaje isti.");
                } else {
                    System.out.printf("Vi: %s  | Server: %s%n", st.potezIgraca, st.potezServera);
                }

                System.out.printf("Rezultat -> Vi: %d  Server: %d (do %d)%n",
                        st.poeniIgrac, st.poeniServer, st.doPobeda);

                gotovo = st.gotovo;
            }

            if (krajPobedioKlijent(game)) {
                System.out.println("Cestitamo, pobedili ste!");
            } else {
                System.out.println("Nazalost, izgubili ste.");
            }

        } catch (Exception e) {
            System.err.println("Greska u klijentu: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    // Pomocna „finalna“ provera (pozovemo jos jednom bez bodovanja)
    private static boolean krajPobedioKlijent(Game game) {
        try {
            // posaljemo null -> nevalidno -> ne menja rezultat, samo dobijemo trenutno stanje
            Stanje st = game.odigraj(null);
            return st.poeniIgrac > st.poeniServer;
        } catch (Exception e) {
            return false;
        }
    }
}
