package pl.pal.kamil.pt4;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Piwo {

    public Piwo(){};

    public Piwo(String name, long cena, Browar browar) {
        this.name = name;
        this.cena = cena;
        this.browar = browar;
    }

    @Id
    private String name;

    private long cena;

    @ManyToOne
    private Browar browar;


    public String getName() {
        return name;
    }

    public long getCena() {
        return cena;
    }

    public void setCena(long cena) {
        this.cena = cena;
    }

    public Browar getBrowar() {
        return browar;
    }

    public void setBrowar(Browar browar) {
        this.browar = browar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piwo piwo = (Piwo) o;
        return cena == piwo.cena &&
                Objects.equals(name, piwo.name) &&
                Objects.equals(browar, piwo.browar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cena, browar);
    }

    @Override
    public String toString () {
        return "Nazwa piwa: " + name + " Cena piwa: " + cena + " Produkowane przez browar: " + browar.getName();
    }

}
