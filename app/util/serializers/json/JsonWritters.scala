package util.serializers.json

import play.api.libs.json.Json
import models.Question
import util.SimpleResponse

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
}
