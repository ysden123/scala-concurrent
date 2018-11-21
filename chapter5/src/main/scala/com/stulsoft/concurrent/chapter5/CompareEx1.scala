/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.concurrent.chapter5

import com.typesafe.scalalogging.LazyLogging

import scala.util.Random

/**
  * @author Yuriy Stul
  */
object CompareEx1 extends App with LazyLogging {
  test(5000000)
  test(500)

  def test(n: Int): Unit = {
    logger.info(s"==>test1, n=$n")
    val numbers = Random.shuffle(Vector.tabulate(n)(i => i))
    val seqtime = timed {
      numbers.max
    }
    logger.info(s"Sequential time $seqtime ms")
    val partime = timed {
      numbers.par.max
    }
    logger.info(s"Parallel time $partime ms")
    logger.info("<==test1")
  }
}
