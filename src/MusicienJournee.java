import java.time.Duration;
import java.time.LocalTime;

public class MusicienJournee {
    static final Integer POIDS_MINUTE_TROU = 1;
    static final Double POIDS_DUREE = 0.2;

    LocalTime heureDebut;
    LocalTime heureFin;
    Duration duree;
    Musicien musicien;

    public MusicienJournee(LocalTime heureDebut, Musicien musicien) {
        this.heureDebut = this.heureFin = heureDebut;
        this.duree = Duration.ZERO;
        this.musicien = musicien;
    }

    //retourne le poids d'ajout, pas le poids complet
    long ajouterPratique(Pratique pratique) {
        long poids = 0;

        if (pratique.heureDebut != this.heureFin) {
            long trou = Duration.between(this.heureFin, pratique.heureDebut).toMinutes();
            poids += trou * POIDS_MINUTE_TROU;
        }

        long poidsPrecedent = this.poidsDuree();
        this.duree = this.duree.plus(Duration.between(pratique.heureDebut, pratique.heureFin));
        poids += this.poidsDuree() - poidsPrecedent;

        this.heureFin = pratique.heureFin;

        return poids;
    }

    MusicienJournee copy() {
        //copy of immutable objects + shallow copy of muscicien
        MusicienJournee copy = new MusicienJournee(heureDebut, musicien);
        copy.heureFin = heureFin;
        copy.duree = duree;

        return copy;
    }

    private long poidsDuree() {
        return Math.round(this.duree.toMinutes() * this.duree.toMinutes() * POIDS_DUREE);
    }
}
