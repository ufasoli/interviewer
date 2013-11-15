


import models.{Language,Domain,DifficultyLevel,ResponseSatisfactionLevel,Question}
import play.api._
import models.models._
import play.api.db.slick._
import play.api.Play.current
//import play.api.db.slick.Config.driver.simple._
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

        Languages.ddl.dropStatements
        DifficultyLevels.ddl.dropStatements
        Domains.ddl.dropStatements
        Questions.ddl.dropStatements

        // create tables
        Languages.ddl.createStatements
        DifficultyLevels.ddl.createStatements
        Domains.ddl.createStatements


        // Insert intial data

        Languages.autoInc.insert(new Language(None, "fr"))
        Languages.autoInc.insert(new Language(None, "en"))
        Languages.autoInc.insert(new Language(None, "de"))

        DifficultyLevels.autoInc.insert(new DifficultyLevel(None, "JUNIOR", 1, None))
        DifficultyLevels.autoInc.insert(new DifficultyLevel(None, "PROFESSIONNAL", 2, None))
        DifficultyLevels.autoInc.insert(new DifficultyLevel(None, "EXPERT", 3, None))


        Domains.autoInc.insert(new Domain(None, "AD", Some("Application development")))
        Domains.autoInc.insert(new Domain(None, "BI", Some("Business Intelligence")))

        ResponseSatisfactionLevels.autoInc.insertAll(
          new ResponseSatisfactionLevel(None, "EXCELLENT", 10, Some("Given response corresponded exactly to expectations")),
          new ResponseSatisfactionLevel(None, "GOOD", 8, Some("Given response corresponded exactly to expectations")),
          new ResponseSatisfactionLevel(None, "ACCEPTABLE", 6, Some("Given response corresponded exactly to expectations")),
          new ResponseSatisfactionLevel(None, "WRONG", 2, Some("Given response corresponded exactly to expectations")),
          new ResponseSatisfactionLevel(None, "NO_ANSWER", 0, Some("Given response corresponded exactly to expectations"))
        )

        Questions.autoInc.insertAll(
          new Question(None, "q", "a", 1l, 1l, None, 0, None)
        )



    }
  }

}
