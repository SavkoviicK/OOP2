package zad2;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

public class KlijentProgramSoketi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Uzimamo host i port od korisnika
        System.out.print("Unesi host (npr. localhost): ");
        String host = sc.nextLine().trim();
        if (host.isEmpty()) host = "localhost";

        System.out.print("Unesi port (npr. 5000): ");
        String portStr = sc.nextLine().trim();
        int port = 5000;
        try { if (!portStr.isEmpty()) port = Integer.parseInt(portStr); } catch (NumberFormatException ignored) {}

        // 2) Spajamo se na server
        try (Socket s = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8))) {

            System.out.println("Povezano sa serverom: " + s.getRemoteSocketAddress());

            // 3) Server najpre šalje broj pobeda do kog se igra
            String limitLine = in.readLine();
            if (limitLine == null) {
                System.out.println("Server je zatvorio vezu.");
                return;
            }
            int DO_POBEDA = Integer.parseInt(limitLine);
            System.out.println("Igra do " + DO_POBEDA + " pobeda.");
            System.out.println("Dozvoljeni potezi: K(amen), P(apir), M(akaze), G(uster), S(pok).");

            int poeniJa = 0, poeniServer = 0;

            // 4) Petlja rundi dok neko ne skupi DO_POBEDA
            while (poeniJa < DO_POBEDA && poeniServer < DO_POBEDA) {
                System.out.print("Unesi potez (K/P/M/G/S ili cela rec): ");
                String mojPotez = sc.nextLine().trim();

                // posalji liniju serveru
                out.write(mojPotez);
                out.newLine();
                out.flush();

                // procitaj potez servera (ili "!!!" ako smo poslali nevalidno)
                String odgovor = in.readLine();
                if (odgovor == null) {
                    System.out.println("Server je prekinuo vezu.");
                    break;
                }

                if ("!!!".equals(odgovor)) {
                    System.out.println("Nevazeci potez! Runda se ne racuna.");
                    continue;
                }

                // legitimni potez servera
                System.out.println("Server je odigrao: " + odgovor);

                Potez pServer = Potez.parse(odgovor);
                Potez pJa = Potez.parse(mojPotez);

                if (pJa == null || pServer == null) {
                    System.out.println("Greska u parsiranju poteza. Runda preskocena.");
                    continue;
                }

                int ishod = ServerProgramSoketi.uporedi(pServer, pJa);
                if (ishod > 0) poeniServer++;
                else if (ishod < 0) poeniJa++;
                // ishod == 0 -> nereseno, niko ne dobija poen

                System.out.printf("Rezultat: JA %d : %d SERVER%n", poeniJa, poeniServer);
            }

            // 5) Kraj igre
            if (poeniJa > poeniServer) System.out.println("BRAVO! Pobedio si.");
            else if (poeniServer > poeniJa) System.out.println("Poraz. Server je pobedio.");
            else System.out.println("Igra prekinuta.");

        } catch (IOException e) {
            System.err.println("Ne mogu da se povezem/komuniciram sa serverom: " + e.getMessage());
        }
    }
}
