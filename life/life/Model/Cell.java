package life.Model;

import java.awt.*;

public class Cell {
    private boolean isAlive;
    private Rectangle rect;
    private int posX;
    private int posY;
    private CellState state;

    private Cell[] neighbourhood;
    private int numberOfNeigbourd ;
    private int neigbourCounter;  // Just for adding the cells
    private int numberOfActiveNeighbours = 0;


    public boolean isAlive() {
        return isAlive;
    }

    Cell(int numberOfNeigbourd, int posX, int posY) {
        starve();

        rect = new Rectangle(posX, posY ,10,10);
        neighbourhood = new Cell[numberOfNeigbourd];
        this.numberOfNeigbourd = numberOfNeigbourd;
    }

    public void setNeighbour(Cell cell) {
        if (neigbourCounter < numberOfNeigbourd) {
            neighbourhood[neigbourCounter++] = cell;
        }
    }

    public void bringToLife() {
        isAlive = true;
        state = CellState.ACTIVE;
    }

    public int getNumberOfActiveNegbours(){
        return numberOfActiveNeighbours;
    }

    public int getNeigbourCounter() {
        return neigbourCounter;
    }

    public void starve() {
        isAlive = false;
        state = CellState.NOTACIVE;
    }
    public void checkNeighboursState(){
        numberOfActiveNeighbours =0;
        for (Cell c : neighbourhood) {
            if(c.isAlive){
                numberOfActiveNeighbours++;
            }
        }
    }

    public Rectangle getRect() {
        return rect;
    }
    public Color getState(){
        return state.getColor();
    }

    @Override
    public String toString() {

        if (isAlive){
            return "O";
        }else {
            return " ";
        }
    }

    public void retrunStatesOfNeighbours(){
        for (Cell c: neighbourhood) {
            System.out.print(c.isAlive + " ");
        }
        System.out.println();
    }


}
