val conversionKilosToPounds = 2.20462262185

class Person(nm: String, wt: Double) {

  def name: String = nm

  private[this] var wtIbs = wt

  def weightInPounds: Double = wtIbs

  def weightInPounds_=(newWeight: Double): Unit = {
    wtIbs = newWeight
  }

  def weightInKilos: Double = wtIbs / conversionKilosToPounds

  def weightInKilos_=(newWeight: Double): Unit = {
    wtIbs = newWeight * conversionKilosToPounds
  }

}

val fred = new Person("Fred", 120)

fred.weightInPounds

fred.weightInKilos

fred.weightInKilos_=(60)

fred.weightInKilos

fred.weightInPounds

fred.weightInPounds_=(125)

fred.weightInPounds

fred.weightInKilos

// Below will not compile - try it if you like,
// vals, vars and defs share the same name space
// and cannot duplicate names

class Foo {
  private[this] val _yo: String = "Yo"

  def yo: String = "Hello" // will not compile...
  def greet(name: String): String =
    s"$yo $name" // which yo would it call?
}

val foo = new Foo
foo.greet("Sergi")