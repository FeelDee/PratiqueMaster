package com.polyjam.pm;

public class AvancementPratiques extends AvancementAbs {
    AvancementPratiques(Requis requis) {
        super(requis);
    }

    private AvancementPratiques(AvancementPratiques avancement) {
        super(avancement);
    }

    @Override
    boolean estComplet() {
        return compteurPratiques.isEmpty();
    }

    @Override
    AvancementAbs copy() {
        return new AvancementPratiques(this);
    }
}
