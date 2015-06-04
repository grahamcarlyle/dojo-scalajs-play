package controllers

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicReference

import play.api.libs.json.{JsArray, JsObject, Json}
import play.api.mvc._

case class BodyStat(weight: Double, height: Double, date: LocalDate)

object Application extends Controller {

  val initialData = Seq(
    BodyStat(24.5, 1.5, LocalDate.parse("2015-05-31")),
    BodyStat(24.3, 1.5, LocalDate.parse("2015-06-01")),
    BodyStat(23.3, 1.5, LocalDate.parse("2015-05-02")),
    BodyStat(23.0, 1.49, LocalDate.parse("2015-05-03"))
  )
  val allRecordings = new AtomicReference(Vector[BodyStat](initialData:_*))

  def index = Action {
    Ok(views.html.index())
  }

  def recordings = Action {
    val jsonRecordings = allRecordings.get().map(stat =>
      Json.obj(
        "weight" -> stat.weight,
        "height" -> stat.height,
        "date" -> stat.date.format(DateTimeFormatter.ISO_LOCAL_DATE)
      )
    ).toSeq
    Ok(Json.obj("recordings" -> JsArray(jsonRecordings)))
  }
}
