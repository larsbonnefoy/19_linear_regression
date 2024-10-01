import iocsv.CSVReader
import iocsv.CSVWriter
import model.LinearRegression
import scaler.StandardScaler

@main def train(): Unit =
  // can fail if file does not exist/wrong perms !! 
  try {
    val table = CSVReader.readCSV("data/data.csv")

    val headers = List(table(0)(0), table(1)(0))
    val data = Array(table(0).drop(1), table(1).drop(1)).map(col => col.map(_.toDouble))

    // val dataMap = headers.zip(data).toMap
    // dataMap.foreach((header, data) => println(header + ": " + data.mkString(", ")))

    val scaler = new StandardScaler()
    val model = new LinearRegression()
    
    val x_scaled = scaler.fit_transform(data(0))
    val y = data(1)

    model.fit(x_scaled, y, lr = 0.01, epochs = 1000)

    val (weight, bias) = model.params
    val (mean, stdDev) = scaler.params
    val toWrite = s"b:${bias}, w:${weight}, mean:${mean}, stdDev:${stdDev}"
    println(s"Writing to weights.csv: $toWrite")
    CSVWriter.writeCSV("data/weights.csv", s"${bias}, ${weight}, ${mean}, ${stdDev}", 1)
  }
  catch 
    case e: java.io.FileNotFoundException => println(s"Unable to find file: ${e.getMessage()}")
    case e: java.lang.NumberFormatException => println(s"Wrong format to convert to double: ${e.getMessage()}") 
    case other => println(s"Error ${other.getMessage()}")

@main def predict(): Unit =
  try {
    val table = CSVReader.readCSV("data/weights.csv")
    table.foreach(row => println(row.mkString(": ")))

    val data = (for i <- 0 until table.size yield table(i)(1)).map(_.toDouble)

    val model = new LinearRegression(data(1), data(0))
    val scaler = new StandardScaler(data(2), data(3))

    def compute(): Double =

      def compute_aux(value: Option[Double]): Double = 
        value match {
          case None => {
            try 
              print("Enter number of km: ")
              compute_aux(Some(scala.io.StdIn.readDouble()))
            catch
              case e => println(s"Wrong input: $e"); compute_aux(None)
          }
          case Some(x) => model.pred(scaler.transform_elt(x))
        }

      compute_aux(None)
    
    val res = compute()
    println(s"Expected price: $res")
  }
  catch 
    case e: java.io.FileNotFoundException => println(s"Unable to find file: ${e.getMessage()}")
    case e: java.lang.NumberFormatException => println(s"Wrong format to convert to double: ${e.getMessage()}") 
    case other => println(s"Unknown error ${other}")
