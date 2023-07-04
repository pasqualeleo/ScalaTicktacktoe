case class Cell(x: Int, y:Int, player: Option[Player] = Option.empty) {

  def ==(cell: Cell):Boolean = x == cell.x && cell.y == y

  def setPlayer(player: Player): Cell = Cell(x,y, Option(player))

  def isEmpty: Boolean = player.isEmpty

  def toStringPlayer: String = player match {
    case Some(_:X) => "X"
    case Some(_:O) => "O"
    case _ => " "
  }
}

object Cell{
  def apply(x: Int, y:Int, player: Player): Cell = Cell(x, y, Option(player))
}

case class Board(cells: List[Cell],
                 currentPlayer: Player,
                 winner: Option[Player] = Option.empty){

  def getCell(x: Int, y: Int): Option[Cell] = cells.find(Cell(x,y) == _)

  def getEmptyCells:List[Cell] = cells.filter(_.isEmpty)

  private def setMove(cell: Cell): List[Cell] = cells.updated(cordToIndex(cell), cell.setPlayer(currentPlayer))

  private def isThereWinner(update: List[Cell]): Boolean = ???

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
