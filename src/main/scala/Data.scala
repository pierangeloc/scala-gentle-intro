import scala.io.Source


object Data {
  val introPath = "./src/main/resources/intro.txt"

  lazy val lines = Source.fromFile(introPath).getLines().toList


  def parseInt(s: String): Option[Int] = try {
    Some(s.toInt)
  } catch {
    case ex => None
  }

  val squares = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 * v1
  }
}
