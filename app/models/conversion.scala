package models

import play.api.db.slick.Config.driver.simple._

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 15.11.13
 * Time: 06:41
 *
 */
object conversion {

    implicit val javaUtilDateTypeMapper = MappedTypeMapper.base[java.util.Date, java.sql.Date](
      x => new java.sql.Date(x.getTime),
      x => new java.util.Date(x.getTime))

}
