
abstract class AbsClass {
  type CTYPE <: Seq[_]
  val field: CTYPE
}

class ConcrectList extends AbsClass {
  override type CTYPE = List[String]
  override val field = List("a", "b", "c")
}

val acl = new ConcrectList
val t: ConcrectList#CTYPE = acl.field
val t2:  acl.CTYPE  = acl.field

// Singleton type

val s = "hello"

def f(str: s.type ) = s"$str"

f(s)

val or = "or"
val to = "to"

object To {
  def be(o: or.type ): To.type = this
  def not(t: to.type): To.type = this
  def be = "That's the question"
}

To be or not to be


