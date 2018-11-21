/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.concurrent

/**
  * @author Yuriy Stul
  */
package object chapter5 {
  @volatile var dummy: Any = _

  /**
    * Returns execution time of the body
    *
    * @param body the body
    * @tparam T body result type
    * @return execution time of the body in ms.
    */
  def timed[T](body: => T): Double = {
    val start = System.nanoTime
    dummy = body
    val end = System.nanoTime
    ((end - start) / 1000) / 1000.0
  }
}
