# Projet programmation Web - Serveur (Programmation concurrente)

## Présentation du projet

Le projet a pour objectif de gérer grâce à un serveur les réservations, les emprunts et les retours de documents d’une bibliothèque.


Ce projet utilise la programmation concurrente car les documents sont des ressources partagées qui peuvent à l’origine de conflits entre les différents services possible dans la bibliothèque. Afin d’éviter d’éventuels conflits l’accès aux ressources partagées les sections critiques ont été verrouillés au niveau de la bibliothèque qui va se charger de gérer l’accès aux ressources par les clients. Les verrous se trouvent dans la bibliothèque afin de simplifier l’ajout d’éventuels nouveaux services. 

### Côté Client
Il y a un client pour chaque service possible dans un bibliothèque c’est-à-dire un client qui emprunte un document, un autre pour faire la réservation d’un document emprunté, enfin un client pour retourner un livre emprunté. Chaque client sera représenté par un socket par laquelle il pourra accéder au serveur en entrant le port et l’hôte du serveur lié au service demandé par le client.

### Côté Serveur
Il y a autant de serveurs que de services disponibles dans la bibliothèque. En fonction du port de connexion du client , le serveur va se charger de rediriger le client vers le serveur adapté à son port. Les serveurs dans ce projet tournent en boucle car on considère que tout fonctionne 24h/24. Pour la conception de classe bibliothèque le design pattern Singleton a été utilisé car on considère que les services sont disponibles uniquement dans une seule bibliothèque.

### Bibliothèque
Il y a une seule et unique bibliothèque qui est chargé de gérer les emprunts, les réservations et les retours de documents en fonction de son type et les éventuelles erreurs qui peuvent se produire au cours d’un emprunt, d’une réservation ou d’un retour. 