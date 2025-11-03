package zad1;

public class Program {

    public static void main(String[] args) {

        Torte.torteStream(5000)
                .forEach(System.out::println);


        //ISPIS PRVI DEO

        long broj = Torte.ispodPet(20,8); // bilo koji datum
        System.out.println("Torti sa <5 preliva tog dana: " + broj);

        //ISPIS DRUGI DEO

        System.out.println("Razliciti sastojci za tortu #015011:"); // na primer torta #015011
        System.out.println(Torte.razlicitiSastojci("#015011"));

        //ISPIS TRECI DEO

        System.out.println("\nKolicine sastojaka za tortu #015011:");
        Torte.ispisiKolicine("#015011");

        //ISPIS CETVRTI DEO

        System.out.println();
        Torte.statistikaOblika2020();
    }

}