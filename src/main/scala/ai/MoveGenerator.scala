package ai

import game.Game

class MoveGenerator() {

   def bestMove(game: Game): Game  = ???

   private def nextGamesGen(game: Game): List[Game] = game.board.getEmptyCells match {
     case Nil => Nil
     case cells => cells.map(cell => game.toGame(cell.x, cell.y))
   }



  private def isThereWinner(games: List[Game]): Option[Game] = games.find(game => game.winner.isDefined)

  private def sessionGen(session: List[Game]) = ???


}

object MoveGenerator{

  def apply(): MoveGenerator = new MoveGenerator()
}
