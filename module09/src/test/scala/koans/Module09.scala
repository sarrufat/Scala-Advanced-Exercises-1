package koans

import java.io.File

import org.scalatest.{CancelAfterFailure, FunSpec, Matchers, SeveredStackTraces}

import scala.io.Source

class Module09 extends FunSpec with Matchers with SeveredStackTraces with CancelAfterFailure {


  describe("RoShamBo") {
    // Create an Algebraic Data Type of RoShamBo with three sub types, Rock, Paper and Scissors

    // next write methods rock, paper and scissors that take a RoShamBo and return either Win, Lose or Draw
    // so that the tests below pass

    // A starting ADT, Win, Lose or Draw
    trait Outcome
    object Win extends Outcome
    object Lose extends Outcome
    object Draw extends Outcome



    // Put your RoShamBo ADT and rock,paper,scissors methods below
    trait RoShamBo

    trait Rock extends RoShamBo
    object Rock extends Rock

    trait Paper extends RoShamBo
    object Paper extends Paper

    trait Scissors extends RoShamBo
    object Scissors extends Scissors

    def rock(bid: RoShamBo): Outcome = bid match {
      case _: Rock => Draw
      case _: Paper => Lose
      case _: Scissors => Win
    }

    def paper(bid: RoShamBo): Outcome = bid match {
      case _: Rock => Win
      case _: Paper => Draw
      case _: Scissors => Lose
    }

    def scissors(bid: RoShamBo): Outcome = bid match {
      case _: Rock => Lose
      case _: Paper => Win
      case _: Scissors => Draw
    }

    it("Should follow the rules of RoShamBo") {

      rock(Rock) should be(Draw)
      rock(Paper) should be(Lose)
      rock(Scissors) should be(Win)

      paper(Rock) should be(Win)
      paper(Paper) should be(Draw)
      paper(Scissors) should be(Lose)

      scissors(Rock) should be(Lose)
      scissors(Paper) should be(Win)
      scissors(Scissors) should be(Draw)

    }

  }

  describe("Process the first line of a file with a function") {
    // Using a loan pattern or otherwise, make a method which when handed a file and
    // a function from String to something, reads the first line of the file and
    // uses the function to process it, closing the file automatically before handing back the result.
    // Hint - to make the tests pass, you might need to clean up the string that is read in from the file,
    // try .trim()

    def withFileContents[A](file: File)(fn: String => A): A = {
      val source = Source.fromFile(file)
      try {
        fn(source.getLines.take(1).map(_.trim).mkString)
      } finally source.close
    }

    it("should work on provided resource files") {

      def resourceFile(name: String): File = new File(this.getClass.getClassLoader.getResource(name).toURI)

      val palindrome = withFileContents(resourceFile("quote.txt")) { str => str.reverse }
      palindrome should be("Madam, I'm Adam")

      val total = withFileContents(resourceFile("nums.txt")) { str =>
        str.split(",").map(_.trim.toInt).sum // make sure to understand what this is doing
      }
      total should be(55)

    }

  }

  describe("A fluent DSL") {
    // Create a fluent DSL that allows the test below to pass when uncommented.
    // Note, you will get a warning about postfix operators, so you might want to fix that too
    val late = "late"
    val out = "out"
    object It {
      def gets(l: late.type): It.type = this

      def early(o: out.type): It.type = this

      def here: String = "Yogi Berra"
    }
    it("should quote Yogi Berra") {
      (It gets late early out here) should be("Yogi Berra")
    }
  }

  describe("Arity three case classes") {
    // in the slides, we covered an example case class arity 2 JSON writer.
    // Demonstate your mastery by adapting that code to deal with an Arity 3 case class instead.
    //
    // You will find supporting code in the CaseClassJson file in this package. Due to some
    // complicated interaction between by-name functions and this kind of reflection,
    // you will need to complete your implementation in that same CaseClassJson file - just
    // follow the instructions there. Once you have written the code, uncomment below to test
    // it out.


    it("should work for a Person") {
      val p1 = Person("Harry", "Potter", 34)

      import JSONWrite._

      jsonify(p1) should be(
        """{ "Person": {"first": "Harry", "last": "Potter", "age": 34} }""".stripMargin)

    }

  }

}
