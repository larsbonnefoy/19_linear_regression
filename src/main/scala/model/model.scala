package model


class LinearRegression(weight: Double = 0, bias: Double = 0) {
  private var w : Double = weight
  private var b : Double = bias


  /**
    * Parameters of model
    * @return (weight, bias)
    */
   def params = (w, b) //needs to be a function, not a val, if val it keeps value from initatilsation => 0 and 0

  def pred (x : Double): Double = 
    // println(s"0: ${theta0} 1: ${theta1}")
    b + (w * x)

  def fit(x : Array[Double], y: Array[Double], lr: Double = 0.01, epochs: Int = 20) = 
    val nSamples = x.size
    assert(y.size == nSamples, s"Size of inputs do not match x:${nSamples} y:${y.size}")
    for 
      e <- 1 to epochs  
    do { 
      var loss: Double= 0
      for 
        i <- 0 until nSamples
      do {
        val xi = x(i)
        val yi = y(i)
        val yhat = pred(xi)

        val curr_loss = yi - yhat
        loss += curr_loss

        val db = (1 / nSamples.toDouble) * curr_loss
        val dw = (1 / nSamples.toDouble) * curr_loss * xi
        
        b += lr * db
        w += lr * dw
      }
      println(s"epoch: $e/$epochs. Loss: $loss")
    }
    println(s"w: ${w}, b: ${b}")
}
