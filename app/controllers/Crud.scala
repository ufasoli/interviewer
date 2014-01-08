package controllers


import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.Session
import scala.slick.lifted.Query
import models.AutoIncrementable


/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 07.01.14
 * Time: 17:45
 *
 */
trait Crud[T <: AnyRef {val id : Option[Long]}, U <: AutoIncrementable[T]]  {


  def fetchAllEntities(table: U)(implicit session: Session): Seq[T] = {

      Query(table).filter(_.id > 0L).list()
  }

  def fetchEntityById(table:U, id:Long)(implicit session:Session):T={

    Query(table).filter(_.id === id).first()
  }

  def deleteEntity(table:U, id:Long)(implicit session:Session)={

    Query(table).filter(_.id === id).delete
  }

  def addEntity(table:U, entity:T)(implicit session:Session)={

    table.autoInc.insert(entity)
  }

  def updateEntity(id:Long, table:U,entity:T)(implicit session:Session)={
    val status = Query(table).filter(_.id === id).update(entity)
  }

}
