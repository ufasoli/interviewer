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
import util.serializers.json.JsonWritters
import JsonWritters._
import play.api.mvc.BodyParsers.parse


/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 16.12.13
 * Time: 13:29
 *
 */
object QuestionController extends Crud[Question, Questions] with Controller {


  def fetch(id: Long) = DBAction {implicit rs =>
      fetchEntityById(Questions, id).map(entity => Ok(Json.toJson(entity))).getOrElse(BadRequest)
  }

  def fetchAll = DBAction({
    implicit rs =>
      Ok(Json.toJson(fetchAllEntities(Questions)))
  })


  def add = DBAction(parse.json) {implicit rs =>

      val question = rs.request.body.validate[Question]

      question match {

        case JsSuccess(_, _) =>
          val questionId = Questions.autoInc.insert(question.get)
          Ok(Json.toJson(new SimpleResponse(s"question [$questionId] was successfully created")))
        case JsError(_) => BadRequest(Json.toJson(new SimpleResponse(s"Unable to parse body. error : [$question]")))
        case _ => InternalServerError(Json.toJson(new SimpleResponse(s"unknown error")))
      }

  }

  def update(id: Long) = DBAction(parse.json) {implicit rs =>



      val question = rs.request.body.validate[Question]

      question match {

        case JsSuccess(_, _) =>
          val status = Query(Questions).filter(_.id === id).update(question.get)

          Ok(Json.toJson(new SimpleResponse(s"question [$status] was successfully created. rows modified [$status]")))
        case JsError(_) => BadRequest(Json.toJson(new SimpleResponse(s"Unable to parse body. error : [$question]")))
        case _ => InternalServerError(Json.toJson(new SimpleResponse(s"unknown error")))
      }


  }


  def delete(id: Long) = DBAction {implicit rs =>

      val deletedRows =    deleteEntity(Questions, id)

    deletedRows match{
         case 0 => BadRequest(Json.toJson(new SimpleResponse(s"Unable to delete question the id [$id] was not found in the database")))
         case 1 => Ok(Json.toJson(new SimpleResponse(s"Question $id deleted. rows affected : 1")))
         case err => BadRequest(Json.toJson(new SimpleResponse(s"Unable to delete question. Error [$err]")))

    }

  }

 

}