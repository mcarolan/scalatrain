object implicits {

	case class Animal(val name: String)

	implicit def stringToAnimal(s: String) = Animal(s)
                                                  //> stringToAnimal: (s: String)implicits.Animal
	val fish: Animal = "nemo"                 //> fish  : implicits.Animal = Animal(nemo)
	fish.name                                 //> res0: String = nemo
	
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}