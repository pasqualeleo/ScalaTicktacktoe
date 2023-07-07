import board.Board
import player.{O, P, Player, X}


case class Game (board: Board,
                 currentPlayer: Player,
                 winner: Option[Player] = Option.empty){

  def toGame(x: Int, y: Int): Game = {
    val updatedBoard = board.toMove(x, y, currentPlayer)
    Game(updatedBoard, switchPlayer, checkWinner(updatedBoard))
  }

  def checkWinner(updatedBoard: Board): Option[Player] =
      updatedBoard.getLines
                  .filter(!_.map(_.player).contains(None))
                  .map(_.map(_.player.get))
                  .map(_.reduce(_ + _))     match {
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


object prova{

  def main(args: Array[String]): Unit = {

    println("Win X")
    val game = Game()
    val game1 = game.toGame(1,1) //X
    val game2 = game1.toGame(3,3) //O
    val game3 = game2.toGame(3,1) //X
    val game4 = game3.toGame(2,2) //O
    val game5 = game4.toGame(2,1) //X
    println(game.checkWinner(game.board))
    println(game.checkWinner(game1.board))
    println(game.checkWinner(game2.board))
    println(game.checkWinner(game3.board))
    println(game.checkWinner(game4.board))
    println(game.checkWinner(game5.board))
    println(game5.board.toString)
    println()
    println("Pair")
    val gamePair1 = Game()
    val gamePair2 = gamePair1.toGame(1,1) //X
    val gamePair3 = gamePair2.toGame(1,3) //0
    val gamePair4 = gamePair3.toGame(1,2) //X
    val gamePair5 = gamePair4.toGame(2,1) //0
    val gamePair6 = gamePair5.toGame(2,3) //X
    val gamePair7 = gamePair6.toGame(2,2) //0
    val gamePair8 = gamePair7.toGame(3,1) //X
    val gamePair9 = gamePair8.toGame(3,3) //0
    val gamePair10 = gamePair9.toGame(3,2) //X

    println(game.checkWinner(gamePair2.board))
    println(gamePair2.board.toString)
    println(game.checkWinner(gamePair3.board))
    println(gamePair3.board.toString)
    println(game.checkWinner(gamePair4.board))
    println(gamePair4.board.toString)
    println(game.checkWinner(gamePair5.board))
    println(gamePair5.board.toString)
    println(game.checkWinner(gamePair6.board))
    println(gamePair6.board.toString)
    println(game.checkWinner(gamePair7.board))
    println(gamePair7.board.toString)
    println(game.checkWinner(gamePair8.board))
    println(gamePair8.board.toString)
    println(game.checkWinner(gamePair9.board))
    println(gamePair9.board.toString)
    println(game.checkWinner(gamePair10.board))
    println(gamePair10.board.toString)

  }
}