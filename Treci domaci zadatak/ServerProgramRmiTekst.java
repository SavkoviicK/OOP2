package zad3;

/*
 * Treci zadatak za domaci (15 poena)
 * ==================================
 *
 * Napisati distribuiranu igru kamen-papir-makaze-guster-Spok u Javi pomocu RMI.
 *
 * Svaki igrac igra nezavisno protiv servera. U svakoj rundi igrac i server
 * nezavisno biraju potez (kamen, papir, makaze, guster ili Spok) i pobednik
 * runde dobija poen. Igra se dok neko ne osvoji tri poena.
 * 
 * Ako su obe strane odigrale isti potez, niko nije pobedio i niko ne dobija
 * poen, u suprotnom primenjuju se sledeca pravila:
 *   kamen lomi makaze i gustera,
 *   papir pokriva kamen i Spoka
 *   makaze seku papir i gustera,
 *   guster jede papir i Spoka,
 *   Spok unistava kamen i makaze.
 * 
 * Server registruje objekat pomocu kojeg se zapocinje partija. Objekti koji
 * predstavljaju partiju se nalaze iskljucivo na serveru a jedini metod
 * odigraj() se pozivaja udaljeno sa klijenta.
 * 
 * Ovaj metod prima igracev izbor a vraca stanje igre, odnosno ko je do tog
 * momenta pobedio koliko rundi i da li je igra zavrsena.
 *
 * Klijent pita igraca na kom hostu i pod kojim imenom se nalazi serverski
 * objekat i uspostavlja konekciju sa njim. Ako je konekcija uspesno
 * uspostavljena, zapocinje se nova parija i igra pocinje. U svakoj rundi
 * klijent pita igraca da unese potez koji potom salje serveru. Klijent potom
 * proverava stanje a igra se nastavlja dok neko ne skupi potreban broj poena.
 * Posle svake runde, igracu se ispisuje trenutni rezultat a na kraju igre se
 * saopstava ko je pobedio.
 *
 * Server, u metodu za igranje poteza, nasumicno bira svoj potez pre provere
 * igracevog poteza (ne sme da vara i koristi procitanu vrednost pri odredjivanju
 * svog poteza). Po obradi igracevog i svog poteza, metod azurira vraca klijentu
 * stanje partije.
 *
 * U celoj aplikaciji obratiti paznju i na moguce greske i ispisati prigodne
 * poruke u tom slucaju.
 */
public class ServerProgramRmi {

}
