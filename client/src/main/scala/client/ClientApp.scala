package client

import shared.Calcs
import org.scalajs.jquery.{JQueryEventObject, jQuery}

import scala.scalajs.js
import org.scalajs.dom
import dom.ext.Ajax
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow

import scala.scalajs.js.Date

object ClientApp extends js.JSApp {
  def main(): Unit = {
    println("Hello from the client")
    jQuery(setupUi _)
    Ajax.get("/previousRecordings").onSuccess({ case xhr =>
      val payload = js.JSON.parse(xhr.responseText)
      showRecordedBmi(payload.recordings.asInstanceOf[js.Array[js.Dynamic]])
    })
  }

  def setupUi(): Unit = {
    jQuery("#confirm").click(confirmClicked _)
    jQuery("#record").click(recordClicked _)
  }

  def confirmClicked(event: JQueryEventObject): Unit = {
    event.preventDefault()
    val weight = jQuery("#weight").value().toString.toDouble
    val height = jQuery("#height").value().toString.toDouble
    val bmi = Calcs.bmi(weight, height)
    showBmiForToday(bmi)
  }

  def showBmiForToday(bmi: Double): Unit = {
    val now = new Date()
    jQuery("#bmi").text(bmi.toString)
    jQuery("#today").text(now.toDateString())
    jQuery("#bmiSection").show()
  }

  def showRecordedBmi(recordings: js.Array[js.Dynamic]) = {
    val items = recordings.map(recording => {
      val bmi = Calcs.bmi(recording.weight.asInstanceOf[Double],
                          recording.height.asInstanceOf[Double])
      s"<li>Date: ${recording.date}, Weight: ${recording.weight}, Height: ${recording.height}, BMI: ${bmi}</li>"
    })
    jQuery("#previousRecordings").append("<h2>Previous recordings</h2>")
    val ul = jQuery("<ul>")
    ul.append(items)
    jQuery("#previousRecordings").append(ul)
  }

  def recordClicked(event: JQueryEventObject): Unit = {
    event.preventDefault()
    println("to do!")
  }
}
