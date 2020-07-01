import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<LocalDate> calendrier = new Vector<>();
        calendrier.add(LocalDate.of(2020, 6, 29));
        calendrier.add(LocalDate.of(2020, 6, 30));
        Musicien thomas = new Musicien("Thomas", exempleDispos1());
        Musicien sendy = new Musicien("Sendy", exempleDispos2());
        Musicien etienne = new Musicien("Étienne", exempleDispos3());
        Vector<Musique> musiques = new Vector<>();
        musiques.add(new Musique("Hold the line", new Musicien[]{etienne, sendy}, Duration.ofMinutes(30), 3));
        musiques.add(new Musique("Free Bird", new Musicien[]{etienne, thomas}, Duration.ofMinutes(30), 3));

        Requis requis = new Requis(calendrier, musiques);
        Graphe graphe = new Graphe(requis);

        graphe.meilleurePlanification();

        System.out.println("fini :)");
    }


    private static HashMap<LocalDateTime, Boolean> exempleDispos1() {
        HashMap<LocalDateTime, Boolean> res = new HashMap<>();
        res.put(LocalDateTime.of(2020, 6, 29, 18, 0), true);
        res.put(LocalDateTime.of(2020, 6, 29, 18, 15), true);
        res.put(LocalDateTime.of(2020, 6, 29, 18, 30), true);
        res.put(LocalDateTime.of(2020, 6, 29, 18, 45), true);
        res.put(LocalDateTime.of(2020, 6, 30, 19, 0), true);
        res.put(LocalDateTime.of(2020, 6, 30, 19, 15), true);
        res.put(LocalDateTime.of(2020, 6, 30, 19, 30), true);
        res.put(LocalDateTime.of(2020, 6, 30, 19, 45), true);
        return res;
    }
    private static HashMap<LocalDateTime, Boolean> exempleDispos2() {
        HashMap<LocalDateTime, Boolean> res = new HashMap<>();
        res.put(LocalDateTime.of(2020, 6, 29, 18, 0), true);
        res.put(LocalDateTime.of(2020, 6, 29, 18, 15), true);
        res.put(LocalDateTime.of(2020, 6, 29, 18, 45), true);
        res.put(LocalDateTime.of(2020, 6, 29, 19, 45), true);
        res.put(LocalDateTime.of(2020, 6, 30, 19, 45), true);
        res.put(LocalDateTime.of(2020, 6, 30, 20, 0), true);
        res.put(LocalDateTime.of(2020, 6, 30, 20, 15), true);
        res.put(LocalDateTime.of(2020, 6, 30, 20, 30), true);
        return res;
    }
    private static HashMap<LocalDateTime, Boolean> exempleDispos3() {
        HashMap<LocalDateTime, Boolean> res = new HashMap<>();
        res.put(LocalDateTime.of(2020, 6, 29, 19, 0), true);
        res.put(LocalDateTime.of(2020, 6, 29, 19, 15), true);
        res.put(LocalDateTime.of(2020, 6, 29, 19, 30), true);
        res.put(LocalDateTime.of(2020, 6, 29, 19, 45), true);
        res.put(LocalDateTime.of(2020, 6, 30, 19, 0), true);
        res.put(LocalDateTime.of(2020, 6, 30, 19, 15), true);
        res.put(LocalDateTime.of(2020, 6, 30, 19, 30), true);
        res.put(LocalDateTime.of(2020, 6, 30, 19, 45), true);
        return res;
    }
}
