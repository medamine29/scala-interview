package basic

/**
 * This is basic language questions so don't use external library or build in function
 */
object BasicScala {

  /**
   * Encode parameter in url format
   *
   * Example:
   *
   * input  : Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
   * output : "?sort_by=name&order_by=asc&user_id=12"
   *
   * input  : Map()
   * output : ""
   */
  def encodeParamsInUrl(params: Map[String, String]): String = {
    var encodedURL: String = "?"

    if(!params.isEmpty){
      for ((k,v) <- params) if(k != null && v != null)  encodedURL+=k+"="+v+"&"
      return  encodedURL.substring(0, encodedURL.length - 1)
    }else{
      println("the map is empty !")
      return null
    }
  }

  /**
   * Test if a String is an email
   */
  def isEmail(maybeEmail: String): Boolean = """([a-zA-Z0-9._-]+)@([a-zA-Z0-9._-]+)\.([a-zA-Z0-9]+)""".r.unapplySeq(maybeEmail).isDefined

  /**
   * Compute i ^ n
   *
   * Example:
   *
   * input : (i = 2, n = 3) we compute 2^3 = 2x2x2
   * output : 8
   *
   * input : (i = 99, n = 38997)
   * output : 1723793299
   */

  def power(i: Int, n: Int): Int = {
    var result: Int = i
    if(n==0){
      result = 1
    }else if(n==1){
      result = i
    }else{
      for( a <- 1 to n-1){
        result = result*i
      }
    }
    //I didn't know if I'm allowed to use the pow function below !
    //result = scala.math.pow(i,n).toInt
    return result
  }

  def main(args: Array[String]): Unit = {

    // val testerMap: Map[String,String] = Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
    // println(encodeParamsInUrl(testerMap))

    // println(power(0,0))

    // println(isEmail("ami6.5ne@gmail"))

  }

}


