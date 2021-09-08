package basic

/**
 * What is the complexity of the function ?
 *
 * -> C = O (3n) with n being the input list's length
 *
 * Refactor it to be a better function
 */
object Refactoring {

  case class File(
    name:     String,
    category: String
  )

  def getCategories(files: List[File]): List[String] = {
    val categories: List[String] = List()

    if(files != null) {
      for(file <- files) {
        if(file.category != null) {
          categories :+ file.category
        }
      }
    }
    return categories.toSet.toList
  }


}
