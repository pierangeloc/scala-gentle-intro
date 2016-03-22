import akka.actor.ActorSystem
import akka.pattern._

import scala.concurrent.Future
import scala.concurrent.duration._

/**
  * Cook Pasta with Futures
  */
object Pasta {

  implicit val actorSystem = ActorSystem("dev-jam")
  implicit val executionContext = actorSystem.dispatcher

  case class Water(temperature: Int)
  case class Pasta(softness: Double = 0)

  sealed trait Condiment
  case object Ragu extends Condiment
  case object Carbonara extends Condiment

  case class PrimoPiatto(pasta: Pasta, condiment: Condiment)

  def boil(targetTemperature: Int = 100): Future[Water] = {
    after(5 seconds, actorSystem.scheduler)(Future {
      Water(targetTemperature)
    })
  }

  def cookPasta(water: Water)(pasta: Pasta): Future[Pasta] = {
    if (water.temperature >= 98) {
      after(5 seconds, actorSystem.scheduler) (Future.successful {
        println("pasta is cooked")
        pasta.copy(softness = 0.7)
      })
    } else {
      after(500 milliseconds, actorSystem.scheduler) (Future.failed {
        println("temperature is not good")
        new RuntimeException("can't eat it")
      })
    }
  }

  def addCondiment(pasta: Pasta, condiment: Condiment): Future[PrimoPiatto] = {
    if (pasta.softness < 0.8 && pasta.softness > 0.6) {
      after(3 seconds, actorSystem.scheduler) (Future.successful{
        println("condiment ready, primo piatto is ready!")
        PrimoPiatto(pasta, Ragu)
      })
    } else {
      after(1 second, actorSystem.scheduler) (Future.failed {
        println("pasta is badly cooked")
        new RuntimeException("overcooked pasta")
      })
    }
  }


  def chore() = println("Choring")
}
