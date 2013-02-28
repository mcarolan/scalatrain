import com.typesafe.training.scalatrain
import misc.Equal.EqualOps

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
	
	
	def pow(x: Double)(implicit y: Double) = Math.pow(x, y)
                                                  //> pow: (x: Double)(implicit y: Double)Double
         
	pow(2)(10)                                //> res5: Double = 1024.0
	
	implicit val defaultExponent: Double = 10 //> defaultExponent  : Double = 10.0
	
	pow(2)                                    //> res6: Double = 1024.0
	pow(5)                                    //> res7: Double = 9765625.0
	pow(10)                                   //> res8: Double = 1.0E10
}