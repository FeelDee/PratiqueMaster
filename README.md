Pour générer des pratiques, PratiqueMaster se sert des modèles ci-dessous afin de représenter les données.
Le programme devra cependant permettre aux utilisateurs d'entrer leurs disponibilités comme avant.

Durée: 
le temps est découpé en blocs de 15 minutes.
une pratique peut s'étendre de 17h00 à 22h00

Disponiblités:
Pour l'instant, la disponibilité est de 0 (false) ou de 1 (true).

Par défaut, ces préférences sont pour toute la journée, mais on peut aussi définir des plages horaires,
avec une préférence associée.
Si un plage horaire particulière est définie par l'utilisateur, il faudra aussi qu'il entre une préférence
par défaut.

On devrait également pouvoir inscrire une durée de pratique maximale désirée pour une journée.
Au-delà de ce seuil, la valeur de la préférence tombe à 1 (absolue nécessité).

Musique:
Une musique comporte
- les musiciens qui jouent dedans (ben voyons)
- le temps de pratique requis (durée) estimé au préalable
Le temps de pratique requis devra évidemment être modifié en fonction de la situation

Requis:
PratiqueMaster devra créer des pratiques selon un de ces critères
- atteindre un objectif de temps de pratique total pour une période prédéterminée
- atteindre un objectif de temps de pratique par musique pour une période prédéterminée
- rester en-dessous d'un poids maximal

Notes:
On procédera avec un pseudo algorithme de Djikstra: on peut donc obtenir plusieurs possiblités d'organsation
sans avoir à recommencer les calculs.
Ces calculs prendront néanmoins beaucoup de temps, on va devoir optimiser un max.

Build:
./src/com.polyjam.pm$ javac -d ../../build *.class
./build$  jar cfe PratiqueMaster.jar com.polyjam.pm.Main com/polyjam/pm/*.class