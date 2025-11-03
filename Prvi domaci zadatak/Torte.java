package zad1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

////////////////////////////////////
// Tipovi za predstavljanje torti //
////////////////////////////////////

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
}

/////////////////////////////////////
// Klasa za generisanje porudzbina //
/////////////////////////////////////

public final class Torte {

	/////////////////////
	// Potrebni tokovi //
	/////////////////////

	public static final Stream<Torta> torteStream(int n) {
		Stanje stanje = new Stanje(0);
		return Stream.generate(() -> torta(stanje)).limit(n);
	}

	///////////////////
	// Sistemski deo //
	//  (ne dirati)  //
	///////////////////

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
}
