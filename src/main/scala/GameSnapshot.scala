import scala.collection.immutable.HashMap

case class GameSnapshot(snap: HashMap[(Int, Int), Player]){

  def getSnap: HashMap[(Int, Int), Player] = snap

}
