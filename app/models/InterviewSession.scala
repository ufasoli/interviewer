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
                             id: Option[Long],
                             sessionDate:Date = new Date(),
                             interviewer: String = "anonymous",
                             interviewee: String = "anonymous",
                             nbOfQuestions: Int,
                             questionMode: String,
                             maxDifficultyLevelId: Option[Long],
                             comments: Option[String]) {

}

object QuestionMode extends Enumeration {
  val RANDOM, SEQUENTIAL, MOST_ASKED, LESS_ASKED = Value
}

import play.api.db.slick.Config.driver.simple._

class InterviewSessions extends Table[InterviewSession]("INTERVIEW_SESSION") with AutoIncrementable[InterviewSession] {

  import conversion._
  import models.DifficultyLevels

  def sessionDate = column[Date]("SESSION_DATE")

  def interviewer = column[String]("INTERVIEWER")

  def interviewee = column[String]("INTERVIEWEE")

  def nbOfQuestions = column[Int]("NB_OF_QUESTIONS")

  def maxDifficultyLevelId = column[Long]("MAX_DIFFICULTY_LEVEL_ID")

  def questionMode = column[String]("QUESTION_MODE")

  def comments = column[String]("COMMENTS")



  def * =
    id.? ~ sessionDate ~ interviewer ~  interviewee ~ nbOfQuestions ~  questionMode ~ maxDifficultyLevelId.? ~ comments.? <> (InterviewSession.apply _, InterviewSession.unapply _)

  //FK
  def fkDifficultyLevel = foreignKey("fk_difficultyLevel", maxDifficultyLevelId, DifficultyLevels)(_.id)
}