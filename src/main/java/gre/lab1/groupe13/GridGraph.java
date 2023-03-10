package gre.lab1.groupe13;

import gre.lab1.graph.GridGraph2D;

import java.util.ArrayList;
import java.util.List;

// TODO: javadoc
public final class GridGraph implements GridGraph2D {
  /** Largeur de la grille. */
  private final int width;

  /** Hauteur de la grille. */
  private final int height;

  ArrayList<ArrayList<Integer>> graphEdges;

  /**
   * Construit une grille carrée.
   * @param side Côté de la grille.
   */
  public GridGraph(int side) {
    this(side, side);
  }

  /**
   * Construit une grille rectangulaire.
   * @param width Largeur de la grille.
   * @param height Hauteur de la grille.
   * @throws IllegalArgumentException si {@code width} ou {@code length} sont négatifs ou nuls.
   */
  public GridGraph(int width, int height) {
    if (width <= 0 || height <= 0)
      throw new IllegalArgumentException("Width: " + width + " and height: " + height + " must be positive");

    this.width = width;
    this.height = height;

    graphEdges = new ArrayList<>(width*height);

    for (int i = 0; i < width*height; i++) {
      graphEdges.add(new ArrayList<>());
    }
  }

  @Override
  public List<Integer> neighbors(int v) {
    return graphEdges.get(v);
  }

  @Override
  public boolean areAdjacent(int u, int v) {
    // TODO: A implémenter
    return graphEdges.get(u).contains(v);
  }

  @Override
  public void addEdge(int u, int v) {
    var edges = graphEdges.get(u);

    if(!edges.contains(v)) {
      edges.add(v);
      graphEdges.get(v).add(u);
    }
  }

  @Override
  public void removeEdge(int u, int v) {
    var edges = graphEdges.get(u);

    if(edges.contains(v)) {
      edges.remove((Integer)v);
      graphEdges.get(v).remove((Integer)u);
    }
  }

  @Override
  public int nbVertices() {
    return width * height;
  }

  @Override
  public boolean vertexExists(int v) {
    return v < nbVertices();
  }

  @Override
  public int width() {
    return width;
  }

  @Override
  public int height() {
    return height;
  }

  /**
   * Lie chaque sommet du graphe donné à tous ses voisins dans la grille.
   * @param graph Un graphe.
   */
  public static void bindAll(GridGraph graph) {
    for (int x = 0; x < graph.width; x++) {
      for(int y = 0; y < graph.height; y++) {
        int u = x + y * graph.width;

        if(x + 1 < graph.width) {
          int v = (x + 1) + y * graph.width;
          graph.graphEdges.get(u).add(v);
          graph.graphEdges.get(v).add(u);
        }

        if(y + 1 < graph.height) {
          int v = x + (y + 1) * graph.width;
          graph.graphEdges.get(u).add(v);
          graph.graphEdges.get(v).add(u);
        }
      }
    }
  }
}
