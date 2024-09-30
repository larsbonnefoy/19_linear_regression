package scaler

class StandardScaler {
  private var mean: Double = 0
  private var stdDev: Double = 0

  def fit_transform(d: Array[Double]) = 
    val nSamples = d.size
    mean = d.sum / nSamples
    val variance = d.map(elt => elt - mean)
                    .map(elt => elt * elt)
                    .sum / nSamples.toDouble 
    stdDev = math.sqrt(variance)
    transform(d)

  def transform(d: Array[Double]) = d.map(elt => (elt - mean) / stdDev)
}
