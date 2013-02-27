object ws {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 

	//a function
	val addOne : Int => Int = _ + 1;System.out.println("""addOne  : Int => Int = """ + $show(addOne ));$skip(55); 
	//equiv to
	val addOne2 : Function1[Int, Int] = _ + 1;System.out.println("""addOne2  : Int => Int = """ + $show(addOne2 ));$skip(107); 
	//equiv to
	val addOne3 : Function1[Int, Int] = new Function1[Int, Int] {
		def apply(x : Int) = x + 1
	};System.out.println("""addOne3  : Int => Int = """ + $show(addOne3 ));$skip(148); val res$0 = 
	
	for  {
		number <- Seq(13, 5, 6, 7) if number % 2 == 1 // this is a generator
		number2 <- Seq(2, 3, 4) // so is this
	}
	yield number * number2
	//can do this with any type that has map, flatMap, filter e.g. futures, options
	
	
	abstract class Animal {
		val name : String
	}
	
	class Bird extends Animal {
		// cannot have def name = "Bird", as code might be written that expects name to never change
		val name = "Bird"
		
	}
	
	//CAN do the other way around though
	
	abstract class Animal2 {
		def name : String
	}
	
	class Bird2 extends Animal2 {
		// CAN have def name = "Bird", can also have a
		val name = "Bird"
	}
	
	//a seal class can only be extended IN THIS SOURCE FILE
	
	//traits cannot have class parameters, constructor
	
	class Foo {
	}
	
	trait Baz {
	}
	
	
	class Bar extends Foo with Baz {
	}
	
	class FooBar2 {
	}
	
	class Foo2 extends FooBar2 {
	}
	
	class Boob {
	}
	
	trait Baz2 extends Boob {
	}
	
	// can mix in Baz but not Baz 2, as Foo2 and Baz2 do not have the same supertype
	class Bar2 extends Foo2 with Baz {
	
	};System.out.println("""res0: Seq[Int] = """ + $show(res$0));$skip(1070); 
	
	def x(s : String) = s match {
		case x : String if x.startsWith("a") => "a string"     //patern match with guard expression
		case x : String => "other string"
	};System.out.println("""x: (s: String)String""");$skip(14); val res$1 = 
	
	x("hello");System.out.println("""res1: String = """ + $show(res$1));$skip(28); val res$2 = 
	x("a good morning to you");System.out.println("""res2: String = """ + $show(res$2));$skip(74); 
	
	//options
	def returnSomething(b : Boolean) = if (b) Some(1) else None;System.out.println("""returnSomething: (b: Boolean)Option[Int]""");$skip(37); val res$3 = 
	
	returnSomething(true) map {_ + 1};System.out.println("""res3: Option[Int] = """ + $show(res$3));$skip(36); val res$4 = 
	returnSomething(false) map {_ + 1};System.out.println("""res4: Option[Int] = """ + $show(res$4));$skip(84); val res$5 = 
	
	for {
		x <- returnSomething(true)
		y <- returnSomething(false)
	}
	yield x * y;System.out.println("""res5: Option[Int] = """ + $show(res$5));$skip(82); val res$6 = 

	for {
		x <- returnSomething(true)
		y <- returnSomething(true)
	}
	yield x * y;System.out.println("""res6: Option[Int] = """ + $show(res$6));$skip(46); 
	
  println("Welcome to the Scala worksheet")}
}
