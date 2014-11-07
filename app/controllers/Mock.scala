package controllers
import play.api.libs.concurrent.Promise
import play.api.mvc.{Result, Action, Controller}
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.Future

/**
 * Created by Aleksey Voronets on 04.11.14.
 */

 object Mock extends Controller {

  val SUGGESTIONS = List("Aleksey Voronets", "Lespuh Andrey", "Martin Oderski")

  def mock(serviceName: String) = Action.async {
    serviceName match {
      case "tweets" => respond(data = "230", delay = 10)
      case "following" => respond(data = "167", delay = 10000)
      case "followers" => respond(data = "150", delay = 40)
      case "suggestions" => respond(data = SUGGESTIONS.mkString(","), delay = 20)
    }

  }

  private def respond(data: String, delay: Long): Future[Result] = Promise.timeout(Ok(data), delay)

 }
