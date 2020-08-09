package com.polyjam.pm;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Vector;

public class Journee {
    private static final Integer POIDS_AJOUT_MUSICIEN = 200;
    static final Integer MINUTE_INCREMENT = 15;

    LocalDate date;
    LocalTime heureDebut;
    LocalTime heureFin;

    Vector<Pratique> pratiques;
    Vector<MusicienJournee> musiciens;
    HashSet<Musique> musiques;

    public Journee(LocalDate date, LocalTime heureDebut) {
        this.date = date;
        this.heureDebut = this.heureFin = heureDebut;
        this.pratiques = new Vector<>();
        this.musiciens = new Vector<>();
        this.musiques = new HashSet<>();
    }

    boolean peutAjouter(Musique musique) {
        return !musiques.contains(musique) && checkDispos(musique);
    }

    Long ajouterPratique(Musique musique) {
        Long poids = 0L;
        Pratique pratique = new Pratique(musique, heureFin, heureFin.plus(musique.tempsPratique));

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
        musiques.add(musique);
        pratiques.add(pratique);
        return poids;
    }

    Journee copy() {
        //immutable
        Journee copy = new Journee(date, heureDebut);
        copy.heureFin = heureFin;

        //shallow copy of values
        copy.pratiques = new Vector<>(pratiques);
        copy.musiques = new HashSet<>(musiques);

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

    private boolean checkDispos(Musique musique) {
        boolean res = true;
        LocalTime dernierePeriode = heureFin.plus(musique.tempsPratique).minus(Duration.ofMinutes(MINUTE_INCREMENT));
        for (LocalTime heure = heureFin;
             heure.compareTo(dernierePeriode) <= 0;
             heure = heure.plus(Duration.ofMinutes(MINUTE_INCREMENT))) {
            res &= musique.checkDispos(LocalDateTime.of(date, heure));
        }
        return res;
    }
}
