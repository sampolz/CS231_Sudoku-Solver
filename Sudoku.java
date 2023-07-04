/*
 * Sam Polyakov
 * Sudoku.java
 * 03/30/2023
 * Project 5
 * CS231 B
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class Sudoku {
    // this Class creates the sudoku board and solves it
    public Board board;
    public LandscapeDisplay ld;
    // ArrayList<Cell> listOfCells = new ArrayList<Cell>();


    public Sudoku(int numVals){
        //creates a new Board with some number of pre-determined randomly placed values.
        board = new Board(numVals);
        ld = new LandscapeDisplay(board);
    }

    public Sudoku(int numVals, int size){
        //creates a new Board with some number of pre-determined randomly placed values.
        board = new Board(numVals, size);
        ld = new LandscapeDisplay(board);
    }



    public int findNextValue(Cell cell){
        // determines if there is a valid value for this cell that we haven't tried
        // returns that value if yes, returns 0 if no.
        int row = cell.getRow();
        int col = cell.getCol();

        for(int i  = cell.getValue() + 1; i <= board.SIZE; i++){
            if(board.validValue(row, col, i)){
                return i;
            }
        } return 0;
    }


    public Cell findNextCell(){
        //finds the next cell to go to and finds an appropriate value for it
        int rows = board.getRows();
        int cols = board.getCols();

        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                if(board.value(i, j) == 0){
                    int nextVal = findNextValue(board.get(i, j));
                    if(nextVal != 0){
                        board.set(i, j, nextVal);
                        return board.get(i, j);
                    } else {
                        return null;
                    }
                }
            }
        } return null;
    }


    public boolean solve(int delay) throws InterruptedException{
        // solves the sudoku board
        Stack<Cell> stack = new LinkedList<Cell>();

        int rows = board.getRows();
        int cols = board.getCols();

        int emptyCells = 0;

        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                if(board.value(i, j) == 0){
                    emptyCells++;
                }
            }
        }

        while(stack.size()<emptyCells){
            if (delay > 0){
                Thread.sleep(delay);
            }
            if (ld != null){
                ld.repaint();
            }
                    
            System.out.println(board);
            Cell next = findNextCell();

            while(next==null && stack.size()>0){
                Cell popped = stack.pop();
                int nextVal = findNextValue(popped);
                popped.setValue(nextVal);
                if(nextVal != 0){
                    next = popped;
                }
            }

            if(next == null){
                System.out.println("Could not solve");
                board.finished = true;
                return false;
            }

            else{
                stack.push(next);
            }
        } 
        System.out.println("Solved!");
        board.finished = true;
        return true;
    }


    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of locked values: ");
        int lockedVals = scanner.nextInt();

        System.out.println("Enter size (1, 4, 9, 16, 25): ");
        int size = scanner.nextInt();

        scanner.close();
        
        
        Sudoku sudoku = new Sudoku(lockedVals,size);
        System.out.println(sudoku.board);
        sudoku.solve(0);
        System.out.println(sudoku.board);

        // Cell cell = sudoku.board.get(0, 0); // get the top-left cell
        // System.out.println(sudoku.board.toString());
        // int nextValue = sudoku.findNextValue(cell); // find the next valid value for the cell
        // if (nextValue == 0) {
        //     System.out.println("No valid value found for cell (" + cell.getRow() + ", " + cell.getCol() + ")");
        // } else {
        //     System.out.println("Next valid value for cell (" + cell.getRow() + ", " + cell.getCol() + "): " + nextValue);
        // }
        // Cell nextCell = sudoku.findNextCell();
        // if(nextCell == null){
        //     System.out.println("No next cell");
        // }
        // else{
        //     System.out.println("Next cell: \n" + nextCell);
        // }
        // sudoku.solveFromArrayList();
        // System.out.println(sudoku.board);
    }
}
