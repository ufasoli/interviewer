package models

import java.util.Date
import models.DifficultyLevels
import play.api.libs.json.Json

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 08.11.13
 * Time: 10:49
 *
 */
case class Question(
                     id: Option[Long],
                     q: String,
                     a: String,
                     languageId: Long,
                     difficultyLevelId: Long,
                     comment: Option[String],
                     timesAsked: Int = 0,
                     lastAsked: Option[Date]) {

}


import play.api.db.slick.Config.driver.simple._

class Questions extends Table[Question]("QUESTIONS") with AutoIncrementable[Question] {

  import conversion._


  def q = column[String]("Q", O.NotNull)

  def a = column[String]("A", O.NotNull)

  def languageId = column[Long]("LANGUAGE_ID", O.NotNull)

  def difficultyLevelId = column[Long]("DIFFICULTY_LEVEL_ID", O.NotNull)

  def comment = column[String]("COMMENT", O.Nullable)

  def timesAsked = column[Int]("TIMES_ASKED")

  def lastAsked = column[Date]("LAST_ASKED", O.Nullable)

  def * = id.? ~ q ~ a ~ languageId ~ difficultyLevelId ~ comment.? ~ timesAsked ~ lastAsked.? <>(Question.apply _, Question.unapply _)

  def difficultyLevel = foreignKey("FK_DL", difficultyLevelId, DifficultyLevels)(_.id)


}
