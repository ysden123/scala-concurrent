/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.concurrent.chapter4

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.{Future, Promise}

/** Demonstrates the use of Promise
  *
  * @author Yuriy Stul
  */
object PromiseTimeoutEx1 extends App with LazyLogging {

  import java.util._

  import scala.concurrent.ExecutionContext.Implicits.global

  private val timer = new Timer(true)

  def timeout(t: Long): Future[Unit] = {
    val p = Promise[Unit]
    timer.schedule(new TimerTask {
      def run(): Unit = {
        p success()
        timer.cancel()
      }
    }, t)
    p.future
  }

  logger.info("==>PromiseTimeoutEx1")
  timeout(1000) foreach (_ => logger.info("Timed out!"))
  Thread.sleep(2000)
  logger.info("<==PromiseTimeoutEx1")
}
