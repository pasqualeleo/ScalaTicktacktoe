package player

trait Player{

  def + (player: Player): Player
}

case class X() extends Player {
  override def + (player: Player): Player = player match {
    case p:X => p
    case _ => P()
  }
}

case class O() extends Player {
  override def + (player: Player): Player = player match {
    case p:O => p
    case _ => P()
  }
}

case class P() extends Player {
  override def + (player: Player): Player = P()
}

object prova{

  def main(args: Array[String]): Unit = {
    val p1 = X()
    val p2 = O()
    val p3 = O()

    val p4 = O()
    val p5 = O()
    val p6 = O()

    val p7 = X()
    val p8 = X()
    val p9 = X()

    val p10 = X()
    val p11 = O()
    val p12 = X()


    println(p1 + p2 + p3)
    println(p4 + p5 + p6)
    println(p7 + p8 + p9)
    println(p10 + p11 + p12)


  }
}