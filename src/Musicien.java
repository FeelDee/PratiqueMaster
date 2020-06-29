import java.time.LocalDateTime;
import java.util.HashMap;

public class Musicien {
    private HashMap<LocalDateTime, Boolean> dispos;

    String nom;

    public Musicien(String nom, HashMap<LocalDateTime, Boolean> dispos) {
        this.nom = nom;
        this.dispos = dispos;
    }

    public Boolean checkDispo(LocalDateTime date) {
        return dispos.getOrDefault(date, false);
    }
}
