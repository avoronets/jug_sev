package controllers

import play.api.mvc.{Action, Controller}
import ui.Parser
import play.api.libs.concurrent.Execution.Implicits._
/**
 * Created by Aleksey Voronets on 04.11.14.
 */
object Aggregator extends Controller
{
    def index = Action.async
    { request =>
        val userFuture = User.index(embed = true)(request)
        val suggestionsFuture = Suggestions.index(embed = true)(request)
        for
        {
            user <- userFuture
            suggestions <- suggestionsFuture

            userBody <- Parser.readBody(user)
            suggestionsBody <- Parser.readBody(suggestions)
        } yield
          {
              Ok(views.html.aggregator.aggregator(userBody,suggestionsBody))
          }
    }
}
