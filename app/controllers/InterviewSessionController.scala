package controllers

import _root_.util.SimpleResponse
import play.api.mvc.Controller
import models._
import play.api.Play.current
import play.api.libs.json._
import _root_.util.serializers.json.JsonWritters._
import java.util.Date
import play.api.db.slick._
import models.InterviewSessions


/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 16.01.14
 * Time: 11:05
 *
 */
object InterviewSessionController extends Crud[InterviewSession, InterviewSessions] with Controller {


  def newSession = DBAction(parse.json) {
    implicit rs =>

      val json = rs.body

      val res = Seq(
        (json \ "interviewer").validate[String],
        (json \ "interviewee").validate[String],
        (json \ "nbOfQuestions").validate[Int],
        (json \ "maxDifficultyLevelId").validate[Int]

      )


      if (hasErrors(res)) {

        BadRequest(Json.toJson(SimpleResponse(
          res.filter(r => {
            r.isInstanceOf[JsError]
          }).toString())))
      }
      else {


        val interviewSession = InterviewSession(
          None,
          new Date(),
          (json \ "interviewer").as[String],
          (json \ "interviewee").as[String],
          (json \ "nbOfQuestions").as[Int],
          QuestionMode.SEQUENTIAL,
          (json \ "maxDifficultyLevelId" ).asOpt[Long],
          Some(SessionStatus.NEW),
          None
        )

        val interviewSessionId =   InterviewSessions.autoInc.insert(interviewSession)



        Ok(Json.toJson(interviewSession.copy(id= Some(interviewSessionId.toLong))))
      }
  }


  def next(sessionId:Long) = DBAction{
    implicit rs =>

      val interviewSession = fetchEntityById(InterviewSessions, sessionId).get


      interviewSession
      NotImplemented
  }


  private def hasErrors(res: Seq[JsResult[Any]]) = {
         res.count(_.isInstanceOf[JsError]) > 0

  }
}
