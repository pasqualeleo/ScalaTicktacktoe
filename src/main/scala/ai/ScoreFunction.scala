package ai

import game.Game
import player.Player

object ScoreFunction {
    def getScore(game: Game, depth: Int ): Double = game match {
      case Game(_, _, Some(p)) => 1000 * (1 / depth)
      case Game(_, p, _) if getNumberLineForWin(game, Some(p)) > 0
          => 100 * (1 / depth) * (1 + getNumberLineForWin(game, Some(p)))
      case _ => 0
    }

  private def getNumberLineForWin(game: Game, player: Option[Player]): Int =
    game
      .board
      .getLines
      .map(line => line.count(cell => cell.player == player))
      .count(_ == 2)

}

object prova{

  def main(args: Array[String]): Unit = {
    val game = Game()
    val game11X = game.toGame(1, 1)
    val game22O = game11X.toGame(2,2)
    val game33X = game22O.toGame(3,3)
    val game31O = game33X.toGame(3,1)
    val game13X = game31O.toGame(1,3)
    val game120 = game13X.toGame(1,2)
    val game23X = game120.toGame(2,3)

    println(ScoreFunction.getScore(game13X, 1))
    println(game13X.board.toString)

  }
}
