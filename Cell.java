/*
 * Sam Polyakov
 * Cell.java
 * 03/30/2023
 * Project 5
 * CS231 B
 */

import java.awt.*;

public class Cell {
    // this class creates cells to be filled in with different sudoku values
    public int row;
    public int col;
    public int value;
    public boolean locked;

    public Cell(){
        //initialize all values to 0 or false
        row = 0;
        col = 0;
        value = 0;
        locked = false;
    }

    public Cell(int row, int col, int value){
        //initialize the row, column, and value fields to the given parameter values. Set the locked field to false
        this.row = row;
        this.col = col;
        this.value = value;
        locked = false;
    }

    public Cell(int row, int col, int value, boolean locked){
        //initialize all of the Cell fields given the parameters.
        this.row = row;
        this.col = col;
        this.value = value;
        this.locked = locked;
    }

    public int getRow(){
        //return the Cell's row index.
        return row;
    }
    public int getCol(){
        //return the Cell's column index.
        return col;
    }

    public int getValue(){
        //return the Cell's value.
        return value;
    }

    public void setValue(int newval){
        //set the Cell's value.
        value = newval;
    }

    public boolean isLocked(){
        // return the value of the locked field.
        return locked;
    }

    public void setLocked(boolean lock){
        //set the Cell's locked field to the new value.
        locked = lock;
    }

    public String toString(){
        //return a string with the Cell's numeric value.
        return("Row: "+getRow() + "\nCol: " +getCol()+ "\nVal: "+getValue());
    }

    public void draw(Graphics g, int x, int y, int scale){
        // draws each cell
        if(getValue() <= 9){
            char toDraw = (char) ((int) '0' + getValue());
            g.setColor(isLocked()? Color.BLUE : Color.RED);
            g.drawChars(new char[] {toDraw}, 0, 1, x, y);
        }
        else if(getValue() > 9 && getValue() <= 19){
            char toDraw1 = (char) ((int) '1');
            char toDraw2 = (char) ((int) '0' + getValue()-10);
            g.setColor(isLocked()? Color.BLUE : Color.RED);
            g.drawChars(new char[] {toDraw1}, 0, 1, x, y);
            g.drawChars(new char[] {toDraw2}, 0, 1, x+8, y);
        }
        else{
            char toDraw1 = (char) ((int) '2');
            char toDraw2 = (char) ((int) '0' + getValue()-20);
            g.setColor(isLocked()? Color.BLUE : Color.RED);
            g.drawChars(new char[] {toDraw1}, 0, 1, x, y);
            g.drawChars(new char[] {toDraw2}, 0, 1, x+8, y);

        }

    }

    public static void main(String[] args) {
        Cell cell1 = new Cell(5, 5, 7, false);
        Cell cell2 = new Cell(6, 6, 6, true);
        Cell cell3 = new Cell(1, 1, 2, false);

        System.out.println(cell1.toString());
        System.out.println(cell2.toString());
        System.out.println(cell3.toString());
    }
    
}
