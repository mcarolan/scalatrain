package com.typesafe.training.scalatrain

case class Hop(from: Station, to: Station, train: Train) {
  require(from != to, "from must not be equal to to")
  require(train.backToBackStations contains from -> to, "from -> to must be in the backToBackStations of the train")

  val departureTime = train.departureTimes(from)
  val arrivalTime = train.departureTimes(to)

}