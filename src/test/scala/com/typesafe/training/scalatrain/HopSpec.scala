package com.typesafe.training.scalatrain

import TestData._
import java.lang.{ IllegalArgumentException => IAE }
import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

class HopSpec extends WordSpec with MustMatchers {

  "Creating a hop" should {
    "throw an IllegalArgumentException if from and to are the same station" in {
      evaluating(Hop(Nuremberg, Nuremberg, Ice724)) must produce[IAE]
    }

    "throw an IllegalArgumentException if from and to not in the backToBackStations of the train" in {
      evaluating(Hop(Frankfurt, Munich, Ice724)) must produce[IAE]
    }

    "arrival time must be initialized correctly" in {
      Hop(Frankfurt, Cologne, Ice724).departureTime must be === Time(12, 10)
    }

    "departure time must be initialized correctly" in {
      Hop(Frankfurt, Cologne, Ice724).arrivalTime must be === Time(13, 39)
    }
  }

}