package board
import player.Player

case class Board(cells: List[Cell]){

  def getCell(x: Int, y: Int): Option[Cell] = cells.find(Cell(x,y) == _)

  def getEmptyCells: List[Cell] = cells.filter(_.player.isEmpty)

  def getRows:List[List[Cell]] = cells.grouped(3).toList

  def getColumns:List[List[Cell]] = cells.sortBy(_.y).grouped(3).toList

  def getDiagonals: List[List[Cell]] =
    List(List(cells.head, cells(4), cells(8)), List(cells(2), cells(4), cells(6)))

  def getLines: List[List[Cell]] = getRows ::: getColumns ::: getDiagonals

  def isEmptyCell(x: Int, y: Int): Boolean = isEmptyCell(Cell(x, y))

  def isEmptyCell(cell: Cell): Boolean = cells(cordToIndex(cell)).player.isEmpty

  def isCompleted: Boolean = cells.forall(_.player.isDefined)

  def toMove(x: Int, y: Int, player: Player): Board = Board(updateCell(Cell(x,y, player)))

  override def toString: String = {
    cells
      .map(_.toStringPlayer)
      .grouped(3)
      .map(_.mkString(" | "))
      .mkString("\n--|---|---\n")
  }

  private def updateCell(cell: Cell): List[Cell] =
    cells.updated(cordToIndex(cell), cell)

  private def cordToIndex(cell: Cell): Int = cell match {
    case Cell(1,1, _) => 0
    case Cell(1,2, _) => 1
    case Cell(1,3, _) => 2
    case Cell(2,1, _) => 3
    case Cell(2,2, _) => 4
    case Cell(2,3, _) => 5
    case Cell(3,1, _) => 6
    case Cell(3,2, _) => 7
    case _ => 8
  }

}

object Board{

  def apply(): Board = Board(initBoard())

  def initBoard(): List[Cell] =
    List(Cell(1,1), Cell(1,2), Cell(1,3),
         Cell(2,1), Cell(2,2), Cell(2,3),
         Cell(3,1), Cell(3,2), Cell(3,3))
}


