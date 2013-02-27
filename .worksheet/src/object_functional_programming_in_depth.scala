import scala.annotation.tailrec

object object_functional_programming_in_depth {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(189); 

	//can overflow the stack
	def factorial(n : BigInt) : BigInt =
		if (n == 0) 1
		else n * factorial(n - 1);System.out.println("""factorial: (n: BigInt)BigInt""");$skip(204); 
	
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
	};System.out.println("""factorial2: (n: BigInt)BigInt""");$skip(56); val res$0 = 
	
	//stack overflow factorial(10000)
	factorial2(10000);System.out.println("""res0: BigInt = """ + $show(res$0));$skip(46); 
	
  println("Welcome to the Scala worksheet")}
}
