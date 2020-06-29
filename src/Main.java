import java.time.Duration;
import java.time.LocalDate;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<LocalDate> calendrier = new Vector<>();
        calendrier.add(LocalDate.of(2020, 6, 29));
        calendrier.add(LocalDate.of(2020, 6, 30));
        Musicien thomas = new Musicien("Thomas", null);
        Musicien sendy = new Musicien("Sendy", null);
        Musicien etienne = new Musicien("Étienne", null);
        Vector<Musique> musiques = new Vector<>();
        musiques.add(new Musique("Hold the line", new Musicien[]{etienne, sendy}, Duration.ofMinutes(120)));
        musiques.add(new Musique("Free Bird", new Musicien[]{etienne, thomas}, Duration.ofMinutes(120)));
        Requis requis = new Requis(calendrier, musiques);
        Vector<Noeud> exemple = Noeud.commencer(requis);

        System.out.println("fini :)");
    }
}
