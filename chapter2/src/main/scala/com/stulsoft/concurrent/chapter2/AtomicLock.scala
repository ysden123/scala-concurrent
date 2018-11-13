/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.concurrent.chapter2

import scala.concurrent.ExecutionContext

/**
  * @author Yuriy Stul
  */
object AtomicLock extends App {

  import java.util.concurrent.atomic._

  private val lock = new AtomicBoolean(false)

  def mySynchronized(body: => Unit): Unit = {
    while (!lock.compareAndSet(false, true)) {}
    try body
    finally lock.set(false)
  }

  var count = 0
  for (i <- 0 until 10) execute {
    mySynchronized {
      count += 1
    }
  }
  Thread.sleep(1000)
  println(s"Count is: $count")

  def execute(body: => Unit): Unit = ExecutionContext.global.execute(() => body)
}
