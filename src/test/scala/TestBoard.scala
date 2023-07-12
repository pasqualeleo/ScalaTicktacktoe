import board.{Board, Cell}
import org.scalatest.wordspec.AnyWordSpec
import player.{O, X}

class TestBoard extends AnyWordSpec{

   "A board" should{
     val board = Board()
     "give Cell(1, 1, None) with (1,1) coodinate" in{
       assert(board.getCell(1, 1).contains(Cell(1, 1, None)))
     }

     "give Cell(1, 1, Some(X())) with (1,1) coodinate" in{
       val newBoard = board.toMove(1,1, X())
       assert(newBoard.getCell(1, 1).contains(Cell(1, 1, Some(X()))))
     }

     "return a list of rows" in {
       val expectedRows = List(
                 List(Cell(1, 1, None), Cell(1, 2, None), Cell(1, 3, None)),
                 List(Cell(2, 1, None), Cell(2, 2, None), Cell(2, 3, None)),
                 List(Cell(3, 1, None), Cell(3, 2, None), Cell(3, 3, None)))
       assert(board.getRows == expectedRows)
     }

     "return a list of column" in {
       val expectedColumns = List(
         List(Cell(1, 1, None), Cell(2, 1, None), Cell(3, 1, None)),
         List(Cell(1, 2, None), Cell(2, 2, None), Cell(3, 2, None)),
         List(Cell(1, 3, None), Cell(2, 3, None), Cell(3, 3, None)))
       assert(board.getColumns == expectedColumns)
     }

     "return a list of Diagonals" in {
       val expectedDiagonals = List(
         List(Cell(1, 1, None), Cell(2, 2, None), Cell(3, 3, None)),
         List(Cell(1, 3, None), Cell(2, 2, None), Cell(3, 1, None))
         )
       assert(board.getDiagonals == expectedDiagonals)
     }

     "return a list of column, row and diagonals" in {
       val expectedLine = List(
         List(Cell(1, 1, None), Cell(1, 2, None), Cell(1, 3, None)),
         List(Cell(2, 1, None), Cell(2, 2, None), Cell(2, 3, None)),
         List(Cell(3, 1, None), Cell(3, 2, None), Cell(3, 3, None)),
         List(Cell(1, 1, None), Cell(2, 1, None), Cell(3, 1, None)),
         List(Cell(1, 2, None), Cell(2, 2, None), Cell(3, 2, None)),
         List(Cell(1, 3, None), Cell(2, 3, None), Cell(3, 3, None)),
         List(Cell(1, 1, None), Cell(2, 2, None), Cell(3, 3, None)),
         List(Cell(1, 3, None), Cell(2, 2, None), Cell(3, 1, None))
       )
       assert(board.getLines == expectedLine)
     }

     "without empty cells is completed" in {
       val expectedBoard = List(
         Cell(1, 1, Some(O())), Cell(1, 2, Some(X())), Cell(1, 3, Some(X())),
         Cell(2, 1, Some(X())), Cell(2, 2, Some(O())), Cell(2, 3, Some(O())),
         Cell(3, 1, Some(O())), Cell(3, 2, Some(X())), Cell(3, 3, Some(X()))
       )
       assert(Board(expectedBoard).isCompleted)
     }

     "after move is changed" in {
       assert(board.getCell(1, 1).contains(Cell(1, 1, None)))
       val updateBoard = board.toMove(1, 1, X())
       assert(updateBoard.getCell(1, 1).contains(Cell(1, 1, X())))
     }

   }
}
