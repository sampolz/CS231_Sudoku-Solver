/*
 * Sam Polyakov
 * SudokuTests.java
 * 03/30/2023
 * Project 5
 * CS231 B
 */


 public class SudokuTests{
    public static void main(String[] args) {

            // testing findNextValue(Cell cell)
            {
                // setup
                Sudoku sudoku = new Sudoku(20);
                Cell cell = sudoku.board.get(0, 0);

                // verify
                int imposs = 0;
                for (int i = 0; i < 10; i++){
                    if (!(sudoku.board.validValue(0, 0, i))){
                        imposs = i;
                    }
                }
                System.out.println("Value can't be: " + imposs);
                // System.out.println(sudoku.board.validValue(0, 0, imposs));
                System.out.println(sudoku.findNextValue(cell) + " != " + imposs);

                // test
                assert sudoku.findNextValue(cell) != imposs : "Error in Sudoku::findNextValue(Cell cell)";
            }

            // testing findNextCell()
            {
                // setup
                Sudoku sudoku = new Sudoku(20);

                // verify
                System.out.println(sudoku.findNextCell().getValue() + " != 0");

                // test
                assert sudoku.findNextCell().getValue() != 0 : "Error in Sudoku::findNextCell()";

                // additional test cases
                for (int i = 0; i < Board.SIZE; i++){
                    for (int j = 0; j < Board.SIZE; j++){
                        Cell cell = sudoku.board.get(i, j);
                        if (sudoku.board.value(i, j) == 0){
                            int value = sudoku.findNextValue(cell);
                            if (value != 0){
                                cell.setValue(value);
                                System.out.println(sudoku.findNextCell().getValue() + " != 0");
                                assert sudoku.findNextCell().getValue() != 0 : "Error in Sudoku::findNextCell()";
                                return;
                            }
                        }
                    }
                }
            }
        }
}