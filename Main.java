import graph.DirectedGraph;
import graph.DirectedGraphReader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Labo1 - Implémentation de l'algorithme de Tarjan
 * Ecrit le résultat dans un fichier result.txt
 * @author Maxime Scharwath
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String dataFolder = "data/"; // Dossier contenant les fichiers de données

        // Récupération du nom du fichier
        String[] filenames = new String[]{
               // "G7_1.txt", exemple de graphe plus petit pour verification manuelle
                "G500_1.txt",
                "G500_2.txt",
                "G500_3.txt",
                "G1000_1.txt",
                "G1000_2.txt",
                "G1000_3.txt",
                "G2000_1.txt",
                "G2000_2.txt",
                "G2000_3.txt",
                "G4000_1.txt",
                "G4000_2.txt",
                "G4000_3.txt"
        };

        // Création du fichier de sortie
        File outFile = new File("./results.txt");
        outFile.getParentFile().mkdirs();
        PrintWriter outWriter = new PrintWriter(outFile);

        for (String filename : filenames) {
            DirectedGraph graph = DirectedGraphReader.fromFile(dataFolder + filename); // on récupère le graphe
            Tarjan tarjan = new Tarjan(graph); // on crée un objet Tarjan
            tarjan.run(); // on lance l'algorithme

            String resultString = "Le graphe du fichier \"" + filename + "\" a " + tarjan.getNbComponent() + " composantes fortement connexes.";
            System.out.println(resultString); // on affiche le résultat
            outWriter.println(resultString); // on écrit le résultat dans le fichier de sortie
        }
        outWriter.close();
    }
}