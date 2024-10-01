package model

import scaler.StandardScaler

class LinearRegression(weight: Double = 0, bias: Double = 0) {
  private var w : Double = weight
  private var b : Double = bias

  private val scaler : StandardScaler = new StandardScaler()

  def params = (w, b)

  def pred (x : Double): Double = 
    // println(s"0: ${theta0} 1: ${theta1}")
    b + (w * x)

  def fit(x : Array[Double], y: Array[Double], lr: Double = 0.01, epochs: Int = 20) = 
    val nSamples = x.size
    assert(y.size == nSamples)
    val x_scaled = scaler.fit_transform(x)
    for 
      e <- 1 to epochs  
    do { 
      var loss: Double= 0
      for 
        i <- 0 until nSamples
      do {
        val xi = x_scaled(i)

        val yi = y(i)
        val yhat = pred(xi)

        var curr_loss = yi - yhat
        loss += curr_loss

        val db = (1 / nSamples.toDouble) * curr_loss
        val dw = (1 / nSamples.toDouble) * curr_loss * xi
        
        b += lr * db
        w += lr * dw
      }
      println(s"epoch: $e/$epochs. Loss: $loss")
    }
}
