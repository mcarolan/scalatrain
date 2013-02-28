/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import scala.collection.immutable.Seq
import scala.collection.breakOut

case class Train(info: TrainInfo, schedule: Seq[(Time, Station)]) {
  require(schedule.size >= 2, "schedule must contain at least two elements")
  require(isIncreasing(schedule map (_._1)), "schedule times must be strictly increasing")

  val stations: Seq[Station] =
    schedule map (_._2)

  val departureTimes: Map[Station, Time] =
    //works, but traverses twice: (schedule map (_.swap)).toMap
    (schedule map (_.swap))(breakOut)
  //breakOut only does 1 traversal!
  //first method is equiv to
  //val col1: Seq[(Station, Time)] = schedule map (_.swap)
  //val col2: Map[Station, Time] = col1 toMap
  //breakOut uses the buildFrom of map
  //http://stackoverflow.com/questions/1715681/scala-2-8-breakout

  val backToBackStations: Seq[(Station, Station)] =
    stations zip (stations tail)
}

object TrainInfo {

  case class InterCityExpress(number: Int, hasWifi: Boolean = false) extends TrainInfo

  case class RegionalExpress(number: Int) extends TrainInfo

  case class BavarianRegional(number: Int) extends TrainInfo
}

sealed abstract class TrainInfo {

  def number: Int
}

//can see in sbt console, :javap com.typesafe.training.scalatrain.JourneyPlanner that this is now inlined to a string when passed as a parameter
case class Station(name: String) extends AnyVal
