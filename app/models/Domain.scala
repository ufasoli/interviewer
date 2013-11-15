package models

import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.ColumnBase

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 08.11.13
 * Time: 10:46
 *
 */
case class Domain(id:Option[Long], name:String, description:Option[String]) {

}

class Domains extends Table[Domain]("DOMAINS") with AutoIncrementable[Domain]{


  def name = column[String]("NAME")
  def description = column[String]("DESCRIPTION", O.Nullable)

  def * = id.? ~ name ~ description.? <> (Domain.apply _, Domain.unapply _)
}
