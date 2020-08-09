package com.polyjam.pm;

import java.util.HashMap;

public abstract class AvancementAbs {
    HashMap<Musique, Integer> compteurPratiques;    //compte nombre pratiques restantes

    AvancementAbs(Requis requis) {
        compteurPratiques = new HashMap<>();
        for (Musique musique: requis.musiques) {
            compteurPratiques.put(musique, musique.pratiquesRequises);
        }
    }

    AvancementAbs(AvancementAbs avancementAbs){
        compteurPratiques = new HashMap<>(avancementAbs.compteurPratiques);
    }

    boolean peutAjouter(Musique musique) {
        Integer pratiquesRestantes = compteurPratiques.getOrDefault(musique, 0);
        return pratiquesRestantes > 0;
    }
    AvancementAbs ajouter(Musique musique) {
        AvancementAbs copie = copy();
        Integer nbPratiques = copie.compteurPratiques.getOrDefault(musique, 0);
        if (nbPratiques > 1) {
            copie.compteurPratiques.put(musique, nbPratiques - 1);
        } else {
            copie.compteurPratiques.remove(musique);
        }
        return copie;
    }
    abstract boolean estComplet();
    abstract AvancementAbs copy();
}
