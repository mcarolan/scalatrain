package misc

import scala.collection.immutable.Seq

object Queue {

  def apply[A](as: A*): Queue[A] =
    new Queue(as.toVector)
}

class Queue[+A] private (private val as: Seq[A]) {

  def dequeue: (A, Queue[A]) = as match {
    case head +: tail => head -> new Queue[A](tail)
    case _ => throw new NoSuchElementException()
  }

  override def equals(other: Any): Boolean =
    other match {
      case that: Queue[_] => (this eq that) || (this.as == that.as)
      case _ => false
    }

  override def hashCode: Int =
    as.hashCode

  override def toString: String =
    s"Queue(${as mkString ", "})"
}
