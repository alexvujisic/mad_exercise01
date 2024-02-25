/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package at.ac.fhcampuswien

import java.lang.IllegalArgumentException
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.roundToInt

class App {
    // Game logic for a number guessing game
    fun playNumberGame(digitsToGuess: Int = 4) {
        val numbersToGuess = generateRandomNonRepeatingNumber(4)
        println(8576)
        do {

            println("Type in numbers:")
            val input = readln().toInt()
            if (true) {
                println("input: $input")
                checkUserInputAgainstGeneratedNumber(input, 8576)
                println("count: " + getRightPlaced(input, numbersToGuess))

            } else {
                println("error")
            }
        }while (false)




        //TODO: build a menu which calls the functions and works with the return values
    }

    /**
     * Generates a non-repeating number of a specified length between 1-9.
     *
     * Note: The function is designed to generate a number where each digit is unique and does not repeat.
     * It is important to ensure that the length parameter does not exceed the maximum possible length
     * for non-repeating digits (which is 9 excluding 0 for base-10 numbers).
     *
     * @param length The length of the non-repeating number to be generated.
     *               This dictates how many digits the generated number will have.
     * @return An integer of generated non-repeating number.
     *         The generated number will have a number of digits equal to the specified length and will
     *         contain unique, non-repeating digits.
     * @throws IllegalArgumentException if the length is more than 9 or less than 1.
     */
    val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
        //TODO implement the function
        var finalNumber = 0.0
        if(length in 1..9){
            var generatedNumber = 0.1
            var randomNumber = 0
            val usedNumbers = mutableSetOf<Int>()
            val base = 10.0
            for(i in length downTo 1 step 1) {
                do {
                    generatedNumber = Math.random() * 10
                    randomNumber = generatedNumber.roundToInt()
                } while (randomNumber !in 0..9 || (randomNumber in usedNumbers)) //check if 0 to 9 and already used
                usedNumbers.add(randomNumber)
                val multiplier = base.pow(i-1)
                finalNumber += (randomNumber * multiplier)
            }
        }
        else{
            throw IllegalArgumentException()
        }
        finalNumber.toInt()   // return value is a placeholder
    }

    /**
     * Compares the user's input integer against a generated number for a guessing game.
     * This function evaluates how many digits the user guessed correctly and how many of those
     * are in the correct position. The game generates number with non-repeating digits.
     *
     * Note: The input and the generated number must both be numbers.
     * If the inputs do not meet these criteria, an IllegalArgumentException is thrown.
     *
     * @param input The user's input integer. It should be a number with non-repeating digits.
     * @param generatedNumber The generated number with non-repeating digits to compare against.
     * @return [CompareResult] with two properties:
     *         1. `n`: The number of digits guessed correctly (regardless of their position).
     *         2. `m`: The number of digits guessed correctly and in the correct position.
     *         The result is formatted as "Output: m:n", where "m" and "n" represent the above values, respectively.
     * @throws IllegalArgumentException if the inputs do not have the same number of digits.
     */
    val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
        //TODO implement the function
        var n = 0 //right numbers
        var m = 0 //right places

        if(getDigits(input) == getDigits(generatedNumber)){
            if(input == generatedNumber){
                n = getDigits(generatedNumber)
                m = getDigits(generatedNumber)
            }
            n = getRightNumbers(input, generatedNumber)
            m = getRightPlaced(input, generatedNumber)

        }else{
            throw IllegalArgumentException()
        }

        CompareResult(n, m)   // return value is a placeholder
    }

    private fun getDigits(number: Int): Int {
        return floor(log10(number.toDouble()) + 1).toInt()
    }

    private fun getRightNumbers(number: Int, generatedNumber: Int): Int{
        var digitsInNumber = mutableSetOf<Int>()
        var digitsInGeneratedNumber = mutableSetOf<Int>()
        var currentNumber = number
        var count = 0
        while(currentNumber > 0){
            digitsInNumber += currentNumber % 10
            currentNumber /= 10
        }
        //digitsInNumber.reverse()

        currentNumber = generatedNumber
        while(currentNumber > 0){
            digitsInGeneratedNumber += currentNumber % 10
            currentNumber /= 10
        }
        //digitsInGeneratedNumber.reverse()

        for(i in (digitsInNumber.size - 1) downTo 0 step 1){
            for(j in (digitsInGeneratedNumber.size - 1) downTo 0 step 1){
                if(digitsInNumber.elementAt(i) == digitsInGeneratedNumber.elementAt(j)){
                    count++
                }
            }
        }
        return count
    }

    private fun getRightPlaced(number: Int, generatedNumber: Int): Int{
        var digitsInNumber = mutableSetOf<Int>()
        var digitsInGeneratedNumber = mutableSetOf<Int>()
        var currentNumber = number
        var count = 0
        while(currentNumber > 0){
            digitsInNumber += currentNumber % 10
            currentNumber /= 10
        }
        //digitsInNumber.reverse()

        currentNumber = generatedNumber
        while(currentNumber > 0){
            digitsInGeneratedNumber += currentNumber % 10
            currentNumber /= 10
        }
        //digitsInGeneratedNumber.reverse()

        for(i in (digitsInNumber.size - 1) downTo 0 step 1){
            for(j in (digitsInGeneratedNumber.size - 1) downTo 0 step 1) {
                if (digitsInNumber.elementAt(i) == digitsInGeneratedNumber.elementAt(i)) {
                    count++
                    break
                }
            }
        }
        if(digitsInNumber.size == 1){
            for(i in (digitsInGeneratedNumber.size - 1) downTo 0 step 1){
                if (digitsInNumber.elementAt(0) == digitsInGeneratedNumber.elementAt(i)){
                    count++
                }
            }
        }
        return count

    }
}

fun main() {
    println("Hello World!")
    val app = App()
    app.playNumberGame(4)
    // TODO: call the App.playNumberGame function with and without default arguments

}
