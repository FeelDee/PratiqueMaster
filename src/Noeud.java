import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

public class Noeud {
    Long poids;
    Vector<Journee> planification;

    Requis requis;

    private Noeud(Requis requis, Vector<Journee> planification) {
        this.poids = 0L;
        this.requis = requis;
        this.planification = planification;
    }

    private Noeud(Noeud parent, Musique musique, LocalDate date) {
        //immutable
        this.poids  = parent.poids;

        //shallow copy
        this.requis = parent.requis;

        //deep copy
        planification = new Vector<>();
        for (Journee journee: parent.planification) {
            if (journee.date == date) {
                Journee newJournee = journee.copy();
                poids += newJournee.ajouterPratique(musique);
                planification.add(newJournee);
            } else {
                planification.add(journee);
            }
        }
    }

    Noeud[] voisins() {
        Vector<Noeud> voisins = new Vector<>();

        for (Journee journee: planification) {
            for (Musique musique: requis.musiques) {
                if (journee.peutAjouter(musique)) {
                    voisins.add(new Noeud(this, musique, journee.date));
                }
            }
        }

        Noeud[] res = new Noeud[voisins.size()];
        voisins.copyInto(res);
        return res;
    }

    static Vector<Noeud> commencer (Requis requis) {
        Vector<Noeud> graphe = new Vector<>();
        for (LocalDate date: requis.calendrier) {
            Vector<Noeud> nextGraphe = new Vector<>(graphe);

            for (Noeud noeud: graphe) {
                for (LocalTime heure = Requis.DEFAULT_HEURE_DEBUT;
                     heure.compareTo(Requis.DEFAULT_HEURE_FIN) <= 0;
                     heure = heure.plusMinutes(30)) {
                    Vector<Journee> planif = new Vector<>(noeud.planification);
                    planif.add(new Journee(date, heure));
                    nextGraphe.add(new Noeud(requis, planif));
                }

                nextGraphe.add(new Noeud(requis, noeud.planification));
            }

            for (LocalTime heure = Requis.DEFAULT_HEURE_DEBUT;
                 heure.compareTo(Requis.DEFAULT_HEURE_FIN) <= 0;
                 heure = heure.plusMinutes(30)) {
                Vector<Journee> planif = new Vector<>();
                planif.add(new Journee(date, heure));
                nextGraphe.add(new Noeud(requis, planif));
            }

            graphe = nextGraphe;
        }

        return graphe;
    }
}
