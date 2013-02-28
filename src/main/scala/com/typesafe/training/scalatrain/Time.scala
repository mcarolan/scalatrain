/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import misc.{ JSONFormat, JSONSerializable }
import scala.collection.immutable.Seq
import scala.util.control.Exception
import scala.util.parsing.json.JSONObject

object Time extends JSONSerializable[Time] {

  implicit val timeJSONFormat: JSONFormat[Time] =
    new TimeJSONFormat

  private class TimeJSONFormat extends JSONFormat[Time] {

    def toJSON(time: Time): JSONObject =
      JSONObject(Map("hours" -> time.hours, "minutes" -> time.minutes))

    def fromJSON(json: JSONObject): Option[Time] =
      for {
        hoursAny <- json.obj get "hours"
        hours <- Exception.allCatch opt hoursAny.toString.toInt
        minutesAny <- json.obj get "minutes"
        minutes <- Exception.allCatch opt minutesAny.toString.toInt
      } yield Time(hours, minutes)
  }

  def fromMinutes(minutes: Int): Time =
    Time(minutes / 60, minutes % 60)

  implicit def stringToTime(s: String): Time = {
    val regex = """(\d{1,2}):(\d\d)""".r
    val regex(hours, mins) = s

    Time(Integer.parseInt(hours), Integer.parseInt(mins))
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

  override def compare(that: Time): Int =
    this - that
}
