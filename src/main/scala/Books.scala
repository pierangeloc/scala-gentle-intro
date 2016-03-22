import scala.io.Source
import scala.util.matching.Regex

case class Book(id: Int, title: String, authors: List[String], topic: String)

object BooksRepository {
  private val ratingsPath = "/home/pierangeloc/Documents/projects/scala/presentations/scala-gentle-intro/src/main/resources/programming-books-ratings.txt"
  private val booksPath = "/home/pierangeloc/Documents/projects/scala/presentations/scala-gentle-intro/src/main/resources/programming-books.txt"

  val BookRow: Regex = "([^\\|].*)\\|([^\\|]*)\\|([^\\|]*)".r
  val RatingRow = "([^\\|].*)\\|([^\\|]*)".r

  def load(path: String = booksPath): List[Book] =
    Source.fromFile(path)
      .getLines().map{
    case BookRow(title, authors, topic) => (title, authors.split(",").toList, topic)
  }.toList.zipWithIndex.map{
    case ((title, author, topic), index) => Book(index + 1, title, author, topic)
  }

  lazy val books: Map[Int, Book] = load().map(book => (book.id, book)).toMap

  lazy val ratings: Map[Int, Int] =
    Source.fromFile(ratingsPath)
        .getLines().map {
      case RatingRow(id, rating) => (id.toInt, rating.toInt)
    }.toMap

}
