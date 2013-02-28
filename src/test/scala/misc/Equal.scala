package misc

object Equal {

  implicit class EqualOps[T](val me: T) {

    def ===(other: T) = me == other

  }

}

