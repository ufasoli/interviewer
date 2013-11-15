package models

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 08.11.13
 * Time: 10:48
 *
 */

import play.api.db.slick.Config.driver.simple._

case class Language(id: Option[Long], lang: String) {

}


class Languages extends Table[Language]("LANGUAGES") with AutoIncrementable[Language] {

  def lang = column[String]("LANGUAGE", O.NotNull)

  def * = id.? ~ lang <>(Language.apply _, Language.unapply _)


}



