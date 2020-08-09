package com.polyjam.pm;

import java.time.Duration;

public class AvancementTemps extends AvancementAbs {
    private Duration tempsRequis;

    AvancementTemps(Requis requis, Duration temps) {
        super(requis);
        tempsRequis = temps;
    }

    private AvancementTemps(AvancementTemps avancement) {
        super(avancement);
        tempsRequis = avancement.tempsRequis;
    }

    @Override
    AvancementAbs ajouter(Musique musique) {
        AvancementTemps copie = (AvancementTemps)super.ajouter(musique);
        copie.tempsRequis = tempsRequis.minus(musique.tempsPratique);
        return copie;
    }

    @Override
    boolean estComplet() {
        return tempsRequis.compareTo(Duration.ZERO) <= 0;
    }

    @Override
    AvancementAbs copy() {
        return new AvancementTemps(this);
    }
}
