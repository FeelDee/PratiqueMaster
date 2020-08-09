package com.polyjam.pm;

import java.time.LocalTime;

public class  Pratique {
    LocalTime heureDebut;
    LocalTime heureFin;

    Musique musique;

    public Pratique(Musique musique, LocalTime heureDebut, LocalTime heureFin) {
        this.musique = musique;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }
}