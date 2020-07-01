import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

public class Noeud {
    Long poids;
    Vector<Journee> planification;

    Requis requis;
    AvancementAbs avancement;

    private Noeud(Requis requis, Vector<Journee> planification, AvancementAbs avancement) {
        this.poids = 0L;
        this.requis = requis;
        this.planification = planification;
        this.avancement = avancement;
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
                // Ce serait une bonne idee d'encapsuler la copie d'une journee
                Journee newJournee = journee.copy();
                poids += newJournee.ajouterPratique(musique);
                planification.add(newJournee);
            } else {
                planification.add(journee);
            }
        }

        avancement = parent.avancement.ajouter(musique);
    }

    Noeud[] voisins() {
        Vector<Noeud> voisins = new Vector<>();

        for (Journee journee: planification) {
            for (Musique musique: requis.musiques) {
                if (journee.peutAjouter(musique) && avancement.peutAjouter(musique)) {
                    voisins.add(new Noeud(this, musique, journee.date));
                }
            }
        }

        Noeud[] res = new Noeud[voisins.size()];
        voisins.copyInto(res);
        return res;
    }

    boolean estComplet() {
        return avancement.estComplet();
    }

    static Vector<Noeud> commencer (Requis requis, String typeAvancement) {
        AvancementAbs avancement;
        switch(typeAvancement) {
            case "temps":
                avancement = new AvancementTemps(requis, Duration.ofMinutes(90));
                break;
            case "pratiques":
                avancement = new AvancementPratiques(requis);
                break;
            default:
                return null;
        }


        Vector<Noeud> graphe = new Vector<>();
        for (LocalDate date: requis.calendrier) {
            Vector<Noeud> nextGraphe = new Vector<>(graphe);

            for (Noeud noeud: graphe) {
                for (LocalTime heure = Requis.DEFAULT_HEURE_DEBUT;
                     heure.compareTo(Requis.DEFAULT_HEURE_FIN) <= 0;
                     heure = heure.plusMinutes(30)) {
                    Vector<Journee> planif = new Vector<>(noeud.planification);
                    planif.add(new Journee(date, heure));
                    nextGraphe.add(new Noeud(requis, planif, avancement));
                }

                nextGraphe.add(new Noeud(requis, noeud.planification, avancement));
            }

            for (LocalTime heure = Requis.DEFAULT_HEURE_DEBUT;
                 heure.compareTo(Requis.DEFAULT_HEURE_FIN) <= 0;
                 heure = heure.plusMinutes(30)) {
                Vector<Journee> planif = new Vector<>();
                planif.add(new Journee(date, heure));
                nextGraphe.add(new Noeud(requis, planif, avancement));
            }

            graphe = nextGraphe;
        }

        return graphe;
    }
}
