import org.scalatest.wordspec.AnyWordSpec
import player.{P, X}

class TestGame extends AnyWordSpec{

  "A Game" should{
    val game = Game()

    "at bagging has nothing winner and current player is X" in {
      assert(game.winner.isEmpty)
      assert(game.currentPlayer == X())
    }

    "after the X player has moved on (1,1) cell, return new game. The new game has current player X, nothing winner and " +
      "the (1,1) cell of board hosted by player X" in {
      val game1 = game.toGame(1,1)

      assert(game1.winner.isEmpty)
      assert(game1.currentPlayer == X())
      assert(game1.board.getCell(1,1).get.player.get == X())
    }

    "after some moves, the X player has completed first column. The last game has winner == Some(X())" in {
      val game1 = game.toGame(1,1)  //X
      val game2 = game1.toGame(3,3) //O
      val game3 = game2.toGame(3,1) //X
      val game4 = game3.toGame(2,2) //O
      val game5 = game4.toGame(2,1) //X

      val winnerCells = game5.board.getColumns.head

      assert(game5.winner.contains(X()))
      assert(winnerCells.forall(cell => cell.player.contains(X())))
    }

    "after some moves, there isn't other move. The last game has winner == Some(P()), board full" in {
      val game1 = game.toGame(1,1)  //X
      val game2 = game1.toGame(1,3) //0
      val game3 = game2.toGame(1,2) //X
      val game4 = game3.toGame(2,1) //0
      val game5 = game4.toGame(2,3) //X
      val game6 = game5.toGame(2,2) //0
      val game7 = game6.toGame(3,1) //X
      val game8 = game7.toGame(3,3) //0
      val game9 = game8.toGame(3,2) //X

      assert(game9.winner.contains(P()))
      assert(game9.board.isCompleted)

    }
  }



}
