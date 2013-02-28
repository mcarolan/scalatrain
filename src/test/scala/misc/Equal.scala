package misc

object Equal {
  
  trait AreEqual[T] {
    def eval(t1: T, t2: T): Boolean
  }
  
  implicit val anyEqual = new AreEqual[Any] {
    def eval(a1: Any, a2: Any) = a1 == a2
  }
  
  implicit val intEqual = new AreEqual[Int] {
    def eval(i1: Int, i2: Int) = i1 == i2
  }
  
  implicit val stringEqual = new AreEqual[String] {
    def eval(s1: String, s2: String) = s1 == s2
  }

  implicit class EqualOps[T](val me: T) {

    def ===(other: T)(implicit areEqual: AreEqual[T]): Boolean = areEqual.eval(me, other)

  }

}

