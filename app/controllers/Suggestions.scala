package controllers

import data.ServiceClient
import play.api.mvc.{Action, Controller}
import play.api.libs.concurrent.Execution.Implicits._

/**
 * Created by Aleksey Voronets on 04.11.14.
 */
object Suggestions extends Controller
{
  def index(embed : Boolean) = Action.async
  {
      ServiceClient.makeServiceCall("suggestions").map
      { str =>
        if(embed)Ok(views.html.suggestion.suggestionBody(str.split(',')))
        else Ok(views.html.suggestion.suggestion(str.split(',')))
      }
  }
}
