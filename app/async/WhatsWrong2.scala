package async

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

case class CEO(id: String, first_name: String, last_name: String)
case class Enterprise(id: String, name: String, ceo_id: String)

object CEODao {
  val ceos = List(
    CEO("1", "Mark", "Zuckerberg"),
    CEO("2", "Sundar", "Pichai")
  )

  def byId(id: String): Future[Option[CEO]] = Future(ceos.find(_.id == id))
}

object EnterpriseDao {
  val enterprises = List(
    Enterprise("1", "Google", "1"),
    Enterprise("2", "Facebook", "2")
  )

  def byId(id:        String): Future[Option[Enterprise]] = Future(enterprises.find(_.id == id))
  def byCEOId(ceo_id: String): Future[Option[Enterprise]] = Future(enterprises.find(_.ceo_id == ceo_id))
}

object WhatsWrong2 {

  //Review this code. What could be done better ? How would you do it ?
  //we are passing Option[String] to getCEOAndEnterprise and we are extracting the parameter value with get without
  // checking if it's defined we could risk a "NoSuchElementException" if we invoke the get method on an instance of None
  // I would pass a String as parameter so than if a false value is passed to the method we get an empty list instead of an exception
  def getCEOAndEnterprise(ceo_id: String): Future[(Option[CEO], Option[Enterprise])] = {
    for {
      ceo        <- CEODao.byId(ceo_id)
      enterprise <- EnterpriseDao.byCEOId(ceo_id)
    } yield {
      (ceo, enterprise)
    }
  }
}
