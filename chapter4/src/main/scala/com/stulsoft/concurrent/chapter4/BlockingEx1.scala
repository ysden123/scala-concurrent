/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.concurrent.chapter4

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, blocking}

/**
  * @author Yuriy Stul
  */
object BlockingEx1 extends App with LazyLogging {
  test1()
  test2()

  /**
    * Without use of blocking
    */
  def test1(): Unit = {
    logger.info("==>test1")
    val startTime = System.nanoTime
    val futures = for (_ <- 0 until 16) yield Future {
      Thread.sleep(1000)
    }
    for (f <- futures) Await.ready(f, Duration.Inf)
    val endTime = System.nanoTime
    logger.info(s"Total time = ${(endTime - startTime) / 1000000} ms")
    logger.info(s"Total CPUs = ${Runtime.getRuntime.availableProcessors}")
    logger.info("<==test1")
  }

  /**
    * With use of blocking
    */
  def test2(): Unit = {
    logger.info("==>test2")
    val startTime = System.nanoTime
    val futures = for (_ <- 0 until 16) yield Future {
      blocking {
        Thread.sleep(1000)
      }
    }
    for (f <- futures) Await.ready(f, Duration.Inf)
    val endTime = System.nanoTime
    logger.info(s"Total time = ${(endTime - startTime) / 1000000} ms")
    logger.info(s"Total CPUs = ${Runtime.getRuntime.availableProcessors}")
    logger.info("<==test2")
  }
}
