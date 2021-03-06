/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import TestData._
import java.lang.{ IllegalArgumentException => IAE }
import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

class TrainSpec extends WordSpec with MustMatchers {

  "Creating a Train" should {
    "throw an IllegalArgumentException for a schedule with 0 or 1 elements" in {
      evaluating(Train(TrainInfo.InterCityExpress(724), Vector())) must produce[IAE]
      evaluating(Train(TrainInfo.InterCityExpress(724), Vector(Ice724MunichTime -> Munich))) must produce[IAE]
    }

    "throw an IllegalArgumentException for a schedule with none-increasing times" in {
      evaluating(Train(TrainInfo.InterCityExpress(724), Vector(Time(8, 50) -> Munich, Time(7, 49) -> Munich))) must produce[IAE]
    }
  }

  "stations" should {
    "be initialized correctly" in {
      Ice724.stations must be === Vector(Munich, Nuremberg, Frankfurt, Cologne)
    }
  }

  "departureTimes" should {
    "be initialized correctly" in {
      Ice724.departureTimes(Munich) must be === Time(8, 50)
      Ice724.departureTimes(Nuremberg) must be === Time(10)
      Ice724.departureTimes(Frankfurt) must be === Time(12, 10)
      Ice724.departureTimes(Cologne) must be === Time(13, 39)
    }
  }

  "implicit conversion from string to station" should {
    "work correctly" in {
      Ice724.departureTimes("Munich") must be === Time(8, 50)
      Ice724.departureTimes("Nuremberg") must be === Time(10)
      Ice724.departureTimes("Frankfurt") must be === Time(12, 10)
      Ice724.departureTimes("Cologne") must be === Time(13, 39)
    }
  }

  "backToBackStations" should {
    "be intialized correctly for 724" in {
      Ice724.backToBackStations must be === Vector(Munich -> Nuremberg, Nuremberg -> Frankfurt, Frankfurt -> Cologne)
    }
  }
}
