package basic

/**
 * Compute the avarage of the list
 *
 * ex : [1, 10, 16] -> 9
 */
object ComputeAvarage {

  def average(l: List[Double]) : Double = {
    var avg: Double = 0
    if(!l.isEmpty){
      for(number <- l) {
        avg += number
      }
      avg = avg/l.length
    }
    return  avg
  }

  def main(args: Array[String]): Unit = {
    val tester: List[Double] = List(10,20,15,15)
    println(average(tester))
  }

}
