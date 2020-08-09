package com.polyjam.pm;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

public class Graphe {
    private Requis requis;
    private String typeAvancement;

    Graphe(Requis requis) {
        this.requis = requis;
    }

    //Dijkstra power
    Noeud meilleurePlanification() {

        // Heap pour obtenir le prochain noeud (qui a le plus faible poids)
        PriorityQueue< Pair<Long, Noeud> > heap = new PriorityQueue<>(10, new HeapComparator());

        Vector<Noeud> noeuds = Noeud.commencer(requis, "pratiques");
        for (Noeud noeud: noeuds) {
            heap.offer(new Pair<>(0L, noeud));
        }

        Noeud noeud;
        do {
            noeud = heap.poll().getValue(); // le heap n'est jamais vide
            Noeud[] voisins = noeud.voisins();
            for(Noeud voisin: voisins) {
                heap.offer(new Pair<>(voisin.poids, voisin));
            }
        } while(!noeud.estComplet() && heap.size() > 0);

        System.out.println(noeud.estComplet());
        return noeud;
    }

    private static class HeapComparator implements Comparator<Pair<Long, Noeud>> {
        public int compare(Pair<Long, Noeud> pair1, Pair<Long, Noeud> pair2) {
            return pair1.getKey().compareTo(pair2.getKey());
        }
    }
}
