package board
import player.{Player, X}

import scala.annotation.tailrec

case class Board(cells: List[Cell],
                 currentPlayer: Player,
                 winner: Option[Player] = Option.empty){

  def getCell(x: Int, y: Int): Option[Cell] = cells.find(Cell(x,y) == _)

  def getEmptyCells: List[Cell] = cells.filter(_.isEmpty)



  def isThereWinner(updatedCells: List[Cell]): Boolean = {

    @tailrec
    def checkLine(updatedCells: List[Cell]): Boolean = updatedCells match {
      case Nil => false
      case h1::h2::h3::_ if h1.player == h2.player &&
                            h2.player == h3.player &&
                            h3.player.isDefined => true
      case _ :: _ :: _ ::t => checkLine(t)
    }

    def checkOblique(updatedCells: List[Cell]): Boolean = updatedCells match {
      case h1::_::_::_::h2::_::_::_::h3::_ if h1.player == h2.player &&
                                              h2.player == h3.player &&
                                              h3.player.isDefined => true
      case _::_::h1::_::h2::_::h3::_::_::_ if h1.player == h2.player &&
                                              h2.player == h3.player &&
                                              h3.player.isDefined => true
      case _ => false
    }

    val horiz = checkLine(updatedCells)
    val vert = checkLine(updatedCells.sortBy(_.y))
    val oblique = checkOblique(updatedCells: List[Cell])

    horiz || vert || oblique
  }


  private def setMove(cell: Cell): List[Cell] =
    cells.updated(cordToIndex(cell), cell.setPlayer(currentPlayer))

  override def toString: String = {
    cells
      .map(_.toStringPlayer)
      .grouped(3)
      .map(_.mkString(" | "))
      .mkString("\n--|---|---\n")
  }

  private def cordToIndex(cell: Cell): Int = cell match {
    case Cell(1,1, _) => 0
    case Cell(1,2, _) => 1
    case Cell(1,3, _) => 2
    case Cell(2,1, _) => 3
    case Cell(2,2, _) => 4
    case Cell(2,3, _) => 5
    case Cell(3,1, _) => 6
    case Cell(3,2, _) => 7
    case Cell(3,3, _) => 8
  }
}

object Board{

  def apply(): Board = Board(initBoard(), X())

  def initBoard(): List[Cell] =
    List(Cell(1,1), Cell(1,2), Cell(1,3),
         Cell(2,1), Cell(2,2), Cell(2,3),
         Cell(3,1), Cell(3,2), Cell(3,3))
}
