package zad1;

/*
 * Prvi zadatak za domaci (20 poena)
 * =================================
 * 
 * Napisati Java aplikaciju koja pomocu tokova podataka i lambda izraza
 * obradjuje podatke o rucno radjenim tortama jedne renomirane poslasticarnice.
 * 
 * Data je klasa Torta kojom se predstavljaju torte. Svaka torta ima svoj
 * identifikator, identifikator kupca koji ju je porucio, datum kada je porucena,
 * velicinu i oblik torte, kao i listu slojeva.
 * 
 * Slojevi su predstavljeni klasom Sloj. Svaki sloj moze biti kora (koja se pece),
 * fil (koji se ne pece) ili preliv (koji se takodje ne pece). Sloj, pored svog
 * tipa sadrzi i skup sastojaka.
 * 
 * Svaki sastojak sloja torte je predstavljen klasom Sastojak koja sadrzi
 * informacije o nazivu sastojka i njegovoj kolicini u odgovarajucim jedinicama
 * mere (komad, solja, kasicica...)
 * 
 * Tok torti je dat u vidu metoda torteStream() klase Torte.
 * 
 * Prvi deo (5 poena)
 * ------------------
 * 
 * Implementirati metod long ispodPet(int dan, int mesec), pozvati ga u glavnom
 * programu i ispisati rezultat.
 * 
 * Metod vraca ukupan broj torti porucenih zadatog dana i meseca 2020. godine
 * koje imaju manje od pet preliva. 
 * 
 * Drugi deo (5 poena)
 * -------------------
 * 
 * Implementirati metod List<String> razlicitiSastojci(String id), pozvati ga u
 * glavnom programu i ispisati rezultat.
 * 
 * Metod vraca nazive svih sastojaka potrebnih za pravljenje torte sa zadatim
 * identifikatorom, sortirane abecedno, bez ponavaljanja i bez kolicina.
 * 
 * Ako torta sa zadatim identifikatorom ne postoji, vratiti praznu listu.
 * 
 * Treci deo (5 poena)
 * -------------------
 * 
 * Za svaki sastojak torte sa identifikatorom koriscenim u prethodnom metodu,
 * ispisati koliko je ukupno jedinica mere (komada, solja, kasicica...) potrebno
 * za pravljenje te torte, na sledeci nacin:
 * 
 *  Sastojak | Kolicina
 *-----------+----------
 *    Brasno | 5
 *  Margarin | 3
 *    Pekmez | 2
 *          ...
 * 
 * Cetvrti deo (5 poena)
 * ---------------------
 * 
 * Za svaki mesec 2020. godine, ispisati koliko je poruceno torti u obliku srca,
 * koliko okruglih, a koliko torti ostalih oblika, u tabelarnom obliku na
 * sledeci nacin:
 * 
 * Mesec   | Srca | Okrugle | Ostale 
 * --------+------+---------+--------
 * Januar  |   12 |      53 |     23
 * Februar |   35 |      12 |     14
 * ...     |  ... |     ... |    ...
 * 
 */
public class Program {

	public static void main(String[] args) {

		Torte.torteStream(5000)
				.forEach(System.out::println);

	}
}
