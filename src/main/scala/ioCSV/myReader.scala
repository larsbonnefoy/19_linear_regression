package iocsv

import scala.io.BufferedSource
import scala.util.Try

object CSVReader {

  //TODO: Check format of received CSV

  /**
    *
    * @param path path to csv file
    * @return two dimensional Array where indices represent columns
    */
  def readCSV(path : String): Array[Array[String]] = 
    val it = io.Source.fromFile(path)

    val data = it.getLines()
                .toArray
                .map(_.split(","))


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

