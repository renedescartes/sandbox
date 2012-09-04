package com.ekanathk.parser.log

import io.Source
import java.io.File
import util.logging.ConsoleLogger
import java.lang.String
import scala.sys.process._
import collection.mutable.HashMap

/**
 * Created by IntelliJ IDEA.
 * User: kannan
 * Date: 13/04/12
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */

/**
 * Example
 * /logs/global.betfair.com/bf-cas-access/2012/201208/20120810/20120810-23-prdcas004.prd.betfair-bf-cas-access.log.bz2
 */
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