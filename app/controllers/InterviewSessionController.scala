package controllers

import _root_.util.SimpleResponse
import play.api.mvc.Controller
import models._
import play.api.db.slick.DBAction
import play.api.Play.current
import play.api.libs.json._
import _root_.util.serializers.json.JsonWritters._
import java.util.Date
import _root_.models.QuestionMode._
import play.api.db.slick.Config.driver.simple._
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


  def hasErrors(res: Seq[JsResult[Any]]) = {
    var hasError = false

    for (result <- res) {
      if (result.isInstanceOf[JsError]) {
        //TODO: find a way to break loop in scala
        hasError = true

      }

    }

    hasError
  }
}
