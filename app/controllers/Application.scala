package controllers

import models._
import models._
import scala.slick.lifted.Query
import play.api.db.slick.Config.driver.simple._


import play.api.mvc._

import play.api.db.slick._
import play.api.Play.current

object Application extends Controller {


  def index = DBAction {
    implicit rs =>
      val languages = Query(Languages).filter(_.id > 0L).list()
      val questions = Query(Questions).filter(_.id === 1L).leftJoin(DifficultyLevels).on(_.difficultyLevelId === _.id).list()
       val difficultyLevels = Query(DifficultyLevels).list()


      Ok(
        s"<p>$languages</p><br /><br /> <p>$questions</p><br /><br /><p>$difficultyLevels").as(HTML)
  }



}