package scaler

class StandardScaler(m: Double = 0, sdev: Double = 0) {
  private var mean: Double = m
  private var stdDev: Double = sdev

  /**
    * Returns (mean, stdDev) of scaler
    */
  def params = (mean, stdDev)

  def fit_transform(d: Array[Double]) = 
    val nSamples = d.size
    mean = d.sum / nSamples
    val variance = d.map(elt => elt - mean)
                    .map(elt => elt * elt)
                    .sum / nSamples.toDouble 
    stdDev = math.sqrt(variance)
    transform(d)

  def transform_elt(elt: Double) = if stdDev == 0 then 0 else (elt - mean) / stdDev

  def transform(d: Array[Double]) = d.map(elt => transform_elt(elt))

}
