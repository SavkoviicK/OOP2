package zad2;

/*
 * Drugi zadatak za domaci (15 poena)
 * ==================================
 * 
 * Napisati igru kamen-papir-makaze-guster-Spok u Javi pomocu soketa.
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
 * Server prihvata konekcije od strane vise klijenata i paralelno ih sve usluzuje.
 * Po uspostavi konekcije, server salje klijentu broj koji predstavlja do koliko
 * pobeda se igra. Potom pocinje igra i svaka strana odvojeno prati rezultat.
 *
 * Klijent pita igraca na kom hostu i portu se nalazi server i uspostavlja
 * konekciju sa njim. Ako je konekcija uspesno uspostavljena, igra pocinje.
 * U svakoj rundi klijent pita igraca da unese potez koji potom salje serveru.
 * Klijent potom cita potez koji je server poslao i azurira stanje poena. Igra
 * se nastavlja dok neko ne skupi potreban broj poena. Posle svake runde, igracu
 * se ispisuje trenutni rezultat a na kraju igre se saopstava ko je pobedio a
 * konekcija se zatvara.
 *
 * Server cita jednu po jednu liniju koja sadrzi potez igraca. Pre citanja
 * linije, server nasumicno bira svoj potez (ne sme da vara i koristi procitanu
 * vrednost pri odredjivanju svog poteza). Po citanju linije, server salje
 * svoj potez klijentu i azurira stanje poena. Ako procitana linija ne sadrzi
 * validan potez, server salje "!!!" umesto svog poteza a broj poena se ne
 * menja. Igra se nastavlja dok neko ne dostigne potreban broj pobeda i u tom
 * slucaju se zatvara konekcija.
 *
 * U celoj aplikaciji obratiti paznju i na moguce greske i ispisati prigodne
 * poruke u tom slucaju.
 */
public class ServerProgramSoketi {

}
