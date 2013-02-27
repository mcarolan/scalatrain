import scala.annotation.tailrec

object object_functional_programming_in_depth {

	//can overflow the stack
	def factorial(n : BigInt) : BigInt =
		if (n == 0) 1
		else n * factorial(n - 1)         //> factorial: (n: BigInt)BigInt
	
	//tail recursive
	def factorial2(n : BigInt) : BigInt = {
		@tailrec
		def inner(n : BigInt, acc : BigInt) : BigInt = {
			if (n == 0)
				acc
			else
				inner(n - 1, n * acc)
		}
		
		inner(n, 1)
	}                                         //> factorial2: (n: BigInt)BigInt
	
	//stack overflow factorial(10000)
	factorial2(10000)                         //> res0: BigInt = 2846259680917054518906413212119868890148051401702799230794179
                                                  //| 9942744113400037644437729907867577847758158840621423175288300423399401535187
                                                  //| 3905242116138271617481982419982759241828925978789812425312059465996259867065
                                                  //| 6016157203603239792632873671705574197596209947972034615369811989709261127750
                                                  //| 0484198845410475544642442136573303076703628825803548967461117097369578603670
                                                  //| 1910715127305872810411586405612811653853259684258259955846881464304255898366
                                                  //| 4931705925171720427659740744613340005419405246230343686915405940406622782824
                                                  //| 8371512038322178644627183822923899638992827221879702459387693803094627332292
                                                  //| 5705554596900278752822425443480211275590191694254290289169072190970836905398
                                                  //| 7374745248337289952180236328274121704026808676921045155584056717255537201585
                                                  //| 2132829034279989818449313610640381489304499621599999359670892980190336998484
                                                  //| 4046654192362584249471631789611920412331082686510713545168455409360330096072
                                                  //| 103469443779823494307806
                                                  //| Output exceeds cutoff limit.
	
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // recursive implementation of map, flatMap, filter
  object Recurse2 {
  
  	def map[A, B](c: Seq[A])(func: A => B) : Seq[B] =
  		c match {
  			case head +: tail => func(head) +: map(tail)(func)
  			case Seq() => Seq.empty
  		}
  		
  	def flatMap[A, B](c: Seq[A])(func: A => Seq[B]): Seq[B] =
  		c match {
  			case head +: tail => func(head) ++ flatMap(tail)(func)
  			case Seq() => Seq.empty
  		}
  		
  	def filter[A](c: Seq[A])(func: A => Boolean) : Seq[A] =
  		c match {
  			case head +: tail if func(head) => head +: filter(tail)(func)
  			case head +: tail => filter(tail)(func)
  			case Seq() => Seq.empty
  		}
  		
  	def forall[A](c: Seq[A])(func: A => Boolean) : Boolean =
  		c match {
  			case head +: tail if func(head) => forall(tail)(func)
  			case Seq() => true
  			case _ => false
  		}
  }
  
  Recurse2.map(Seq(1, 2, 3, 4))(_ + 1)            //> res1: Seq[Int] = List(2, 3, 4, 5)
  Recurse2.filter(Seq(1, 2, 3, 4))(_ % 2 == 0)    //> res2: Seq[Int] = List(2, 4)
  Recurse2.forall(Seq(1, 2, 3, 4))(_ % 2 == 0)    //> res3: Boolean = false
  Recurse2.forall(Seq(1, 2, 3, 4))(_ > 0)         //> res4: Boolean = true
  
  //partial function literal with case statement
  val pairs = ('a' to 'f') zipWithIndex           //> pairs  : scala.collection.immutable.IndexedSeq[(Char, Int)] = Vector((a,0),
                                                  //|  (b,1), (c,2), (d,3), (e,4), (f,5))
  
  //partial function
  pairs filter { case (_, i) => i % 2 == 0 }      //> res5: scala.collection.immutable.IndexedSeq[(Char, Int)] = Vector((a,0), (c
                                                  //| ,2), (e,4))
   
	object Folding {
	
		def map[A, B](c: Seq[A])(func: A => B): Seq[B] =
			c.foldLeft(Seq.empty[B])((x, y) => x :+ func(y))
		
		def flatMap[A, B](c: Seq[A])(func: A => Seq[B]): Seq[B] =
			c.foldLeft(Seq.empty[B])((x, y) => x ++ func(y))
			
		def filter[A](c: Seq[A])(func: A => Boolean): Seq[A] =
			c.foldLeft(Seq.empty[A])((x, y) => if (func(y)) x :+ y else x)
	}
	
	Folding.map(Seq(1, 2, 3, 4))(_ + 1)       //> res6: Seq[Int] = List(2, 3, 4, 5)
	Folding.flatMap(Seq(1, 2, 3, 4))((x) => List(x, x))
                                                  //> res7: Seq[Int] = List(1, 1, 2, 2, 3, 3, 4, 4)
	Folding.filter(Seq(1, 2, 3, 4))(_ % 2 == 0)
                                                  //> res8: Seq[Int] = List(2, 4)
}