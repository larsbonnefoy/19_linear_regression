import iocsv.CSVReader
import model.LinearRegression

@main def train(): Unit =
  // can fail if file does not exist/wrong perms !! 
  val table = CSVReader.readCSV("data/data.csv")

  val headers = List(table(0)(0), table(1)(0))
  val data = Array(table(0).drop(1), table(1).drop(1)).map(col => col.map(_.toDouble))

  val dataMap = headers.zip(data).toMap
  dataMap.foreach((header, data) => println(header + ": " + data.mkString(", ")))

  val model = new LinearRegression()
  model.fit(data(0), data(1), lr = 0.01, epochs = 1000)

@main def predict(): Unit =
  println("predict value")
