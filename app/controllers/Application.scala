package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import com.dean.TheGymScraper.scrapers.SoupScrape
import com.dean.TheGymScraper.gymdata.GymUsageData
import models.UserData
import play.api.libs.json.Json

object Application extends Controller {
  def index = Action {
    Ok(views.html.index(""))
  }

  val form = Form(
    tuple(
      "email" -> text,
      "pin" -> number))

  def submit = Action { implicit request =>
    println("Submit called")
    try {
    val (email, pin) = form.bindFromRequest.get
    val gymUsage = retrieveGymUsage(email, pin.toString())
    val userData = mapToUserDataObject(gymUsage)
    Ok(views.html.gymusage(userData))
    } catch {
      case _ : Throwable => { println("Uh oh! Could not scrape")
        Ok(views.html.index("Oops! Could not connect to TheGym - please ensure you entered the correct email/pin.")) }
    }
  }

  def retrieveGymUsage(user: String, pin: String): GymUsageData = {
    val soup = new SoupScrape(user, pin)
    val gymUsage = new GymUsageData(soup)
    gymUsage

  }

  def mapToUserDataObject(gymUsageData: GymUsageData): UserData = {
    val userData = new UserData(gymUsageData.getTotalNumOfSessions,
      gymUsageData.getAverageNumOfSessionsPerWeek,
      gymUsageData.getAverageNumOfSessionsPerMonth,
      gymUsageData.getAverageSessionLengthMins,
      gymUsageData.getTotalSessionLengthMins,
      gymUsageData.getDaysSinceFirstAndLastSession,
      gymUsageData.getDaysSinceFirstSessionAndNow)
    userData
  }

}