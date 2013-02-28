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

}