package controllers


import play.api.mvc._

import play.api.db.slick._
import play.api.Play.current
import models._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.libs.json.{JsSuccess, JsError, Json}
import scala.slick.lifted.Query
import models.Questions
import util.SimpleResponse


/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 16.12.13
 * Time: 13:29
 *
 */
object QuestionController extends Controller {


  implicit val questionWrites = Json.writes[Question]
  implicit val questionReads = Json.reads[Question]
  implicit val questionFormats = Json.format[Question]
  implicit val simpleResponseWrites = Json.writes[SimpleResponse]

  def add = DBAction(parse.json) {

    implicit rs =>

      val question = rs.request.body.validate[Question]

      question match {
        case JsSuccess(_,_) => Ok(Json.toJson(new SimpleResponse("Ok")))
        case JsError(_) => BadRequest(Json.toJson(new SimpleResponse(s"Unable to parse body. error : [$question]")))
        case _ => InternalServerError(Json.toJson(new SimpleResponse(s"unknown error")))
      }

  }

  def update(id: Long) = DBAction {
    implicit rs =>

      NotImplemented
  }

  def delete(id: Long) = DBAction {
    implicit rs =>



      val affectedRows = Query(Questions).where(_.id === id).delete

      affectedRows match {
        case 0 => BadRequest(Json.toJson(new SimpleResponse(s"Unable to delete question the id [$id] was not found in the database")))
        case 1 => Ok(Json.toJson(new SimpleResponse(s"Question $id deleted. rows affected : $affectedRows")))
        case _ => BadRequest(Json.toJson(new SimpleResponse(s"Unable to delete question. Error [$affectedRows]")))

      }


  }

  def list = DBAction {
    implicit rs =>
      Ok(Json.toJson(Query(Questions).filter(_.id > 0L).list()))
  }

}