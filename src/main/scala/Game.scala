import board.Board
import player.{O, P, Player, X}


case class Game(board: Board,
                currentPlayer: Player,
                winner: Option[Player] = Option.empty){

  def toGame(x: Int, y: Int): Game = winner match {
    case Some(_) => this //
    case None if board.isEmptyCell(x, y) =>
      val updatedBoard = board.toMove(x, y, currentPlayer)
      Game(updatedBoard, switchPlayer, checkWinner(updatedBoard))
    case _ => this
  }

  private def checkWinner(updatedBoard: Board): Option[Player] =
      updatedBoard.getLines
                  .filter(!_.map(_.player).contains(None))
                  .map(_.map(_.player.get))
                  .map(_.reduce(_ + _))  match {
        case l if l.contains(X()) => Some(X())
        case l if l.contains(O()) => Some(O())
        case l if l.length == 8 => Some(P())
        case _ => None
  }

  private def switchPlayer: Player = currentPlayer match {
    case _: O => X()
    case _: X => O()
  }
}

object Game{
  def apply(): Game = Game(Board(), X(), None)
}

