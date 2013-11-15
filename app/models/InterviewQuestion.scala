package models

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 15.11.13
 * Time: 07:01
 *
 */
case class InterviewQuestion(sessionId:Long, questionId:Long, responseSatisfactionId: Long, comment:Option[String]) {

}

import play.api.db.slick.Config.driver.simple._


class InterviewQuestions extends Table[InterviewQuestion]("INTERVIEW_QUESTION"){
  import models.{InterviewSessions,Questions, ResponseSatisfactionLevels}


  def sessionId = column[Long]("SESSION_ID", O.NotNull)
  def questionId = column[Long]("QUESTION_ID", O.NotNull)
  def responseSatisfactionId = column[Long]("RESPONSE_SATISFACTION_ID", O.NotNull)
  def comment = column[String]("COMMENT")

  def * = sessionId ~ questionId ~ responseSatisfactionId ~ comment.? <> (InterviewQuestion.apply _, InterviewQuestion.unapply _)

  // compound pk
  def pk = primaryKey("iq_pk", (sessionId, questionId))

  // foreign keys
  def sessionFK = foreignKey("session_fk", sessionId, InterviewSessions)(_.id)
  def questionFK = foreignKey("question_fk", sessionId, Questions)(_.id)
  def responseSatisfactionFK = foreignKey("response_fk", responseSatisfactionId, ResponseSatisfactionLevels)(_.id)
}
