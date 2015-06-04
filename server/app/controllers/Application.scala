package controllers

import play.api.libs.json.Json
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def previousRecordings = Action {
    Ok(Json.obj("recordings" ->
      Json.arr(
        Json.obj("weight" -> 24.5, "height" -> 1.5, "date" -> "2015-05-31"),
        Json.obj("weight" -> 24.3, "height" -> 1.5, "date" -> "2015-06-01"),
        Json.obj("weight" -> 23.3, "height" -> 1.5, "date" -> "2015-06-02"),
        Json.obj("weight" -> 23.0, "height" -> 1.49, "date" -> "2015-06-03")
      )
    ))
  }
}
