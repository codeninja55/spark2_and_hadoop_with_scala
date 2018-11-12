package com.codeninja55.spark

object LearningScala1 {

    def main(args: Array[String]): Unit = {
        // VALUES are immutable constants.
        val hello: String = "Hello World!"

        // VARIABLES are mutable
        val user = "Andrew"
        var helloUser: String = "Hello "
        helloUser += user
        println(helloUser)

        // Some other types
        val numberOne: Int = 1
        val truth: Boolean = true
        val charA: Char = 'a'
        val pi: Double = 3.4159265
        val piSinglePrec: Float = 3.14159265f  // Scala is strict on literals for single precision
        val bigNumber: Long = 1234567890  // double wide integer
        val smallNumber: Byte = 127  // 8-bit value -127 to 127

        // printf style
        println(f"Pi is about $piSinglePrec%.3f")
        println(f"Zero padding on the left: $numberOne%05d")
        println(s"User: $user")
        println(s"Evaluation expressions: 100 + 55 = ${100+55}")

        // Regular expressions
        val theUltimateQuote: String = "To life, the universe, and everything is 42."

        val pattern = """.* ([\d]+).*""".r  // pattern: scala.util.matching.Regex = .* ([\d]+) .*

        val pattern(answerString) = theUltimateQuote
        val answer = answerString.toInt
        println(s"Answer: $answer")

        /* CONTROL FLOW */
        // Switch || Matching
        val number = 3
        number match {
            case 1 => println("One")
            case 2 => println("Two")
            case 3 => println("Three")
            case 4 => println("Four")
        }

        // For Loops
        for ( x <- 1 to 4) {
            val squared = x * x
            println(squared)
        }

        // Expressions
        // "Returns" the final value in a block automatically

        println("Expressions => " + { val x = 10; x + 20 })

        // Exercise
        /* Write some code that prints out the first 10 values of the Fibonacci sequence. */


        /* FUNCTIONS */
        println("Function => " + square(2))
        println("Adapter function => " + transformInt(2, cube))

        // Lambda functions
        println("Lambda expressions 1 => " + transformInt(5, x => x * x * x))
        println("Lambda expressions 2 => " + transformInt(5, x => { val y = scala.math.pow(x, 2).toInt; y * y }))

        /* TUPLES */
        // Immutable lists
        // Often thought of as database fields, or columns.

        val point3D: Tuple3[Int, Int, Int] = (10, 20, 30)

        // You refer to individual fields with their 1-based index
        println("Width: " + point3D._1)
        println("Length: " + point3D._2)
        println("Height: " + point3D._3)

        /* TUPLES AS MAPS */
        // You can create a key/value pair with ->
        val enterpriseShip = "Captain" -> "Captain Kirk"
        println("Who is the captain of the Enterprise: " + enterpriseShip._2)

        /* LISTS */
        // Like a tuple, but it's an actual Collection object that has more functionality.
        // Also, it can only hold items of the same type.
        // It's a singly-linked list as implemented.

        val avengersLs = List("Iron Man", "Captain America", "Black Widow", "Hulk", "Thor",
                              "Hawkeye", "Black Panther", "Spider Man")
        println(avengersLs.head)  // because its a linked list
        println(avengersLs(1))
        println(avengersLs.tail)  // a doubly-linked list

        for (hero <- avengersLs) { println(hero) }

        // let's apply a function literal to a list!
        // map() can be used to apply any function to every item in a collection
        val reverseAvengers = avengersLs.map( (hero: String) => {hero.reverse} )
        reverseAvengers.foreach(println)

        /* REDUCE() */
        val numbersList = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val sumFunc = numbersList.sum
        val sumReduced = numbersList.reduce( (x: Int, y: Int) => x + y )

        /* FILTER() */
        val findCaptain = avengersLs.filter( (hero: String) => hero.equals("Captain America"))
        println(findCaptain)  // returns a list based on predicate

        /* MAPS / DICTIONARIES */
        val avengersCast = Map(
            "Iron Man" -> "Tony Stark",
            "Captain America" -> "Steve Rogers",
            "Captain Marvel" -> "Carol Danvers",
            "Black Widow" -> "Natalia Alianovna Romanova",
            "Hulk" -> "Bruce Banner",
            "Thor" -> "Thor Odinson",
            "Hawkeye" -> "Clint Barton",
            "Black Panther" -> "T'Challa",
            "Spider Man" -> "Peter Parker",
            "Dr. Strange" -> "Dr. Stephen Strange",
            "Vision" -> "J.A.R.V.I.S.",
            "War Machine" -> "Captain James Rhodes",
            "Scarlet Witch" -> "Wanda Maximoff",
            "Falcon" -> "Sam Wilson"
        )

        println("Who is Captain Marvel? => " + avengersCast("Captain Marvel"))

        // Dealing with missing keys
        println("Is Loki part of the Avengers? " + avengersCast.contains("Loki"))
        val lokiPredicate = util.Try(avengersCast("Loki")) getOrElse "No, really?"
        println("Is Loki part of the Avengers? " + lokiPredicate)
    }

    /* Functions */
    def square(x: Int) : Int = {
        x * x  // expression returned automatically
    }

    def cube(x: Int) : Int = {
        x * x * x
    }

    // Functions taking other functions as parameters i.e. Adapters
    def transformInt(x: Int, f: Int => Int) : Int = {
        """
          |This function takes an int and transforms it using another function that
          |takes a parameter and transforms it.
        """.stripMargin
        f(x)   // remember returns the last expression to evaluate
    }
}
