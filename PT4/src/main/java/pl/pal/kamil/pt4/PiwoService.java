package pl.pal.kamil.pt4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class PiwoService {

    private final EntityManagerFactory emf;

    public PiwoService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void add (Piwo piwo)
    {
        EntityManager em;
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(piwo);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Piwo piwo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(piwo));
        em.getTransaction().commit();
        em.close();
    }

    public void display()
    {
        EntityManager em;
        em = emf.createEntityManager();
        List<Piwo> p = em.createQuery("SELECT o FROM Piwo o", Piwo.class).getResultList();
        em.close();

        for (Piwo piwo : p)
        {
            System.out.println(piwo.toString());
        }

    }

    public List<Piwo> find (String name)
    {
        EntityManager em;
        em = emf.createEntityManager();

        List<Piwo> piw = em.createQuery("select c from Piwo c where c.name = :name", Piwo.class)
                .setParameter("name", name)
                .getResultList();
        em.close();

        return piw;
    }

}
