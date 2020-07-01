import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

public class Requis {
    static final LocalTime DEFAULT_HEURE_DEBUT = LocalTime.of(17, 0);
    static final LocalTime DEFAULT_HEURE_FIN = LocalTime.of(22, 0);

    Vector<LocalDate> calendrier;
    Vector<Musique> musiques;

    public Requis(Vector<LocalDate> calendrier, Vector<Musique> musiques) {
        this.calendrier = calendrier;
        this.musiques = musiques;
    }
}
