package zad2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerProgramSoketi {

    private static final int DEFAULT_PORT = 5000;
    private static final int DO_POBEDA = 3; // koliko pobeda je potrebno

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try { port = Integer.parseInt(args[0]); } catch (NumberFormatException ignored) {}
        }

        ExecutorService pool = Executors.newCachedThreadPool();

        try (ServerSocket ss = new ServerSocket(port)) {
            System.out.println("Server pokrenut na portu " + port + ". Cekam klijente...");
            while (true) {
                Socket s = ss.accept();
                pool.submit(new ObradaKlijenta(s));
            }
        } catch (IOException e) {
            System.err.println("Greska pri radu servera: " + e.getMessage());
        }
    }

    // >0: server pobedio rundu; <0: klijent pobedio; 0: nereseno
    static int uporedi(Potez server, Potez klijent) {
        if (server == klijent) return 0;
        return server.beats(klijent) ? 1 : -1;
    }

    private static class ObradaKlijenta implements Runnable {
        private final Socket socket;
        private final Random rnd = new Random();

        ObradaKlijenta(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String remote = socket.getRemoteSocketAddress().toString();
            System.out.println("Klijent povezan: " + remote);

            int poeniServer = 0, poeniKlijent = 0;

            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

                // Po uspostavi konekcije: posalji broj pobeda
                out.write(Integer.toString(DO_POBEDA));
                out.newLine();
                out.flush();

                // Igra dok neko ne dodje do DO_POBEDA
                while (poeniServer < DO_POBEDA && poeniKlijent < DO_POBEDA) {

                    // 1) Server nasumicno bira potez PRE citanja linije (ne sme da “vara”)
                    Potez potezServera = Potez.random(rnd);

                    // 2) Procitaj potez klijenta (jedna linija)
                    String linija = in.readLine();
                    if (linija == null) {
                        System.out.println("Klijent prekinuo vezu: " + remote);
                        break;
                    }
                    Potez potezKlijenta = Potez.parse(linija);

                    // 3) Ako je nevalidno — posalji "!!!" i ne menjas poene
                    if (potezKlijenta == null) {
                        out.write("!!!");
                        out.newLine();
                        out.flush();
                        System.out.printf("[%s] nevazeci potez klijenta: '%s'%n", remote, linija);
                        continue;
                    }

                    // 4) Inace posalji potez servera i azuriraj rezultat
                    out.write(potezServera.name());
                    out.newLine();
                    out.flush();

                    int ishod = uporedi(potezServera, potezKlijenta);
                    if (ishod > 0) poeniServer++;
                    else if (ishod < 0) poeniKlijent++;

                    System.out.printf("[%s] server:%s klijent:%s  ->  %d:%d%n",
                            remote, potezServera, potezKlijenta, poeniServer, poeniKlijent);
                }

            } catch (IOException e) {
                System.err.println("I/O greska sa " + remote + ": " + e.getMessage());
            } finally {
                try { socket.close(); } catch (IOException ignored) {}
                System.out.println("Veza zatvorena: " + remote);
            }
        }
    }
}
