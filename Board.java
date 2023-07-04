/*
 * Sam Polyakov
 * Board.java
 * 03/30/2023
 * Project 5
 * CS231 B
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.Color;
// import java.util.*;
// import java.awt.*;
import java.awt.Graphics;


public class Board {
  // this class creates the board using cells
  public Cell[][] board;
  public static int SIZE;
  public Random rand;
  public boolean finished;
  public ArrayList<Cell> listOfCells = new ArrayList<Cell>();

  public Board() {
    //creates a new 2D array of Cells that is Board.Size by Board.Size, 
    //initializes each location in the grid with a new Cell with value 0.
    SIZE = 9;
    board = new Cell[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board[i][j] = new Cell(i, j, 0);
      }
    }
  }

  public Board(int numLocked, int size) {
    //creates a new 2D array of Cells that is size by size, 
    //initializes each location in the grid with a new Cell with value 0.
    //randomly choose numLocked cells which to give a fixed original value (1). 
    SIZE = 9;
    double sqrt = Math.sqrt(size);
    if (sqrt != Math.floor(sqrt)) {
      System.out.println("Invalid Size. Size must be a perfect square.");
      return;
    }
    if(size>25){
      System.out.println("Invalid Size. Size must be 25 or smaller. ");
    }
    SIZE = size;
    board = new Cell[SIZE][SIZE];
    Random rand = new Random();

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board[i][j] = new Cell(i, j, 0);
      }
    }

    int i = 0;
    while(i<numLocked){
      int num = rand.nextInt(SIZE)+1;
      int randRow = rand.nextInt(SIZE);
      int randCol = rand.nextInt(SIZE);

      if(validValue(randRow, randCol, num)){
        set(randRow, randCol, num);
        set(randRow, randCol, true);
        i++;
      }
    }
  }

  public Board(String filename) {
    //creates a new 2D array of Cells that is Board.Size by Board.Size, 
    //initializes each location in the grid with a new Cell with value 0.
    //takes in a String filename and calls the read method
    SIZE = 9;
    board = new Cell[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board[i][j] = new Cell(i, j, 0);
      }
    }

    this.read(filename);
  }

  public Board(int numLocked){
    //creates a new 2D array of Cells that is Board.Size by Board.Size, 
    //initializes each location in the grid with a new Cell with value 0.
    //randomly choose numLocked cells which to give a fixed original value (1). 
    SIZE = 9;
    board = new Cell[SIZE][SIZE];
    Random rand = new Random();

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board[i][j] = new Cell(i, j, 0);
      }
    }

    int i = 0;
    while(i<numLocked){
      int num = rand.nextInt(9)+1;
      int randRow = rand.nextInt(SIZE);
      int randCol = rand.nextInt(SIZE);

      if(validValue(randRow, randCol, num)){
        set(randRow, randCol, num);
        set(randRow, randCol, true);
        i++;
      }
    }
  }


  public Cell get(int row, int col) {
    // this returns the Cell at the given row and col
    return board[row][col];
  }

  public int getCols() {
    // returns the number of columns
    return SIZE;
  }

  public int getRows() {
    // returns the Cell at location r, c.
    return SIZE;
  }

  public boolean isLocked(int r, int c) {
    // returns whether the Cell at r, c, is locked.
    return board[r][c].isLocked();
  }

  public int numLocked() {
    // returns the number of locked Cells on the board.
    int counter = 0;

    for (int i = 0; i < getCols(); i++) {
      for (int j = 0; j < getRows(); j++) {
        if (board[i][j].isLocked() == true) {
          counter++;
        }
      }
    }
    return counter;
  }

  public int value(int r, int c) {
    // returns the value at Cell r, c.
    return board[r][c].getValue();
  }

  public void set(int row, int col, int value) {
    // sets the value of the Cell at r, c.
    if(board[row][col].isLocked()){
      System.out.println("This cell is locked.");
    }
    else{
      board[row][col].setValue(value);
    }
  }

  public void set(int row, int col, boolean locked) {
    // sets the locked field of the Cell at r, c.
    board[row][col].setLocked(locked);
  }


  public boolean validValue(int row, int col, int value) {
    // tests if the given value is a valid value at the given row and column of the board
    if (value >= 1 && value <= SIZE) {
      if(row > SIZE-1 || row < 0 || col > SIZE-1 || col < 0){
        return false;
      }

      for (int i = 0; i < SIZE; i++) {
        if(i == col){
          continue;
        }
        if (board[row][i].value == value) {
          return false;
        }
      }

      for (int i = 0; i < SIZE; i++) {
        if(i == row){
          continue;
        }
        if (board[i][col].value == value) {
          return false;
        }
      }

      int squareRoot = (int) Math.sqrt(SIZE);

      int startRow = squareRoot * (row / squareRoot);
      int startCol = squareRoot * (col / squareRoot);
      for (int i = startRow; i < startRow + squareRoot; i++){
        for(int j  = startCol; j < startCol + squareRoot; j++){
          if ((i!=row || j != col) && get(i, j).getValue() == value){
            return false;
          }
        }
      }

      return true;
    }

    else {
      return false;
    }
  }


  public boolean validSolution() {
    // checks if the board has a valid solution
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        int cellValue = board[i][j].getValue();
        if (validValue(i, j, cellValue) == false) {
          return false;
        }
      }
    }
    return true;
  }

  public void draw(Graphics g, int scale){
    // draws the board
    int squareRoot = (int) Math.sqrt(SIZE);
    for(int i = 0; i<getRows(); i++){
        for(int j = 0; j<getCols(); j++){
            get(i, j).draw(g, j*scale+5, i*scale+10, scale);
            if(i % squareRoot == 0 && i != 0){
              g.setColor(Color.BLACK);
              g.drawLine(0, i*scale-5, 800, i*scale-5);
            }
            if(j % squareRoot == 0 && j != 0){
              g.setColor(Color.BLACK);
              g.drawLine(j*scale-5, 0, j*scale-5, 800);
            }
        }
    } 
    
    if(finished){
        if(validSolution()){
            g.setColor(new Color(0, 127, 0));
            g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale*squareRoot+5, scale*SIZE-5);
        } else {
            g.setColor(new Color(127, 0, 0));
            g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale*squareRoot+5, scale*SIZE-5);
        }
    }
}

  public boolean read(String filename) {
    // reads a file
    try {
      // assign to a variable of type FileReader a new FileReader object, passing
      // filename to the constructor
      FileReader fr = new FileReader(filename);
      // assign to a variable of type BufferedReader a new BufferedReader, passing the
      // FileReader variable to the constructor
      BufferedReader br = new BufferedReader(fr);

      // assign to a variable of type String line the result of calling the readLine
      // method of your BufferedReader object.
      String line = br.readLine();
      int rowPointer = 0;
      // start a while loop that loops while line isn't null
      while (line != null) {
        // print line
        // System.out.println(line);
        // assign to an array of Strings the result of splitting the line up by spaces
        // (line.split("[ ]+"))
        String[] splitString = line.split("[ ]+");
        // print the size of the String array (you can use .length)
        // System.out.println(splitString.length);
        // use the line to set various Cells of this Board accordingly
        for (int i = 0; i < splitString.length; i++) {
          set(rowPointer, i, Integer.parseInt(splitString[i]));
          if (!splitString[i].equals("0")) {
            set(rowPointer, i, true);
          }
        }
        // assign to line the result of calling the readLine method of your
        // BufferedReader object.
        line = br.readLine();
        rowPointer++;
      }
      // call the close method of the BufferedReader
      br.close();
      return true;
    } catch (FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename);
    } catch (IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }

    return false;
  }

  public String toString() {
    // prints out board
    String result = "";
    int squareRoot = (int) Math.sqrt(SIZE);

    for (int row = 0; row < SIZE; row++) {
      if (row % squareRoot == 0 && row != 0) {
        result += "\n";
      }

      for (int col = 0; col < SIZE; col++) {
        if (col % SIZE == 0 && col != 0) {
          result += "\n";
        }

        else if (col % squareRoot == 0 && col != 0) {
          result += "  ";
        }

        result += board[row][col].getValue();
        result += " ";
      }
      result += "\n";
    }
    return result;
  }

  public static void main(String[] args) {
    Board board = new Board(5, 16);
    System.out.println(board.toString());
    // Board board = new Board(0);
    // System.out.println(board.toString());
    // System.out.println(board.numLocked() + " == 20");
    // System.out.println(board.value(5, 5) + " == 8");
    // System.out.println(board.getCols() + " == 9");
    // System.out.println(board.getRows() + " == 9");
    // System.out.println(board.isLocked(5, 5) + " == true");
    // System.out.println(board.isLocked(6, 5) + " == false");
    // System.out.println(board.validValue(5, 5, 10) + " == false");
    // System.out.println(board.validValue(5, 5, 5) + " == true");
    // board.set(0, 0, 1);
    // System.out.println(board.validValue(2, 2, 1) + " == false");
    // board.set(0,1,2);
    // board.set(0, 2, 9);
    // board.set(1,0,4);
    // board.set(1,1,5);
    // board.set(1,2,6);
    // board.set(2,0,7);
    // board.set(2,1,8);
    // System.out.println(board.toString());
    // System.out.println(board.validValue(2, 2, 1) + " == false");
    // System.out.println(board.validValue(2, 2, 3) + " == true");
    // System.out.println(board.validSolution() + " == false");
  }

}
