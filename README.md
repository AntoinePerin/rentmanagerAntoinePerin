# rentmanagerAntoinePerin

Antoine PERIN
antoine.perin@epfedu.fr
10/03/202

Exercice Atteint :
- Injection des dépendances
- Les beans
- Ajout des contraintes
- Avoir la possibilité de créer un Client.
- Pouvoir lister tous les Clients.
- Avoir la possibilité de créer un Véhicule.
- Pouvoir lister tous les Véhicules.
- Pouvoir supprimer un Client.
- Pouvoir supprimer un Véhicule.
- Créer une Réservation.
- Lister toutes les Réservations.
- Lister toutes les Réservations associées à un Client donné.
- Lister toutes les Réservations associées à un Véhicule donné.
- Supprimer une Réservation.
- Supprimer un client supprimer les reservations associées
- Supprimer un vheicule supprime les reservations associées
- Tableau de bord du nombre de reservations, de clients, de vehicules

-Difficultés rencontrées :

 - L'ajout des contraintes notament celle concernant le fait qu'une voiture ne puisse pas être reservé 30 jours de suite sans pause.
 - Les tests unitaire et les mock que je n'ai compris et que je n'ai pas réussi à faire
 - La configuration de maven a été un petit peu compliqué
 - La gestion des exception a également a été compliqué et je pense n'avoir pas tout compris
 - La fonction répondant à la contrainte "une voiture ne peux pas être réservé 2 fois le même jour" qui ne fonctionne pas
 - Je n'ai pas reussi à afficher un message d'erreur dans le navigateur lorsque qu'une contrainte empeche l'ajout d'un vehicle, d'un client, ou d'une reservation par exemple


- Commande pour lancer le site :
mvn tomcat7:run
