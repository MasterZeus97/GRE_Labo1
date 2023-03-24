// TODO: refactor le nom du package groupeX avec le bon numéro de groupe (SHIFT + F6)
package gre.lab1.groupe13;

import gre.lab1.gui.MazeGenerator;
import gre.lab1.gui.MazeBuilder;
import gre.lab1.gui.Progression;

import java.util.Collections;

// TODO: javadoc

/**
 * Classe DFSMazeGenerator. Cette classe permet de générer un labyrinthe à partir d'un graphe représentant un carré de
 * cases côtes à côtes.
 */
public final class DFSMazeGenerator implements MazeGenerator {
  /**
   * Méthode pour générer le labyrinthe.
   * @param builder Un builder à qui déléguer les modifications de la structure de données.
   * @param from Sommet de départ, si l'algorithme utilisé en nécessite un.
   */
  @Override
  public void generate(MazeBuilder builder, int from) {
    // TODO: A implémenter
    //  NOTES D'IMPLÉMENTATION :
    //  Afin d'obtenir l'affichage adéquat, indiquer la progression (en tant que label du sommet traité) :
    //  - PROCESSING, en pré-traitement;
    //  - PROCESSED, en post-traitement.
    //  Le labyrinthe n'a que des murs au début de la construction, il faut donc créer les passages en
    //  supprimant des murs.

    dfs(builder, from);
  }

  /**
   * Méthode dfs : Méthode permettant l'exploration en profondeur d'un graphe. Le choix du voisin à explorer ensuite
   * est défini de manière aléatoire. Cette méthode peux crasher si trop d'appel récursifs sont effectués à la suite
   * (Une profondeur entre 6000 et 9900).
   * @param builder : Un builder à qui déléguer les modifications de la structure de données. Est utilisé ici pour
   *                  modifier les labels des sommets visités.
   * @param node : Sommet à partir du quel faire un DFS
   */
  void dfs(MazeBuilder builder, int node) {
    builder.progressions().setLabel(node, Progression.PROCESSING);

    var neighbors = builder.topology().neighbors(node);

    Collections.shuffle(neighbors);

    // Appel récursif de dfs sur tous les voisins du sommet actuel
    for (var neighbor : neighbors) {
      if(builder.progressions().getLabel(neighbor) == Progression.PENDING) {
        builder.removeWall(node, neighbor);
        dfs(builder, neighbor);
      }
    }
    builder.progressions().setLabel(node, Progression.PROCESSED);
  }
}
