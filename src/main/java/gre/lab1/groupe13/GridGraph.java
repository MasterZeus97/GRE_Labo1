package gre.lab1.groupe13;

import gre.lab1.graph.GridGraph2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe GridGraph. Permet de gérer toutes les connexions entre les sommets du graphe.
 */
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
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width: " + width + " and height: " + height + " must be positive");
    }

    this.width = width;
    this.height = height;

    graphEdges = new ArrayList<>(width*height);

    //Initialisation des listes d'adjacence
    for (int i = 0; i < width*height; i++) {
      graphEdges.add(new ArrayList<>());
    }
  }

  /**
   * Récupère une liste des voisin d'une case donnée
   * @param v Un sommet
   * @return : Liste des sommets adjacent somment v
   */
  @Override
  public List<Integer> neighbors(int v) {
    if(!vertexExists(v)) {
      throw new IndexOutOfBoundsException("\"v\" is out of bounds");
    }
    return new ArrayList<>(graphEdges.get(v));
  }

  /**
   * Test si deux sommets sont adjacents
   * @param u Un sommet.
   * @param v Un sommet.
   * @return : True si les deux sommets sont adjascent, False s'ils ne le sont pas
   */
  @Override
  public boolean areAdjacent(int u, int v) {
    checkValidity(u, v);
    return graphEdges.get(u).contains(v);
  }

  /**
   * Ajoute une arrête entre 2 sommet, les rendant ainsi adjacent.
   * @param u Une extrémité de l'arête.
   * @param v L'autre extrémité de l'arête.
   */
  @Override
  public void addEdge(int u, int v) {
    checkValidity(u, v);
    var edges = graphEdges.get(u);

    if(!edges.contains(v)) {
      edges.add(v);
      graphEdges.get(v).add(u);
    }
  }

  /**
   * Supprime une arrête entre 2 sommets
   * @param u Une extrémité de l'arête.
   * @param v L'autre extrémité de l'arête.
   */
  @Override
  public void removeEdge(int u, int v) {
    checkValidity(u, v);
    var edges = graphEdges.get(u);

    if(edges.contains(v)) {
      edges.remove((Integer)v);
      graphEdges.get(v).remove((Integer)u);
    }
  }

  /**
   * Permet de récupérer le nombre de sommets dans le graphe
   * @return : Nombre de sommets dans le graphe
   */
  @Override
  public int nbVertices() {
    return width * height;
  }

  /**
   * Permet de tester si
   * @param v Un sommet.
   * @return boolean : True si le sommet existe, False si non
   */
  @Override
  public boolean vertexExists(int v) {
    return v < nbVertices();
  }

  /**
   * Récupère la largeur du labyrinthe
   * @return int : Largeur du labyrinthe
   */
  @Override
  public int width() {
    return width;
  }

  /**
   * Récupère la hauteur du labyrinthe
   * @return int : Largeur du labyrinthe
   */
  @Override
  public int height() {
    return height;
  }

  /**
   * Lie chaque sommet du graphe donné à tous ses voisins directs afin de créer une grille.
   * @param graph Un graphe.
   */
  public static void bindAll(GridGraph graph) {

    // Parcours du graphe afin de générer une grille
    for (int x = 0; x < graph.width; x++) {
      for(int y = 0; y < graph.height; y++) {

        // Calcule l'index du sommet à la position (x, y)
        int u = x + y * graph.width;

        //Test si le sommet est sur le bord droit de la grille
        if(x + 1 < graph.width) {
          int v = (x + 1) + y * graph.width;
          graph.graphEdges.get(u).add(v);
          graph.graphEdges.get(v).add(u);
        }

        //Test si le sommet est sur le bord du bas de la grille
        if(y + 1 < graph.height) {
          int v = x + (y + 1) * graph.width;
          graph.graphEdges.get(u).add(v);
          graph.graphEdges.get(v).add(u);
        }
      }
    }
  }

  /**
   * Permet de vérifier si les deux valeurs passées en paramètre sont des sommets. Throw une IndexOutOfBoundsException
   * si un des deux n'existe pas.
   * @param u Un sommet à tester
   * @param v Un sommet à tester
   * @throws IndexOutOfBoundsException : un des index se trouve en dehors de la grille
   */
  void checkValidity(int u, int v) {
    if(!vertexExists(u)) {
      throw new IndexOutOfBoundsException("\"u\" is out of bounds");
    }
    if(!vertexExists(v)) {
      throw new IndexOutOfBoundsException("\"v\" is out of bounds");
    }
  }
}
