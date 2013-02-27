/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import scala.util.control.Exception
import scala.util.parsing.json.{ JSON, JSONObject }
import scala.annotation.tailrec

object Time {

  def fromMinutes(minutes: Int): Time =
    Time(minutes / 60, minutes % 60)

  def fromJSON(json: JSONObject): Option[Time] =
    for {
      hoursAny <- json.obj get "hours"
      hours <- Exception.allCatch opt hoursAny.toString.toInt
      minutesAny <- json.obj get "minutes"
      minutes <- Exception.allCatch opt minutesAny.toString.toInt
    } yield Time(hours, minutes)

  @tailrec
  def isIncreasing(times: Seq[Time]): Boolean =
    times match {
      case t1 +: t2 +: rest => (t1 < t2) && isIncreasing(t2 +: rest)
      case Seq(_) => true
      case Seq() => true
    }

  def isIncreasingSliding(times: Seq[Time]) =
    times sliding 2 forall {
      case Seq(t1, t2) => t1 < t2
      case Seq(_) => true
    }

}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  require(hours >= 0 && hours < 24, "hours must be within 0 and 23")
  require(minutes >= 0 && minutes < 60, "minutes must be within 0 and 59")

  val asMinutes: Int =
    hours * 60 + minutes

  override lazy val toString: String =
    f"$hours%02d:$minutes%02d"

  def minus(that: Time): Int =
    this.asMinutes - that.asMinutes

  def -(that: Time): Int =
    minus(that)

  // TODO This "pollutes" the API; in the Advanced Scala course we learn a better solution
  def toJSON: JSONObject =
    JSONObject(Map("hours" -> hours, "minutes" -> minutes))

  override def compare(that: Time): Int =
    this - that
}
