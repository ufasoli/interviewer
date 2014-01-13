


import anorm.Column
import models.{Language,Domain,DifficultyLevel,ResponseSatisfactionLevel,Question}
import play.api._
import models.models._
import play.api.db.slick._
import play.api.Play.current
import scala.slick.jdbc
import scala.slick.jdbc.StaticQuery
import scala.slick.lifted.Query

import play.api.db.slick.Config.driver.simple._
import scala.Some

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 08.11.13
 * Time: 15:55
 *
 */
object Global extends GlobalSettings {
  override def onStart(app: Application): Unit = {

    DB.withSession {

      implicit s: Session =>



         // Drop all Tables
      /*  (InterviewSessions.ddl ++ InterviewQuestions.ddl ++ Questions.ddl ++ Languages.ddl ++ Domains.ddl ++ DifficultyLevels.ddl).dropStatements.foreach(

         // (q:String) =>  StaticQuery.updateNA(q).execute

        )*/

        // create all tables
       /* (Languages.ddl ++ Domains.ddl ++ DifficultyLevels.ddl ++ Questions.ddl ++ InterviewSessions.ddl ++ InterviewQuestions.ddl ).createStatements.foreach(

        //  (q:String) =>  StaticQuery.updateNA(q).execute
        )*/


      // Insert initial data

        if( Query(Languages).filter(_.id > 0L).list.size < 1){
          Languages.autoInc.insert(new Language(None, "fr"))
          Languages.autoInc.insert(new Language(None, "en"))
          Languages.autoInc.insert(new Language(None, "de"))
        }


        if( Query(DifficultyLevels).filter(_.id > 0L).list.size < 1){

          DifficultyLevels.autoInc.insert(new DifficultyLevel(None, "JUNIOR", 1, None))
          DifficultyLevels.autoInc.insert(new DifficultyLevel(None, "PROFESSIONNAL", 2, None))
          DifficultyLevels.autoInc.insert(new DifficultyLevel(None, "EXPERT", 3, None))
        }


        if( Query(Domains).filter(_.id > 0L).list.size < 1){

          Domains.autoInc.insert(new Domain(None, "AD", Some("Application development")))
          Domains.autoInc.insert(new Domain(None, "BI", Some("Business Intelligence")))
        }

        if( Query(ResponseSatisfactionLevels).filter(_.id > 0L).list.size < 1){

          ResponseSatisfactionLevels.autoInc.insertAll(
            new ResponseSatisfactionLevel(None, "EXCELLENT", 10, Some("Response corresponded exactly to expectations")),
            new ResponseSatisfactionLevel(None, "GOOD", 8, Some("Response was pertinent and adequate")),
            new ResponseSatisfactionLevel(None, "ACCEPTABLE", 6, Some("Response was not ideal but the global concept was correct")),
            new ResponseSatisfactionLevel(None, "WRONG", 2, Some("The response was wrong")),
            new ResponseSatisfactionLevel(None, "NO_ANSWER", 0, Some("The candidate gave no answer"))
          )
        }


        if( Query(Questions).filter(_.id > 0L).list.size < 1){

          Questions.autoInc.insertAll(
            new Question(None, "q", "a", 1l, 1l, None, 0, None),
            new Question(None, "q", "a", 2l, 2l, None, 0, None),
          new Question(None, "q", "a", 3l, 3l, None, 0, None)
          )
        }







    }
  }

}
