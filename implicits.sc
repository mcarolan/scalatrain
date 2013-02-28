import com.typesafe.training.scalatrain
import misc.Equal.EqualOps
import misc.Equal.AreEqual

object implicits {
	case class Animal(val name: String)

	implicit def stringToAnimal(s: String) = Animal(s)
                                                  //> stringToAnimal: (s: String)implicits.Animal
	val fish: Animal = "nemo"                 //> fish  : implicits.Animal = Animal(nemo)
	fish.name                                 //> res0: String = nemo
	
	implicit class IntReverse(val n: Int) {
		def reverse: Int =
			n.toString.reverse.toInt
	}

	123.reverse                               //> res1: Int = 321
	
	//type safe equals!
	123 == 123                                //> res2: Boolean(true) = true
	"123" == 123                              //> res3: Boolean = false
	123 === 123                               //> res4: Boolean = true
	//will not compile "123" === 123
	
	"hello" === "hello"                       //> res5: Boolean = true
	
	implicit val weirdStringEquals = new AreEqual[String] {
		def eval(s1: String, s2: String) = s1 != s2
	}                                         //> weirdStringEquals  : misc.Equal.AreEqual[String] = implicits$$anonfun$main$1
                                                  //| $$anon$1@3d9360e2
                               
  "hello" === "hello"                             //> res6: Boolean = false
	
	def pow(x: Double)(implicit y: Double) = Math.pow(x, y)
                                                  //> pow: (x: Double)(implicit y: Double)Double
         
	pow(2)(10)                                //> res7: Double = 1024.0
	
	implicit val defaultExponent: Double = 10 //> defaultExponent  : Double = 10.0
	
	pow(2)                                    //> res8: Double = 1024.0
	pow(5)                                    //> res9: Double = 9765625.0
	pow(10)                                   //> res10: Double = 1.0E10
}