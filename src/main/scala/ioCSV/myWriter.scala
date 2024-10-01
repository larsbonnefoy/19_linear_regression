package iocsv

import java.io.PrintWriter

object CSVWriter {

  /**
   * Writes string `str` to line `lineNb` in file given by `path`
   * Will NOT produces an error if lineNb is out of bound
   */
  def writeCSV(path: String, str: String, lineNb: Int) : Unit = 
    val it = io.Source.fromFile(path)

    val lines = it.getLines().toList

    val updatesLines = lines.zipWithIndex.map {
      case (_, index) if index == lineNb => str
      case (line, _) => line
    }

    val writer = new PrintWriter(path)
    updatesLines.foreach(writer.println)
    writer.close()
}
