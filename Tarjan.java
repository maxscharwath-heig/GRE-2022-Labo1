import graph.DirectedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * DataVertex est une structure pour stocker les informations d'un sommet.
 */
class DataVertex {
    int low = 0;
    int dfsnum = 0;
    int scc = 0;
}

/**
 * Tarjan est une classe qui permet de trouver les composantes fortement connexes d'un graphe.
 *
 * @author Maxime Scharwath
 */
public class Tarjan {
    private final Stack<Integer> visitedNodeStack = new Stack<>(); //pile stockant les sommets visités
    private final DataVertex[] dataVertices; // tableau des données des sommets
    private final DirectedGraph graph; // graphe à traiter
    /**
     * Liste des composantes contenant ses sommets,
     * cette liste est construite au fur et à mesure de l'exploration du graphe.
     * Utilisé pour un affichage détaillé des composantes fortement connexes.
     */
    private final List<List<Integer>> SCC = new ArrayList<>();
    private int nbSCC = 0; // compteur pour la numérotation des composantes (K)
    private int dfnumCounter = 0; // compteur pour la numérotation dfsnum (N)

    /**
     * Constructeur principal de la classe.
     *
     * @param graph graphe à traiter
     */
    public Tarjan(DirectedGraph graph) {
        this.graph = graph;
        dataVertices = new DataVertex[this.graph.getNVertices()]; // création du tableau des données
    }

    /**
     * Constructeur avec lancement de l'algorithme de Tarjan à la construction de l'objet.
     *
     * @param graph   graphe à traiter
     * @param autoRun true si l'algorithme doit être lancé à la construction de l'objet.
     */
    public Tarjan(DirectedGraph graph, boolean autoRun) {
        this(graph);
        if (autoRun) {
            run();
        }
    }

    /**
     * Lance l'algorithme de Tarjan.
     * Cette méthode peut être appelée directement ou appelée par le constructeur.
     */
    public void run() {
        // on initialise les données à 0
        visitedNodeStack.clear();
        nbSCC = dfnumCounter = 0;
        for (int i = 0; i < graph.getNVertices(); i++) {
            dataVertices[i] = new DataVertex();
        }

        for (int i = 0; i < graph.getNVertices(); i++) {
            if (dataVertices[i].scc == 0) { // si le sommet n'a pas encore été traité
                SCC(i); // on lance le traitement pour le sommet i
            }
        }

        // on trie les sommets de chaque composante pour avoir un affichage ordonné.
        for (List<Integer> integers : SCC) {
            integers.sort(Integer::compareTo);
        }
    }

    /**
     * SCC permet de trouver les composantes fortement connexes d'un graphe.
     *
     * @param u sommet à traiter
     */
    private void SCC(int u) {
        ++dfnumCounter; // on incrémente le nombre de sommets
        dataVertices[u].dfsnum = dfnumCounter;
        dataVertices[u].low = dfnumCounter;
        visitedNodeStack.push(u); // on met le sommet dans la pile
        for (int v : graph.getSuccessorList(u)) { // pour chaque successeur de u
            if (dataVertices[v].dfsnum == 0) {
                SCC(v);
            }
            if (dataVertices[v].scc == 0) {
                dataVertices[u].low = Math.min(
                        dataVertices[u].low,
                        dataVertices[v].low
                ); // on met à jour le low
            }
        }
        if (dataVertices[u].low == dataVertices[u].dfsnum) {
            ++nbSCC; // on incrémente le nombre de composantes fortement connexes
            SCC.add(new ArrayList<>()); // on prépare la liste des composantes fortement connexes
            int w;
            do {// on vide la pile jusqu'à ce que le sommet u soit retiré
                w = visitedNodeStack.pop(); // on retire le sommet de la pile
                SCC.get(nbSCC - 1).add(w); // on ajoute le sommet à la composante permet de lister les sommets de la composante
                dataVertices[w].scc = nbSCC;
            } while (w != u);
        }
    }

    /**
     * Retourne le nombre de composantes fortement connexes du graphe.
     *
     * @return le nombre de composantes fortement connexes du graphe.
     */
    public Integer getNbComponent() {
        return nbSCC;
    }

    /**
     * Retourne la liste des composantes fortement connexes du graphe.
     *
     * @param i le numéro de la composante est compris de 0 à K-1.
     * @return la liste des composantes fortement connexes du graphe.
     */
    public List<Integer> getComponent(int i) {
        if (i < 0 || i >= nbSCC) {
            return null;
        }
        return SCC.get(i);
    }

    /**
     * Retourne les composantes fortement connexes du graphe sous forme de texte. Dans la forme :
     * Nombre de composantes fortement connexes : K
     * Composante 1 : [1, 2, 3, 4, 5]
     * Composante 2 : [6, 7, 8, 9, 10]
     * etc...
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre de composantes fortement connexes : ").append(nbSCC).append("\n");
        for (int i = 0; i < nbSCC; i++) {
            sb.append("Composante ").append(i + 1).append(" : ").append(SCC.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

}
