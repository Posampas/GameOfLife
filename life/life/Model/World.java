package life.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

enum WorldState {NEW, OLD}

public class World {
    WorldState worldState;
    Cell[][] world;
    int n;
    int generation = 1;
    JPanel worldPane;
    JLabel aliveLabel;
    JLabel generationLabel;


    public World(int n, JPanel worldPane, JLabel aliveLabel, JLabel generationLabel) {
        this.n = n;
        world = generateWorld(n);
        worldState = WorldState.NEW;
        this.worldPane = worldPane;
        this.aliveLabel = aliveLabel;
        this.generationLabel = generationLabel;
    }

    private Cell[][] generateWorld(int n) {
        Cell[][] world = new Cell[n][n];
        Random random = new Random();
        int ofsett = 11;
        int posY = 0;
        for (int i = 0; i < n; i++) {
            int posX = 0;
            for (int j = 0; j < n; j++) {
                world[i][j] = initializeNormalCell(posX, posY);
                posX += ofsett;
                if (random.nextBoolean()) {
                    world[i][j].bringToLife();
                }
            }
            posY += ofsett;
        }

//      Add neighbours to the cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                addNeighbours(world, i, j, world[i][j]);

            }
        }

        return world;
    }


    private void addNeighbours(Cell[][] world, int i, int j, Cell current) {

        for (int k = i - 1; k <= i + 1; k++) {


            for (int l = j - 1; l <= j + 1; l++) {
                int tempX = k;
                int tempY = l;
                if (tempX == -1 && tempY == -1) {
                    tempX = world.length - 1;
                    tempY = world.length - 1;

                } else if ((tempX == -1) && (tempY == world.length)) {
                    tempX = world.length - 1;
                    tempY = 0;
                } else if ((tempX == world.length) && (tempY == world.length)) {
                    tempX = 0;
                    tempY = 0;
                } else if ((tempX == world.length) && (tempY == -1)) {
                    tempX = 0;
                    tempY = world.length - 1;
                } else if ((tempX == -1)) {
                    tempX = world.length - 1;
                } else if (tempX == world.length) {
                    tempX = 0;
                } else if (tempY == -1) {
                    tempY = world.length - 1;
                } else if (tempY == world.length) {
                    tempY = 0;
                }

                if (!(tempX == i && tempY == j)) {
                    current.setNeighbour(world[tempX][tempY]);
                }
            }
        }


    }


    private Cell initializeNormalCell(int posX, int posY) {
        return new Cell(8, posX, posY);
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                result.append(world[i][j].toString());

                if (j == n - 1 && i < n - 1) {
                    result.append('\n');
                }
            }
        }
        return result.toString();
    }

    public void nextGeneraion() {
        // Loop throught all cell
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                world[i][j].checkNeighboursState();
            }
        }
//      Decide of the cell state:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Cell current = world[i][j];
                if (current.isAlive()) {
                    if (current.getNumberOfActiveNegbours() < 2) {
                        current.starve();
                    } else if (current.getNumberOfActiveNegbours() > 3) {
                        current.starve();
                    }
                } else {
                    if (current.getNumberOfActiveNegbours() == 3) {
                        current.bringToLife();
                    }
                }

            }
        }
        generation++;
        aliveLabel.setText("" + getAlive());
        generationLabel.setText("" +generation);

    }

    public int getAlive() {
        int alive = 0;
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world.length; j++) {
                if (world[i][j].isAlive()) {
                    alive++;
                }
            }
        }
        return alive;
    }

    public void drawWorld(Graphics2D graph) {

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                graph.setColor(world[i][j].getState());
                graph.fill(world[i][j].getRect());


            }

        }
    }
}
