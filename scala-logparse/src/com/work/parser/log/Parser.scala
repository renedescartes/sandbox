package com.work.parser.log

import collection.immutable.TreeMap

/**
 * Created by IntelliJ IDEA.
 * User: kannan
 * Date: 13/04/12
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */

object Parser {

  def main(args: Array[String]) {
    val parser: FolderParser = new FolderParser()
    //parser.parseLine("2012-04-12 00:00:01.874,9581e040-3551-492f-b14d-724b5a7de0a6,/AccountService/v3.0,*,,HTTP,SOAP,10.40.146.35,--,Ok,5074000,411,994,xml,xml")
    parser.parseFolder("/home/data")
    parser.printReport()
//    parser.callerMap.toList sortBy (_._2) foreach {
//      case(key, value) => println("|" + key + " |" + value + "|")
//    }
//    println(parser.callerMap.mkString("\n", "\n", "\n"));
  }

}
