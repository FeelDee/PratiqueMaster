package com.polyjam.pm;

public class Main {
    public static void main(String[] args) {
        GestionnaireJson parser = new GestionnaireJson();
        Graphe graphe = parser.lire("{" +
                "\"requis\": {" +
                "\"calendrier\": [" +
                "\"2020-06-29\", \"2020-06-30\"" +
                "]," +
                "\"typeAvancement\": \"pratiques\"" +
                "}," +
                "\"musiciens\": [" +
                "{" +
                "\"nom\": \"Thomas\"," +
                "\"dispos\": [" +
                "[\"2020-06-29T18:00\", true]," +
                "[\"2020-06-29T18:15\", true]," +
                "[\"2020-06-29T18:30\", true]," +
                "[\"2020-06-29T18:45\", true]," +
                "[\"2020-06-30T19:00\", true]," +
                "[\"2020-06-30T19:15\", true]," +
                "[\"2020-06-30T19:30\", true]," +
                "[\"2020-06-30T19:45\", true]" +
                "]" +
                "}," +
                "{" +
                "\"nom\": \"Sendy\"," +
                "\"dispos\":  [" +
                "[\"2020-06-29T19:00\", true]," +
                "[\"2020-06-29T19:15\", true]," +
                "[\"2020-06-29T19:30\", true]," +
                "[\"2020-06-29T19:45\", true]," +
                "[\"2020-06-30T18:00\", true]," +
                "[\"2020-06-30T18:15\", true]," +
                "[\"2020-06-30T19:30\", true]," +
                "[\"2020-06-30T19:45\", true]" +
                "]" +
                "}," +
                "{" +
                "\"nom\": \"Etienne\"," +
                "\"dispos\":  [" +
                "[\"2020-06-29T19:00\", true]," +
                "[\"2020-06-29T19:15\", true]," +
                "[\"2020-06-29T19:30\", true]," +
                "[\"2020-06-29T19:45\", true]," +
                "[\"2020-06-30T19:00\", true]," +
                "[\"2020-06-30T19:15\", true]," +
                "[\"2020-06-30T19:30\", true]," +
                "[\"2020-06-30T19:45\", true]" +
                "]" +
                "}" +
                "]," +
                "\"musiques\": [" +
                "{" +
                "\"nom\": \"Hold the Line\"," +
                "\"musiciens\": [ \"Etienne\", \"Sendy\" ]," +
                "\"tempsPratique\": 30," +
                "\"pratiquesRequises\": 2" +
                "}," +
                "{" +
                "\"nom\": \"Free Bird\"," +
                "\"musiciens\": [ \"Etienne\", \"Thomas\" ]," +
                "\"tempsPratique\": 30," +
                "\"pratiquesRequises\": 1" +
                "}" +
                "]" +
                "}");

        Noeud noeud = graphe.meilleurePlanification();
        System.out.println(parser.ecrire(noeud));
    }
}
