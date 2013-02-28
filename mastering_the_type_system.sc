
object mastering_the_type_system {
	//AnyRefs are object types
	//val s : AnyRef = "Hello"
	//val v : AnyRef = Vector(1, 2, 3)
	//val n : AnyRef = null
	
	//AnyVals are value types
	//val i : AnyVal = 1       //integer
	
	//in scala 2.10 you can introduce your own value types (subclasses of AnyVal)
	//only 1 class parameter
	//class parameter must be a val
	//there must be no other vals
	//after this, all method calls are inlined!
	
	//covariant: Cage[Bird] is a subtype of Cage[Animal]
	//invariant: there is no subtype relationship between Cage[Bird] and Cage[Animal]
	//contravariant: Cage[Animal] is a subtype of Cage[Bird]
	
	//readonly: has to be covariant
	//writeonly: has to be contravariant
	//readwrite: has to be invariant
	
	//package objects define things that are visible to all classes defined in a package
	//instead of creating a 'Utility' class, you can put them in a package object and grab them that way
	
	//enumeration
	object MyEnum extends Enumeration {
		val A, B, C = Value
	}
	
	//Value calls the Value method
	//val v = MyEnum.B
	
	class Outer {
		class Inner
	}

 //inner class belongs to its instance, not the outer class
	val o = new Outer                         //> o  : mastering_the_type_system.Outer = mastering_the_type_system$Outer@51b4
                                                  //| 8197
	val i = new o.Inner                       //> i  : mastering_the_type_system.o.Inner = mastering_the_type_system$Outer$In
                                                  //| ner@47315d34
	//this is the type definition works for this
	val inner: Outer#Inner = new o.Inner      //> inner  : mastering_the_type_system.Outer#Inner = mastering_the_type_system$
                                                  //| Outer$Inner@79de256f
               
  trait Swimmer extends Food
  sealed abstract class Food
  case object Grain extends Food
  case object Grass extends Food
  case object Fish extends Swimmer
  case object Shark extends Swimmer
                                     
  abstract class Animal {
  	
  	type SuitableFood <: Food
  	def name: String
  	
  	def eat(food: SuitableFood): Animal = this
  }
  
  case class Bird(val name: String) extends Animal {
  	type SuitableFood = Grain.type
  }
  
  case class Cow(val name: String) extends Animal {
  	type SuitableFood = Grass.type
  }
  
  case class Fish(val name: String) extends Animal {
  	type SuitableFood = Swimmer
  }
  
  val tweetyPie = Bird("Tweety Pie")              //> tweetyPie  : mastering_the_type_system.Bird = Bird(Tweety Pie)
  
  tweetyPie eat Grain                             //> res0: mastering_the_type_system.Animal = Bird(Tweety Pie)
  //will not compile tweetyPie eat Grass
  
  val moo = Cow("cow")                            //> moo  : mastering_the_type_system.Cow = Cow(cow)
  moo eat Grass                                   //> res1: mastering_the_type_system.Animal = Cow(cow)
  //will not compile moo eat Grain
  //val moo = Cow("moo)
  
  
  val nemo = Fish("nemo")                         //> nemo  : mastering_the_type_system.Fish = Fish(nemo)
  nemo eat Fish                                   //> res2: mastering_the_type_system.Animal = Fish(nemo)
  nemo eat Shark                                  //> res3: mastering_the_type_system.Animal = Fish(nemo)
  //will nor compile nemo eat Grass
  
}