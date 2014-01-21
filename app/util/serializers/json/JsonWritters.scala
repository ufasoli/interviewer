package util.serializers.json

import play.api.libs.json.Json
import models.{InterviewSession, Question}
import _root_.util.SimpleResponse



/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 07.01.14
 * Time: 14:02
 *
 */
object JsonWritters {
  implicit val questionWrites = Json.writes[Question]
  implicit val questionReads = Json.reads[Question]
  implicit val questionFormats = Json.format[Question]
  implicit val simpleResponseWrites = Json.writes[SimpleResponse]
  implicit val simpleResponseReads = Json.reads[SimpleResponse]

  implicit val interviewSessionWriter = Json.writes[InterviewSession]
  implicit val interviewSessionReader = Json.reads[InterviewSession]


}
