package models



/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 11.11.13
 * Time: 10:41
 *
 */



object models {

  val Languages = new Languages
  val DifficultyLevels = new DifficultyLevels
  val Domains = new Domains
  val Questions = new Questions
  val ResponseSatisfactionLevels = new ResponseSatisfactionLevels
  val InterviewSessions = new InterviewSessions
  val InterviewQuestions = new InterviewQuestions


}




import play.api.db.slick.Config.driver.simple._
trait AutoIncrementable[T] extends Table[T]{


  def id:Column[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
  def autoInc = * returning id
}
