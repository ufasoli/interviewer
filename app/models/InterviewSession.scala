package models

import java.util.Date
import scala.slick.lifted.ColumnBase

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 15.11.13
 * Time: 06:39
 *
 */
case class InterviewSession(
                    id:Option[Long],
                    sessionDate:Date = new Date(),
                    interviewer:String = "anonymous",
                    interviewee:String = "anonymous",
                    comments:Option[String]) {

}
import play.api.db.slick.Config.driver.simple._

class InterviewSessions extends Table[InterviewSession]("INTERVIEW_SESSION") with AutoIncrementable[InterviewSession]{
  import conversion._

  def sessionDate = column[Date]("SESSION_DATE")
  def interviewer = column[String]("INTERVIEWER")
  def interviewee = column[String]("INTERVIEWEE")
  def comments    = column[String]("COMMENTS")

  def * = id.? ~ sessionDate ~ interviewer ~ interviewee ~ comments.? <> (InterviewSession.apply _, InterviewSession.unapply _)
}