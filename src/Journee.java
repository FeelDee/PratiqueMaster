import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

public class Journee {
    static final Integer POIDS_AJOUT_MUSICIEN = 10;

    LocalDate date;
    LocalTime heureDebut;
    LocalTime heureFin;

    Vector<Pratique> pratiques;
    Vector<MusicienJournee> musiciens;

    public Journee(LocalDate date, LocalTime heureDebut) {
        this.date = date;
        this.heureDebut = this.heureFin = heureDebut;
        this.pratiques = new Vector<>();
        this.musiciens = new Vector<>();
    }

    long ajouterPratique(Musique musique, Duration duree) {
        long poids = 0;
        Pratique pratique = new Pratique(musique, heureFin, heureFin.plus(duree));

        for (Musicien musicien: musique.musiciens) {
            MusicienJournee mj = findMusicien(musicien);
            if (mj == null) {
                mj = new MusicienJournee(pratique.heureDebut, musicien);
                this.musiciens.add(mj);
                poids += POIDS_AJOUT_MUSICIEN;
            }
            poids += mj.ajouterPratique(pratique);
        }

        heureFin = pratique.heureFin;
        return poids;
    }

    Journee copy() {
        //immutable
        Journee copy = new Journee(date, heureDebut);
        copy.heureFin = heureFin;

        //shallow copy
        copy.pratiques = pratiques;

        //deep copy
        for (MusicienJournee mj: musiciens) {
            copy.musiciens.add(mj.copy());
        }

        return copy;
    }

    private MusicienJournee findMusicien(Musicien musicien) {
        MusicienJournee resultat = null;
        for (MusicienJournee mj: musiciens) {
            if (mj.musicien == musicien) {
                resultat = mj;
                break;
            }
        }
        return resultat;
    }
}
