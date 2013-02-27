object ws {

	//a function
	val addOne : Int => Int = _ + 1           //> addOne  : Int => Int = <function1>
	//equiv to
	val addOne2 : Function1[Int, Int] = _ + 1 //> addOne2  : Int => Int = <function1>
	//equiv to
	val addOne3 : Function1[Int, Int] = new Function1[Int, Int] {
		def apply(x : Int) = x + 1
	}                                         //> addOne3  : Int => Int = <function1>
	
	for  {
		number <- Seq(13, 5, 6, 7) if number % 2 == 1 // this is a generator
		number2 <- Seq(2, 3, 4) // so is this
	}
	yield number * number2                    //> res0: Seq[Int] = List(26, 39, 52, 10, 15, 20, 14, 21, 28)
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
	
	}
	
	def x(s : String) = s match {
		case x : String if x.startsWith("a") => "a string"     //patern match with guard expression
		case x : String => "other string"
	}                                         //> x: (s: String)String
	
	x("hello")                                //> res1: String = other string
	x("a good morning to you")                //> res2: String = a string
	
	//options
	def returnSomething(b : Boolean) = if (b) Some(1) else None
                                                  //> returnSomething: (b: Boolean)Option[Int]
	
	returnSomething(true) map {_ + 1}         //> res3: Option[Int] = Some(2)
	returnSomething(false) map {_ + 1}        //> res4: Option[Int] = None
	
	for {
		x <- returnSomething(true)
		y <- returnSomething(false)
	}
	yield x * y                               //> res5: Option[Int] = None

	for {
		x <- returnSomething(true)
		y <- returnSomething(true)
	}
	yield x * y                               //> res6: Option[Int] = Some(1)
	
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}