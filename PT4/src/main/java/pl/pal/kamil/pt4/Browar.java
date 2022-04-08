package pl.pal.kamil.pt4;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Browar {

    public Browar(){};

    public Browar(String name, long wartosc) {
        this.name = name;
        this.wartosc = wartosc;
    }

    @Id
    private String name;
    private long wartosc;
    @OneToMany (mappedBy = "browar", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Piwo> piwa = new ArrayList<>();

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Browar browar = (Browar) o;
        return wartosc == browar.wartosc &&
                Objects.equals(name, browar.name) &&
                Objects.equals(piwa, browar.piwa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wartosc, piwa);
    }


    public long getWartosc() {
        return wartosc;
    }

    public void setWartosc(long wartosc) {
        this.wartosc = wartosc;
    }
    public List<Piwo> getPiwa() {
        return piwa;
    }

    public void getPiwa(List<Piwo> piwa) {
        this.piwa = piwa;
    }

    @Override
    public String toString () {
        return "Nazwa browaru: " + name + " Wartość browaru: " + wartosc + " Ilość piw produkowanych: " + piwa.size();
    }

}
