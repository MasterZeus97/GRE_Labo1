// TODO: refactor le nom du package groupeX avec le bon numéro de groupe (SHIFT + F6)
package gre.lab1.groupe13;

import gre.lab1.graph.Graph;
import gre.lab1.gui.MazeGenerator;
import gre.lab1.gui.MazeBuilder;
import gre.lab1.gui.Progression;

import java.util.Collections;
import java.util.HashSet;

// TODO: javadoc
public final class DFSMazeGenerator implements MazeGenerator {
  @Override
  public void generate(MazeBuilder builder, int from) {
    // TODO: A implémenter
    //  NOTES D'IMPLÉMENTATION :
    //  Afin d'obtenir l'affichage adéquat, indiquer la progression (en tant que label du sommet traité) :
    //  - PROCESSING, en pré-traitement;
    //  - PROCESSED, en post-traitement.
    //  Le labyrinthe n'a que des murs au début de la construction, il faut donc créer les passages en
    //  supprimant des murs.

    var visted = new HashSet<Integer>();
    dfs(builder, visted, from);
  }


  void dfs(MazeBuilder builder, HashSet<Integer> visited, int node) {
    visited.add(node);
    builder.progressions().setLabel(node, Progression.PROCESSING);

    var neighbors = builder.topology().neighbors(node);

    Collections.shuffle(neighbors);

    for (var neighbor : neighbors) {
      if(!visited.contains(neighbor)) {
        builder.removeWall(node, neighbor);
        visited.add(neighbor);
        dfs(builder, visited, neighbor);
      }
    }
    builder.progressions().setLabel(node, Progression.PROCESSED);
  }
}
