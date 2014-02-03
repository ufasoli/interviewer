package models

import play.api.db.slick.Config.driver.simple._


/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 08.11.13
 * Time: 10:48
 *
 */
case class DifficultyLevel(id:Option[Long], name:String, points:Int, description:Option[String]) {

}


class DifficultyLevels extends Table[DifficultyLevel]("DIFFICULTY_LEVEL") with AutoIncrementable[DifficultyLevel]{



  def name = column[String]("NAME")
  def points = column[Int]("POINTS")
  def description = column[String]("DESCRIPTION", O.Nullable)

  def * = id.? ~ name ~ points ~ description.? <> (DifficultyLevel.apply _ , DifficultyLevel.unapply _)

}

