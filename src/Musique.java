import java.time.Duration;
import java.time.LocalDateTime;

public class Musique {
    String nom;
    Musicien[] musiciens;
    Duration tempsPratique;
    Integer pratiquesRequises;

    public Musique(String nom, Musicien[] musiciens, Duration tempsPratique, Integer pratiquesRequises) {
        this.nom = nom;
        this.musiciens = musiciens;
        this.tempsPratique = tempsPratique;
        this.pratiquesRequises = pratiquesRequises;
    }

    boolean contient(Musicien musicien) {
        boolean contient = false;
        for (Musicien in: musiciens) {
            if (in == musicien) {
                contient = true;
                break;
            }
        }
        return contient;
    }

    boolean checkDispos(LocalDateTime temps) {
        boolean res = true;
        for (Musicien musicien: musiciens) {
            res &= musicien.checkDispo(temps);
        }
        return res;
    }
}
