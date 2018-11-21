/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.concurrent.chapter4

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source

/** Incorrect link
  *
  * @author Yuriy Stul
  */
object FutureCallBacEx2 extends App with LazyLogging {
  def readUrlSpec(): Future[List[String]] = Future {
    val url = "http://www.w3.orgERROR/Addressing/URL/url-spec.txt"
    val f = Source.fromURL(url)
    try f.getLines.toList finally f.close()
  }

  def find(lines: List[String], keyword: String): String =
    lines.zipWithIndex collect {
      case (line, n) if line.contains(keyword) => (n, line)
    } mkString "\n"

  val urlSpec = readUrlSpec()

  // 1st callback
  urlSpec foreach { lines => logger.info(find(lines, "telnet")) }

  // 2nd callback
  urlSpec foreach { lines => logger.info(find(lines, "password")) }

  // Exception handler
  urlSpec.failed.foreach(ex => logger.error(s"Exception occurred: ${ex.getMessage}", ex))

  logger.info("callback registered, continuing with other work")

  Thread.sleep(2000)
}
