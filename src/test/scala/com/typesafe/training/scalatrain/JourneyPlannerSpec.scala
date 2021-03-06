/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import TestData._
import java.lang.{ IllegalArgumentException => IAE }
import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

class JourneyPlannerSpec extends WordSpec with MustMatchers {

  "stations" should {
    "be initialized correctly" in {
      Planner.stations must be === Set(Munich, Nuremberg, Frankfurt, Cologne, Essen)
    }
  }

  "Calling trainsAt" should {
    "return the correct trains" in {
      Planner.trainsAt(Munich) must be === Set(Ice724, Ice726)
      Planner.trainsAt(Cologne) must be === Set(Ice724)
    }
  }

  "Calling stopsAt" should {
    "return the correct stops" in {
      Planner.stopsAt(Munich) must be === Set(Ice724MunichTime -> Ice724, Ice726MunichTime -> Ice726)
    }
  }

  "Calling connections" should {
    "return 2 routes for Munich -> frankfurt at 6am " in {
      Planner.connections(Munich, Frankfurt, Time(6)).size must be === 3
    }

    "return 1 route for Munich -> frankfurt in 8am " in {
      Planner.connections(Munich, Frankfurt, Time(8)).size must be === 1
    }
  }

  "hops" should {
    "work for starting station" in {
      Planner.hops(Munich) must be === Set(Hop(Munich, Nuremberg, Ice724), Hop(Munich, Nuremberg, Ice726))
    }

    "works for middle station" in {
      Planner.hops(Frankfurt) must be === Set(
        Hop(Frankfurt, Cologne, Ice724),
        Hop(Frankfurt, Essen, Ice726))
    }

    "end only station not in hops" in {
      Planner.hops isDefinedAt Essen must be === false
    }
  }

  "Calling isShortTrip" should {
    "return false for more than one station in between" in {
      Planner.isShortTrip(Munich, Cologne) must be(false)
      Planner.isShortTrip(Munich, Essen) must be(false)
    }
    "return true for zero or one stations in between" in {
      Planner.isShortTrip(Munich, Nuremberg) must be(true)
      Planner.isShortTrip(Munich, Frankfurt) must be(true)
      Planner.isShortTrip(Nuremberg, Frankfurt) must be(true)
      Planner.isShortTrip(Nuremberg, Essen) must be(true)
    }
  }
}
