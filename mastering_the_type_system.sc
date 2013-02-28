
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
}