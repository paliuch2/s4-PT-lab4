package pl.pal.kamil.pt4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fabryka");

        BrowarService broSer = new BrowarService(emf);
        PiwoService piwSer = new PiwoService(emf);

       String str = "";
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (exit == false)
        {
            str = sc.next();
            switch(str) {
                case "exit":
                    exit = true;
                    break;
                case "addBrowar":
                    try {
                        String nazwaB = sc.next();
                        long wartoscB = Long.parseLong(sc.next());
                        broSer.add(new Browar(nazwaB,wartoscB));
                    }
                    catch (NumberFormatException n) {
                        System.out.println("Niepoprawny format liczby. Przerwano wprowadzanie do bazy.");
                    }
                    break;
                case "addPiwo":
                    try {
                        String nazwaP = sc.next();
                        long cenaP = Long.parseLong(sc.next());
                        String nazwaBrowaru = sc.next();

                        List<Browar> t = broSer.find(nazwaBrowaru);
                        if (!t.isEmpty())
                            piwSer.add(new Piwo(nazwaP, cenaP, t.get(0)));
                        else
                            System.out.println("Nie istnieje taki browar. Nie dodano piwa.");
                    }
                    catch (NumberFormatException n) {
                        System.out.println("Niepoprawny format liczby. Przerwano wprowadzanie do bazy.");
                    }
                    break;
                case "deleteBrowar":
                    String nazwaB = sc.next();
                    List<Browar> t = broSer.find(nazwaB);
                    if (!t.isEmpty())
                    broSer.delete(t.get(0));
                    else
                        System.out.println("Nie istnieje taki browar. Nie dokonano zmian.");
                    break;
                case "deletePiwo":
                    String nazwaP = sc.next();
                    List<Piwo> w = piwSer.find(nazwaP);
                    if (!w.isEmpty())
                    piwSer.delete(w.get(0));
                    else
                        System.out.println("Nie istnieje takie piwo. Nie dokonano zmian.");
                    break;
                case "displayBrowar":
                    broSer.display();
                    break;
                case "displayPiwo":
                    piwSer.display();
                    break;
                case "displayAll":
                    broSer.displayAll();
                    break;
                case "cheaperThan":
                    long limit = Long.parseLong(sc.next());
                    EntityManager em = emf.createEntityManager();
                    List<Browar> found = em.createQuery("select b from Browar b join b.piwa p where p.cena < :cena", Browar.class)
                            .setParameter("cena", limit)
                            .getResultList();
                    em.close();
                    for (Browar b : found)
                    {
                        System.out.println(b.toString());
                    }

                default:
                    break;
            }
        }
        emf.close();
    }
}
