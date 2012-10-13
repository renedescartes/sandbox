package com.work.parser.log

import io.Source
import java.io.File
import util.logging.ConsoleLogger
import java.lang.String
import scala.sys.process._
import collection.mutable.HashMap

class FolderParser extends Object with ConsoleLogger {

  var requestEntry = new RequestEntryAggregator();

  def parseLine(line : String) {
    val ipAddress: String = line.split(",")(5)
    requestEntry.addCaller(ipAddress)
  }

  def parseFile(fileName : String) {
    log("Parsing file : " + fileName)
    for(line <- Source.fromFile(fileName).getLines()) {
      parseLine(line)
    }
  }

  def parseFolder(folderName : String) {
    log("Parsing Folder : " + folderName);
    for(file <- new File(folderName).listFiles()) {
      parseFile(file.getAbsolutePath);
    }
  }

  def printReport() {
    requestEntry.printCallers()
    //requestEntry.printCountPercentageReport() Not implemented yet
  }

}
