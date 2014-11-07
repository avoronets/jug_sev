package ui

import play.api.libs.iteratee.Iteratee
import play.api.mvc.{Codec, Result}
import play.twirl.api.Html
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.Future

/**
 * Created by Aleksey Voronets on 04.11.14.
 */
object Parser
{
  def readBody(result: Result)(implicit codec: Codec): Future[Html] =
  {
    result.body.run(Iteratee.consume()).map(bytes => Html(new String(bytes, codec.charset)))
  }
}
