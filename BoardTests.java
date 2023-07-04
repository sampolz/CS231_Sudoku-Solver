/*
 * Sam Polyakov
 * BoardTests.java
 * 03/30/2023
 * Project 5
 * CS231 B
 */

 public class BoardTests {
    public static void main(String[] args) {
        // testing Board() and get method
        {
        //setup
        Board board1 = new Board();
        System.out.println("\n" + "Board 1:");
        System.out.println(board1.toString());

        //verify
        System.out.println("Cell at row 0, col 0: " + board1.get(0, 0));
        System.out.println("Cell at row 4, col 5: " + board1.get(4, 5));
        }
    
    // testing Board(String filename) and isLocked method
    {
        //setup
        Board board2 = new Board("Board1.txt");
        System.out.println("\n" + "Board 2:");
        System.out.println(board2.toString());

        //verify
        System.out.println("Is cell at row 0, col 0 locked? " + board2.isLocked(0, 0));
        System.out.println("Is cell at row 2, col 0 locked? " + board2.isLocked(2, 0));
    }
    
    // Testing numLocked method
    {
        //setup
        Board board2 = new Board("Board1.txt");
        System.out.println("\n" + "Board 2:");
        System.out.println(board2.toString());
        
        //verify
        System.out.println("Number of locked cells on Board 2: " + board2.numLocked());

        //test
        assert board2.numLocked() == 10 : "Error in Board::numlocked";
    }
    
    // Testing set method
    {
        //setup
        Board board2 = new Board("Board1.txt");
        System.out.println("\n" + "Board 2:");
        System.out.println(board2.toString());
        board2.set(0, 0, 2);

        //verify
        System.out.println("After changing cell at row 0, col 0:");
        System.out.println("\n" + board2.toString());

        //test
        assert board2.value(0, 0) == 2 : "Error in Board::set or Error in Board::value"; 
}
    
    
    // Testing value method
    {
        //setup
        Board board2 = new Board("Board1.txt");
        System.out.println("\n" + "Board 2:");
        System.out.println(board2.toString());

        //verify
        System.out.println("Value at cell row 0, col 1: " + board2.value(0, 1));

        //test
        assert board2.value(0, 1) == 0 : "Error in Board::value";
    }
    

    // Testing getRows and getCols methods
    {
        //setup
        Board board2 = new Board("Board1.txt");
        System.out.println("\n" + "Board 2:");
        System.out.println(board2.toString());

        //verify
        System.out.println("Number of rows on Board 1: " + board2.getRows());
        System.out.println("Number of columns on Board 1: " + board2.getCols());

        //test
        assert board2.getRows() == 9 : "Error in Board::getRows";
        assert board2.getCols() == 9 : "Error in Board::getCols";
    }
    

    // Testing validValue
    {
        //setup
        Board board4 = new Board();
        board4.set(0, 0, 1);
        board4.set(1, 0, 2);
        board4.set(2, 0, 3);
        board4.set(0, 1, 4);
        board4.set(1, 1, 5);
        board4.set(2, 1, 6);
        board4.set(0, 2, 7);
        board4.set(1, 2, 8);
        board4.set(2, 2, 9);

        //verify
        System.out.println("\n"  + "Board 4:");
        System.out.println(board4.toString());

        //test
        assert board4.validValue(0, 0, 1) == true : "Error in Board:validValue()";
        assert board4.validValue(0, 0, 4) == false : "Error in Board:validValue()";
        assert board4.validValue(0, 0, 5) == false : "Error in Board:validValue()";
        assert board4.validValue(0, 0, 8) == false : "Error in Board:validValue()";
        assert board4.validValue(1, 0, 1) == false : "Error in Board:validValue()";
        assert board4.validValue(1, 1, 8) == false : "Error in Board:validValue()";
        assert board4.validValue(2, 1, 1) == false : "Error in Board:validValue()";
    }


    //Testing Valid Solution
    {
        //Setup
         //setup
         Board board5 = new Board();
         board5.set(0, 0, 1);
         board5.set(1, 0, 2);
         board5.set(2, 0, 3);
         board5.set(0, 1, 4);
         board5.set(1, 1, 5);
         board5.set(2, 1, 6);
         board5.set(0, 2, 7);
         board5.set(1, 2, 8);
         board5.set(2, 2, 9);
 
         //verify
         System.out.println("\n"  + "Board 5:");
         System.out.println(board5.toString());

        //test
        assert board5.validSolution() == false : "Error in Board::validSolution";
    }

    }
 }