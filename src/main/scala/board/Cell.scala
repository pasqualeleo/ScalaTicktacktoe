package board

import player.{O, Player, X}

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
