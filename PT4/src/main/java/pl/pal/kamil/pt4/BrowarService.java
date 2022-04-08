package pl.pal.kamil.pt4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BrowarService {

    private final EntityManagerFactory emf;

    public BrowarService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void add (Browar browar)
    {
        EntityManager em;
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(browar);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Browar browar) {
        EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
        em.remove(em.merge(browar));
        em.getTransaction().commit();
        em.close();
    }

    public List<Browar> find (String name)
    {
        EntityManager em;
        em = emf.createEntityManager();

        List<Browar> bro = em.createQuery("select c from Browar c where c.name = :name", Browar.class)
                .setParameter("name", name)
                .getResultList();
        em.close();

        return bro;
    }

    public void display ()
    {
        EntityManager em;
        em = emf.createEntityManager();
        List<Browar> b = em.createQuery("SELECT o FROM Browar o", Browar.class).getResultList();
        em.close();

        for (Browar bro : b)
        {
            System.out.println(bro.toString());
        }
        em.close();
    }

    public void displayAll ()
    {
        EntityManager em;
        em = emf.createEntityManager();
        List<Browar> b = em.createQuery("SELECT o FROM Browar o", Browar.class).getResultList();
        em.close();

        for (Browar bro : b)
        {
            System.out.println(bro.toString());
            for (Piwo piwo : bro.getPiwa())
            {
                System.out.println(piwo.toString());
            }
        }
        em.close();
    }

}
