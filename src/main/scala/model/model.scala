package model

import scaler.StandardScaler

class LinearRegression(lr: Double = 0.01) {
  private var w : Double = 0
  private var b : Double = 0

  private val scaler : StandardScaler = new StandardScaler()
  private val learningRate = lr

  def params = (w, b)

  def pred (x : Double): Double = 
    // println(s"0: ${theta0} 1: ${theta1}")
    b + (w * x)

  def fit(x : Array[Double], y: Array[Double], epochs: Int = 20) = 
    val x_scaled = scaler.fit_transform(x)
    val nSamples = x.size
    assert(y.size == nSamples)
    for 
      e <- 0 until epochs 
      i <- 0 until nSamples
    do 
      val xi = x_scaled(i)
      val yi = y(i)

      val yhat = pred(xi)

      val db = -(1 / nSamples.toDouble) * (yhat - yi)
      val dw = -(1 / nSamples.toDouble) * (yhat - yi) * xi
      
      b -= learningRate * db
      w -= learningRate * dw
      println(s"error: ${yhat - yi}")
      println(s"db: $db - dw: $dw")
      println(s"b: $b - w: $w")
 
  //tmp0 = lr * Sum()
  //
}
