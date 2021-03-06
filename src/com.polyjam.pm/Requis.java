package com.polyjam.pm;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Requis {
    static final LocalTime DEFAULT_HEURE_DEBUT = LocalTime.of(17, 0);
    static final LocalTime DEFAULT_HEURE_FIN = LocalTime.of(22, 0);

    ArrayList<LocalDate> calendrier;
    ArrayList<Musique> musiques;

    public Requis() {
        this.calendrier = new ArrayList<>();
        this.musiques = new ArrayList<>();
    }
}
