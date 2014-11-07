package controllers

import data.ServiceClient
import play.api.mvc.{Cookie, Action, Controller}
import play.api.libs.concurrent.Execution.Implicits._

/**
 * Created by Aleksey Voronets on 04.11.14.
 */
 object User extends Controller
 {
  def index(embed: Boolean) = Action.async
   {
      val tweets = ServiceClient.makeServiceCall("tweets")
      val following = ServiceClient.makeServiceCall("following")
      val followers = ServiceClient.makeServiceCall("followers")

      Console.println("It doesn't block!")

      for {
          tweetCount <- tweets
          followingCount <- following
          followersCount <- followers
      } yield
      {
          if (embed) Ok(views.html.user.userBody(tweetCount.toInt, followingCount.toInt, followersCount.toInt)).withCookies(Cookie("foo", "bar"))
          else Ok(views.html.user.user(tweetCount.toInt, followingCount.toInt, followersCount.toInt))
      }
   }
 }
