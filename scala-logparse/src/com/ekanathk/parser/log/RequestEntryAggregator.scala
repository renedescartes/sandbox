package com.ekanathk.parser.log

import util.logging.ConsoleLogger
import collection.mutable.HashMap
import collection.immutable
import scala.sys.process._
/**
 * Created by IntelliJ IDEA.
 * User: kannan
 * Date: 16/04/12
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */

class RequestEntryAggregator extends Object with ConsoleLogger {

  private var callerMap = new HashMap[String, String]();
  private var applicationMap = new HashMap[String, Int]();


  def addCaller(ipAddress : String) {
    if(!callerMap.contains(ipAddress)) {
      val hostName: String = convertIpAddressToHostName(ipAddress)
      callerMap += ipAddress -> hostName
      addCallCount(convertHostNameToApplicationName(hostName))
    }
  }

  def printCallers() {
    log("\nCaller Report\n")
    callerMap.toList sortBy (_._2) foreach {
      case(key, value) => println("|" + key + " |" + value + "|" + convertHostNameToApplicationName(value) + "|")
    }
  }

  def printCountPercentageReport() {
    log("\nLogging Percentage Report\n");
    val totalCount = applicationMap.values.sum;
    applicationMap.toList sortBy (_._1) foreach {
      case(key, value) => log("|" + key + " |" + value + "|" + (value/totalCount));
    }
  }

  private def addCallCount(applicationName : String) {
    val count = applicationMap.getOrElse(applicationName, 0);
    applicationMap.+=(applicationName -> (count + 1))
  }

  private def convertIpAddressToHostName(ipAddress : String) : String = {
    try {
      extractHostName( (("host " + ipAddress).!! ))
    } catch {
      case e: RuntimeException => "Unable to convert ip address to hostname"
    }
  }

  private def extractHostName(hostNameOutput : String) : String = {
    /**
     * The output looks something like "17.180.40.10.in-addr.arpa domain name pointer prdggs002.prd.betfair."
     * Split by space take the last element and remove the last dot to get the hostname
     */
    val input = hostNameOutput.split(" ").last
    input.substring(0, input.length() - 2);
  }

  private def convertHostNameToApplicationName(hostname : String) : String = {
    if(hostname.startsWith("prd")) {
      val shortAppName = hostname.substring(3, 6);
      APP_REF.getOrElse(shortAppName, shortAppName.concat(" : Not sure what this APP is"))
    } else {
      hostname + " : Cant work out what app this is";
    }
  }

  private val APP_REF = immutable.HashMap(
    "ads" -> "Account Services Box",
    "bxb" -> "GEAPI",
    "crw" -> "Customer Registration Box",
    "gap" -> "Games API",
    "ggs" -> "Skills and Dice Games Gateway Service",
    "gps" -> "Skills and Dice Games Portal Service",
    "lds" -> "Localised Deposit Pages",
    "pcf" -> "Poker Client Facade",
    "rpw" -> "Account Web",
    "saw" -> "Sports Exchange API",
    "spa" -> "RAS/SIP Adapter",
    "ssm" -> "SMS Session Management Service",
    "xap" -> "Card API",
    "maw" -> "Mantis Account Web",
    "kmd" -> "Komodo",
    "gxw" -> "Gamex Server",
    "mgw" -> "Mobile Games web",
    "pys" -> "Platform Payment Service",
    "ers" -> "Rules Service Server",
    "fss" -> "Funds Service"
  )

}