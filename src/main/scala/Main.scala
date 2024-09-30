import iocsv.CSVReader
import model.LinearRegression

// TODO Could make a special Table class but seems like a lot of work
// TODO Make a class for model with params theta 0 and theta 1

@main def main(): Unit =
  // can fail if file does not exist/wrong perms !! 

  val table = CSVReader.readCSV("data/data.csv")

  // def applyElt[A, B](arr2d: Array[Array[A]])(f: A => B): Array[Array[B]] = 
  //   arr2d.map(row => f(row))

  val headers = List(table(0)(0), table(1)(0))
  val data = Array(table(0).drop(1), table(1).drop(1)).map(col => col.map(_.toDouble))

  val dataMap = headers.zip(data).toMap
  dataMap.foreach((header, data) => println(header + ": " + data.mkString(", ")))

  val model = new LinearRegression()
  model.fit(data(0), data(1), epochs = 20)
