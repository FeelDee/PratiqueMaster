package com.polyjam.pm;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class GestionnaireJson {
    private Gson gson;

    GestionnaireJson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gson = builder.create();
    }

    Graphe lire(String json) {
        Info info = gson.fromJson(json, Info.class);
        Requis requis = new Requis();
        Collections.addAll(requis.calendrier, info.requis.calendrier);

        // créer musiques à partir des informations
        for (MusiquePrototype musProto : info.musiques) {
            ArrayList<Musicien> musiciens = new ArrayList<>();
            for (String nom : musProto.musiciens) {
                musiciens.add(trouverMusicien(nom, info.musiciens));
            }
            requis.musiques.add(
                    new Musique(
                        musProto.nom,
                        musiciens.toArray(new Musicien[0]),
                        Duration.ofMinutes(musProto.tempsPratique),
                        musProto.pratiquesRequises
                    )
            );
        }

        return new Graphe(requis, info.requis.typeAvancement);
    }

    String ecrire (Noeud noeud) {
        return gson.toJson(noeud);
    }

    private static Musicien trouverMusicien(String nom, Musicien[] musiciens) {
        for (Musicien musicien : musiciens) {
            if (musicien.nom.equals(nom)) {
                return musicien;
            }
        }
        return null;
    }

    private static class RequisPrototype{
        LocalDate[] calendrier;
        String typeAvancement;
    }

    private static class MusiquePrototype{
        String nom;
        String[] musiciens;
        Integer tempsPratique;
        Integer pratiquesRequises;
    }

    private static class Info {
        RequisPrototype requis;
        MusiquePrototype[] musiques;
        Musicien[] musiciens;
    }

    private static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
        }
    }

    private static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString());
        }
    }
}
