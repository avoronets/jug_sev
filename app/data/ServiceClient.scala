package data

import play.api.libs.ws.WS
import scala.concurrent.Future
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._

/**
 * Created by Aleksey Voronets on 04.11.14.
 */

  object ServiceClient {
    def makeServiceCall(serviceName: String): Future[String] = {
      WS.url(s"http://localhost:9000/mock/$serviceName").get().map(_.body)
    }
  }
