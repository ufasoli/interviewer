package models

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 12.11.13
 * Time: 17:28
 *
 */
import play.api.db.slick.Config.driver.simple._

case class ResponseSatisfactionLevel(id:Option[Long], satisfactionLevel:String, points:Int, comment:Option[String]) {

}

class ResponseSatisfactionLevels extends Table[ResponseSatisfactionLevel]("RESPONSE_SATISFACTION_LEVEL")
with AutoIncrementable[ResponseSatisfactionLevel]{

  def satisfactionLevel = column[String]("SATISFACTION_LEVEL")
  def points = column[Int]("POINTS")
  def comment = column[String]("COMMENT", O.Nullable)

  def * = id.? ~ satisfactionLevel ~ points ~ comment.? <> (ResponseSatisfactionLevel.apply _, ResponseSatisfactionLevel.unapply _)
}
