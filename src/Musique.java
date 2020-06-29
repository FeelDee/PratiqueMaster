import java.time.Duration;

public class Musique {
    String nom;
    Musicien[] musiciens;
    Duration tempsRequis;

    public Musique(String nom, Musicien[] musiciens, Duration tempsRequis) {
        this.nom = nom;
        this.musiciens = musiciens;
        this.tempsRequis = tempsRequis;
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
}
