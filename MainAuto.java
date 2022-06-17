import graph.DirectedGraph;
import graph.DirectedGraphReader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Labo1 - Implémentation de l'algorithme de Tarjan
 * Avec lecture automatique des fichiers de graphes dans le répertoire "data" et
 * écriture dans le répertoire "data/out" des composantes fortement connexes de chaque graphe.
 * @author Maxime Scharwath
 */
public class MainAuto {
    public static void main(String[] args) {
        String dataFolder = "./data/"; // dossier contenant les fichiers de données
        String outFolder = "./data/out/"; // dossier de sortie
        String[] files = new File(dataFolder).list((dir, name) -> name.endsWith(".txt")); // récupère tous les fichiers .txt
        assert files != null;
        for (String file : files) {
            try {
                DirectedGraph graph = DirectedGraphReader.fromFile(dataFolder + file); // on récupère le graphe
                Tarjan tarjan = new Tarjan(graph); // on crée un objet Tarjan
                tarjan.run(); // on lance l'algorithme
                String outData = tarjan.toString(); // on récupère le résultat
                System.out.println("File: " + file + " has " + tarjan.getNbComponent() + " SCC"); // on affiche le nombre de SCC

                // on écrit le résultat dans un fichier
                File outFile = new File(outFolder + file);
                outFile.getParentFile().mkdirs();
                PrintWriter outWriter = new PrintWriter(outFile);
                outWriter.print(outData);
                outWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}