/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.concurrent.chapter2

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext

/**
  * @author Yuriy Stul
  */
object AtomicLock extends App with LazyLogging {

  import java.util.concurrent.atomic._

  private val lock = new AtomicBoolean(false)

  def mySynchronized(body: => Unit): Unit = {
    logger.info("==>mySynchronized")
    while (!lock.compareAndSet(false, true)) {}
    try body
    finally lock.set(false)
    logger.info("<==mySynchronized")
  }

  var count = 0
  (0 until 10) foreach { _ =>
    execute {
      mySynchronized {
        count += 1
      }
    }
  }

  Thread.sleep(1000)
  logger.info(s"Count is: $count")

  def execute(body: => Unit): Unit = ExecutionContext.global.execute(() => body)
}
