package misc

import org.scalatest.WordSpec
import org.scalatest.MustMatchers
import java.util.NoSuchElementException

class QueueSpec extends WordSpec with MustMatchers {

  "Calling equals" should {
    "be true for identical objects" in {
      val queue = Queue(1, 2, 3)
      queue must be === queue
    }
    "be true for equal objects" in {
      Queue(1, 2, 3) must be === Queue(1, 2, 3)
    }
    "be false for unequal objects" in {
      Queue(1, 2, 3) == Queue(1, 2) must be === false
    }
  }

  "Test covariance" should {
    class Animal {

    }
    class Bird extends Animal {

    }

    val qb = Queue(new Bird)
    val q: Queue[Animal] = qb
  }

  "Calling hashCode" should {
    "return the same value for equal objects" in {
      Queue(1, 2, 3).## must be === Queue(1, 2, 3).##
    }
  }

  "Calling toString" should {
    "return the class name and the elements in parentheses" in {
      Queue(1, 2, 3).toString must be === "Queue(1, 2, 3)"
    }
  }

  "Calling dequeue" should {
    "throw an UnsupportedOperationException for an empty queue" in {
      evaluating(Queue().dequeue) must produce[NoSuchElementException]
    }
    "return the first element and a new Queue without the dequeued element" in {
      Queue(1, 2, 3).dequeue must be === (1, Queue(2, 3))
    }
  }
}
