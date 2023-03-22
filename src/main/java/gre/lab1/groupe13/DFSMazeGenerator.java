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
    dfs(builder, from);
  }
  
  void dfs(MazeBuilder builder, int node) {
    builder.progressions().setLabel(node, Progression.PROCESSING);

    var neighbors = builder.topology().neighbors(node);

    Collections.shuffle(neighbors);

    for (var neighbor : neighbors) {
      if(builder.progressions().getLabel(neighbor) == Progression.PENDING) {
        builder.removeWall(node, neighbor);
        dfs(builder, neighbor);
      }
    }
    builder.progressions().setLabel(node, Progression.PROCESSED);
  }
}
