package iocsv

import scala.io.BufferedSource

object CSVReader {

  /**
    *
    * @param path path to csv file
    * @return two dimensional Array where indices represent columns
    */
  def readCSV(path : String): Array[Array[String]] = 
    val it = io.Source.fromFile(path)

    val data = it.getLines()
                .toArray
                .map(_.filterNot(_.isWhitespace).split(","))


    val columnCount = if data.nonEmpty then data.head.size else 0

    val table : Array[Array[String]]= Array.tabulate(columnCount) {
      i => 
      for 
        row <- data
        j <- row.indices
        if j == i
      yield row(j)
    }
    table
}

