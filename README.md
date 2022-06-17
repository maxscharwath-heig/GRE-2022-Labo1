# GRE - TP 1 - Algorithme de Tarjan
Maxime Scharwath - 19.03.2022
## Objectif
Objectifs et travail à effectuer
Dans ce travail pratique vous devez programmer l’algorithme de Tarjan présenté en cours
pour le calcul des composantes fortement connexes d’un graphe et vérifier le bon comportement de votre mise en œuvre sur plusieurs graphes, de différentes tailles.
Chaque graphe est stocké dans un fichier texte dont la première ligne contient deux entiers.
Le premier est positif et correspond au nombre n de sommets du graphe, ces derniers étant
numérotés de 0 à n − 1. Le deuxième est positif ou nul et correspond au nombre m d’arcs
du graphe. Les m lignes suivantes définissent chacune un arc et contiennent deux entiers,
le premier correspondant au numéro de l’extrémité initiale de l’arc et le second au numéro
de son extrémité finale. Les deux classes fournies permettent de stocker et manipuler un
graphe orienté et de lire un tel graphe dans un fichier respectant le format précédent.

## Implémentation et utilisation
Le rendu de ce travail a deux programmes qui utilise la classe qui implémente l’algorithme de Tarjan.
- *Main.java* : Le programme principal qui permet de tester l’algorithme de Tarjan sur une liste de fichiers statiquement définis. Les résultats sont affichés dans la console et dans un fichier result.txt à la racine du projet.
- *MainAuto.java* : Le programme va automatiquement chercher les fichiers de graphes dans le répertoire `data/` et les traiter. Les résultats sont stockés dans le répertoire `data/out/` et dans le terminal.

## L'implémentation
L'implémentation de l'algorithme de Tarjan est faite dans la classe *Tarjan*.
Ce n'est pas une classe statique, elle est instanciée pour chaque graphe à traiter.
Par la suite il faut appeler la méthode *Tarjan.run()* pour lancer l'algorithme de Tarjan.
Ensuite nous pouvons utiliser les méthodes *Tarjan.getNbComponent()* et *Tarjan.getComponent(i)* pour récupérer les composantes fortement connexes du graphe.