/*
 * Sam Polyakov
 * CellTests.java
 * 03/30/2023
 * Project 5
 * CS231 B
 */


 public class CellTests {
    public static void main(String[] args) {
        // testing constructor
        {
            // setup
            Cell cell = new Cell();

            // verify
            System.out.println(cell.getValue() + " == 0");

            // test
            assert cell.getValue() == 0 : "Error in Cell::Cell()";
        }

        // testing setValue(int newVal)
        {
            // setup
            Cell cell = new Cell();
            cell.setValue(5);

            // verify
            System.out.println(cell.getValue() + " == 5");

            // test
            assert cell.getValue() == 5 : "Error in Cell::setValue(int newVal)";

            // additional test cases
            cell.setValue(10);
            System.out.println(cell.getValue() + " == 10");
            assert cell.getValue() == 10 : "Error in Cell::setValue(int newVal)";
        }

        // testing setLocked()
        {
            // setup
            Cell cell = new Cell();
            cell.setLocked(true);

            // verify
            System.out.println(cell.isLocked() + " == true");

            // test
            assert cell.isLocked() == true : "Error in Cell::setLocked(boolean lock)";

            // additional test cases
            cell.setLocked(false);
            System.out.println(cell.isLocked() + " == false");
            assert cell.isLocked() == false : "Error in Cell::setLocked(boolean lock)";
        }

        // testing getRow()
        {
            // setup
            Cell cell = new Cell(2, 3, 4);

            // verify
            System.out.println(cell.getRow() + " == 2");

            // test
            assert cell.getRow() == 2 : "Error in Cell::getRow()";

            // additional test cases
            cell = new Cell(0, 0, 1);
            System.out.println(cell.getRow() + " == 0");
            assert cell.getRow() == 0 : "Error in Cell::getRow()";
        }

        // testing getCol()
        {
            // setup
            Cell cell = new Cell(2, 3, 4);

            // verify
            System.out.println(cell.getCol() + " == 3");

            // test
            assert cell.getCol() == 3 : "Error in Cell::getCol()";

            // additional test cases
            cell = new Cell(0, 0, 1);
            System.out.println(cell.getCol() + " == 0");
            assert cell.getCol() == 0 : "Error in Cell::getCol()";
        }

        // testing getValue()
        {
             // setup
             Cell cell = new Cell(2, 3, 4);

             // verify
             System.out.println(cell.getValue() + " == 4");

             // test
             assert cell.getValue() == 4 : "Error in Cell::getValue()";

             // additional test cases
             cell = new Cell(0, 0, -1);
             System.out.println(cell.getValue() + " == -1");
             assert cell.getValue() == -1 : "Error in Cell::getValue()";
        }

        // testing isLocked()
        {
             // setup
             Cell cell = new Cell(2, 3, 4);

             // verify
             System.out.println(cell.isLocked() + " == false");

             // test
             assert cell.isLocked() == false : "Error in Cell::isLocked()";

             // additional test cases
             cell.setLocked(true);
             System.out.println(cell.isLocked() + " == true");
             assert cell.isLocked() == true : "Error in Cell::isLocked()";
        }
    }
}