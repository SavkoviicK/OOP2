package zad1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

final class Torta {

    private final String id;
    private final String porucilac;
    private final LocalDate datum;
    private final int velicina;
    private final OblikTorte oblik;
    private final List<Sloj> slojevi;

    public Torta(String id, String porucilac, LocalDate datum, int velicina, OblikTorte oblik, List<Sloj> slojevi) {
        this.id = id;
        this.porucilac = porucilac;
        this.datum = datum;
        this.velicina = velicina;
        this.oblik = oblik;
        this.slojevi = slojevi;
    }

    public String getId() {
        return id;
    }

    public String getPorucilac() {
        return porucilac;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public int getVelicina() {
        return velicina;
    }

    public OblikTorte getOblik() {
        return oblik;
    }

    public List<Sloj> getSlojevi() {
        return slojevi;
    }

    @Override
    public String toString() {
        return "Torta{" +
                "id='" + id + '\'' +
                ", porucilac='" + porucilac + '\'' +
                ", datum=" + datum +
                ", velicina=" + velicina +
                ", oblik=" + oblik +
                ", slojevi=" + slojevi +
                '}';
    }
}

enum OblikTorte {
    KRUG, KVADRAT, SRCE, ZVEZDA, DRUGO;
}

final class Sloj {

    private final VrstaSloja vrsta;
    private final Set<Sastojak> sastojci;

    public Sloj(VrstaSloja vrsta, Set<Sastojak> sastojci) {
        this.vrsta = vrsta;
        this.sastojci = sastojci;
    }

    public VrstaSloja getVrsta() {
        return vrsta;
    }

    public Set<Sastojak> getSastojci() {
        return sastojci;
    }

    @Override
    public String toString() {
        return "Sloj{" +
                "vrsta=" + vrsta +
                ", sastojci=" + sastojci +
                '}';
    }
}

enum VrstaSloja {
    FIL, KORA, PRELIV;
}

final class Sastojak {

    private final String naziv;
    private final int kolicina;

    public Sastojak(String naziv, int kolicina) {
        this.naziv = naziv;
        this.kolicina = kolicina;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getKolicina() {
        return kolicina;
    }

    @Override
    public String toString() {
        return naziv + " (" + kolicina + ")";
    }
}

public final class Torte {

    public static final Stream<Torta> torteStream(int n) {
        Stanje stanje = new Stanje(0);
        return Stream.generate(() -> torta(stanje)).limit(n);
    }

    private static final String[] SASTOJCI = new String[] {
            "Jaje", "Zumance", "Belance",
            "Keks", "Plazma",
            "Orasi", "Bademi", "Lesnici", "Kikiriki",
            "Margarin", "Puter", "Pavlaka",
            "Mleko", "Jogurt", "Ulje", "Liker",
            "Jabuke", "Kruske", "Jagode", "Maline", "Visnje", "Ananas", "Banane", "Sumsko voce", "Borovnice", "Brusnice", "Limun", "Kivi",
            "Cokolada", "Cokolada za kuvanje", "Mlecna cokolada", "Bela cokolada",
            "Puding", "Pekmez", "Dzem",
            "Secer", "Secer u prahu", "Vanilin secer", "So", "Prasak za pecivo",
            "Kakao", "Vanila", "Karamela",
    };

    private static class Stanje {

        public final Random random;

        public Stanje(int broj) {
            random = new Random(broj);
        }

        private int brojac = 1;
        public String id() {
            brojac += 1 + random.nextInt(5);
            return String.format("#%06d", brojac);
        }

        public String porucilac() {
            return String.format("@%04d", 1 + random.nextInt(9999));
        }

        public LocalDate datum() {
            return LocalDate.of(2020, 1 + random.nextInt(12), 1 + random.nextInt(28));
        }

        public int velicina() {
            return 1 + random.nextInt(9);
        }

        public OblikTorte oblik() {
            OblikTorte[] a = OblikTorte.values();
            int i = random.nextInt(a.length);
            return a[i];
        }

        public VrstaSloja vrsta() {
            VrstaSloja[] a = VrstaSloja.values();
            int i = random.nextInt(a.length);
            return a[i];
        }
    }

    private static final Torta torta(Stanje stanje) {
        String id = stanje.id();
        String porucilac = stanje.porucilac();
        LocalDate datum = stanje.datum();
        int velicina = stanje.velicina();
        OblikTorte oblik = stanje.oblik();
        List<Sloj> slojevi = new ArrayList<>();
        int n = 1 + stanje.random.nextInt(10);
        Sloj sloj = null;
        for (int i = 0; i < n; i ++) {
            sloj = sloj(stanje, null);
            slojevi.add(sloj);
        }
        if (sloj == null || sloj.getVrsta() != VrstaSloja.PRELIV) {
            if (stanje.random.nextDouble() < 0.9) {
                slojevi.add(sloj(stanje, VrstaSloja.PRELIV));
            }
        }
        return new Torta(id, porucilac, datum, velicina, oblik, slojevi);
    }

    private static final Sloj sloj(Stanje stanje, VrstaSloja vrsta) {
        if (vrsta == null) {
            vrsta = stanje.vrsta();
        }
        Set<Sastojak> sastojci = new HashSet<>();
        if (vrsta == VrstaSloja.KORA && stanje.random.nextDouble() < 0.75) {
            String naziv = "Brasno";
            int kolicina = 1 + stanje.random.nextInt(5);
            sastojci.add(new Sastojak(naziv, kolicina));
        }
        int n = 2 + stanje.random.nextInt(5);
        for (int i = 0; i < n; i ++) {
            String naziv = SASTOJCI[stanje.random.nextInt(SASTOJCI.length)];
            int kolicina = 1 + stanje.random.nextInt(5);
            sastojci.add(new Sastojak(naziv, kolicina));
        }
        return new Sloj(vrsta, sastojci);
    }

    public static long ispodPet(int dan, int mesec) {
        return torteStream(5000)
                // samo 2020.
                .filter(t -> t.getDatum().getYear() == 2020)
                // tacno zadati dan i mesec
                .filter(t -> t.getDatum().getDayOfMonth() == dan && t.getDatum().getMonthValue() == mesec)
                // ima manje od 5 PRELIVA
                .filter(t -> t.getSlojevi().stream()
                        .filter(s -> s.getVrsta() == VrstaSloja.PRELIV)
                        .count() < 5)
                .count();
    }

    public static List<String> razlicitiSastojci(String id) {
        return torteStream(5000)
                .filter(t -> t.getId().equals(id)) // nadji tortu po id-ju
                .flatMap(t -> t.getSlojevi().stream()) // uzmi sve slojeve
                .flatMap(s -> s.getSastojci().stream()) // iz slojeva uzmi sastojke
                .map(Sastojak::getNaziv) // uzmi samo naziv
                .distinct() // izbaci duplikate
                .sorted() // sortiraj abecedno
                .toList(); // pretvori u listu
    }

    public static void ispisiKolicine(String id) {
        // nadji tortu sa zadatim id-jem
        torteStream(5000)
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(torta -> {
                    // mapiramo sastojke i sabiramo kolicine po nazivu
                    var mapa = torta.getSlojevi().stream()
                            .flatMap(s -> s.getSastojci().stream())
                            .collect(java.util.stream.Collectors.toMap(
                                    Sastojak::getNaziv,
                                    Sastojak::getKolicina,
                                    Integer::sum // ako se isti sastojak ponavlja – saberi
                            ));

                    // zaglavlje
                    System.out.printf("%-15s | %s%n", "Sastojak", "Kolicina");
                    System.out.println("-----------------+----------");

                    // ispis sortiran po nazivu
                    mapa.entrySet().stream()
                            .sorted(java.util.Map.Entry.comparingByKey())
                            .forEach(e ->
                                    System.out.printf("%-15s | %d%n", e.getKey(), e.getValue())
                            );

                }, () -> {
                    // ako torta sa id-jem ne postoji
                    System.out.println("Nema torte sa ID " + id);
                });
    }

    public static void statistikaOblika2020() {
        String[] meseci = {
                "Januar", "Februar", "Mart", "April", "Maj", "Jun",
                "Jul", "Avgust", "Septembar", "Oktobar", "Novembar", "Decembar"
        };

        // pripremi brojace: [srca, okrugle, ostale] po mesecima 1..12
        java.util.Map<Integer, long[]> mapa = new java.util.HashMap<>();
        for (int m = 1; m <= 12; m++) mapa.put(m, new long[]{0, 0, 0});

        torteStream(5000)
                .filter(t -> t.getDatum().getYear() == 2020)
                .forEach(t -> {
                    int m = t.getDatum().getMonthValue();
                    long[] a = mapa.get(m);
                    if (t.getOblik() == OblikTorte.SRCE) a[0]++;        // Srca
                    else if (t.getOblik() == OblikTorte.KRUG) a[1]++;   // Okrugle
                    else a[2]++;                                        // Ostale
                });

        // ispis tabele
        System.out.printf("%-9s | %5s | %7s | %6s%n", "Mesec", "Srca", "Okrugle", "Ostale");
        System.out.println("---------+-------+---------+--------");
        for (int i = 0; i < 12; i++) {
            long[] a = mapa.get(i + 1);
            System.out.printf("%-9s | %5d | %7d | %6d%n", meseci[i], a[0], a[1], a[2]);
        }
    }


}
