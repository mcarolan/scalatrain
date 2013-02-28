package com.typesafe.training

import scala.annotation.tailrec

package object scalatrain {

  @tailrec
  def isIncreasing[T <: Ordered[T]](times: Seq[T]): Boolean =
    times match {
      case t1 +: t2 +: rest => (t1 < t2) && isIncreasing(t2 +: rest)
      case Seq(_) => true
      case Seq() => true
    }

  def isIncreasingSliding[T <: Ordered[T]](times: Seq[T]) =
    times sliding 2 forall {
      case Seq(t1, t2) => t1 < t2
      case Seq(_) => true
    }

}