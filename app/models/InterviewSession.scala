package models

import java.util.Date
import scala.slick.lifted.{BaseTypeMapper, MappedTypeMapper}
import play.api.libs.json.{Reads, Writes}
import util.EnumUtils
import _root_.models.QuestionMode.QuestionMode
import _root_.models.SessionStatus.SessionStatus


/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 15.11.13
 * Time: 06:39
 *
 */

case class InterviewSession(
                             id: Option[Long],
                             sessionDate: Date = new Date(),
                             interviewer: String = "anonymous",
                             interviewee: String = "anonymous",
                             nbOfQuestions: Int,
                             questionMode: QuestionMode,
                             maxDifficultyLevelId: Option[Long],
                             sessionStatus: Option[SessionStatus],
                             comments: Option[String]) {

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

  def questionMode = column[QuestionMode]("QUESTION_MODE")

  def sessionStatus = column[SessionStatus]("SESSION_STATUS")

  def comments = column[String]("COMMENTS")


  def * =
    id.? ~ sessionDate ~ interviewer ~ interviewee ~ nbOfQuestions ~ questionMode ~ maxDifficultyLevelId.? ~ sessionStatus.? ~ comments.? <>(InterviewSession.apply _, InterviewSession.unapply _)

  //FK
  def fkDifficultyLevel = foreignKey("fk_difficultyLevel", maxDifficultyLevelId, DifficultyLevels)(_.id)
}


object QuestionMode extends Enumeration {
  type QuestionMode = Value

  val RANDOM = Value("RANDOM")
  val SEQUENTIAL = Value("SEQUENTIAL")
  val MOST_ASKED = Value("MOST_ASKED")
  val LESS_ASKED = Value("LESS_ASKED")

  implicit val questionModeEnumReads: Reads[QuestionMode] = EnumUtils.enumReads(QuestionMode)

  implicit def questionModeEnumWrites: Writes[QuestionMode] = EnumUtils.enumWrites

  implicit val questionModeMapper:BaseTypeMapper[this.Value] = MappedTypeMapper.base[this.Value, Int](_.id, this.apply)


}


object SessionStatus extends Enumeration {
  type SessionStatus = Value

  val NEW = Value("NEW")
  val IN_PROGRESS = Value("IN_PROGRESS")
  val ABANDONED = Value("ABANDONED")
  val ENDED = Value("ENDED")

  implicit val sessionStatusEnumReads: Reads[SessionStatus] = EnumUtils.enumReads(SessionStatus)

  implicit def sessionStatusEnumWrites: Writes[SessionStatus] = EnumUtils.enumWrites




  implicit val sessionStatusMapper:BaseTypeMapper[this.Value] = MappedTypeMapper.base[this.Value, Int](_.id, this.apply)



}





