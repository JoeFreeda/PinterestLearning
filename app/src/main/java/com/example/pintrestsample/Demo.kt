package com.example.pintrestsample

fun main() {
//    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
//    val monthsAndDays = listOf("Jan", "Feb", "March", "April", "May", "Monday", "Tuesday")
//    findCommonItems(daysOfWeek,monthsAndDays)
//    val array = MyArrayList()
//    array.add(arrayOf(1,2,3,6,7,89,3,2,655,77))
//    val list = array.getAll()
//    for (items in list){
//        println(items)
//    }
//    palindrome("MOM")
    fibonacci(listOf(0, 1, 1, 2, 3, 5,7))
}

fun fibonacci(givenInput: List<Int>) {
    for (number in 0 until givenInput.size - 2) {
//        if(number<givenInput.size-2) {
            val firstNum = givenInput[number]
            val secondNum = givenInput[number + 1]
            if (firstNum + secondNum != givenInput[number + 2]) {
                println("No Fibonacci")
                return
            }
//        }
    }
    println("Fibonacci")
}


fun palindrome(givenInput: String) {
    var outputValue: String = ""
    for (index in givenInput.length - 1 downTo 0) {
        outputValue += givenInput[index]
    }
    if (givenInput == outputValue)
        println("Palindrome")
}

class MyArrayList {
    private lateinit var arrayFormation: Array<Any?>

    fun add(num: Array<Any>) {
        arrayFormation = arrayOfNulls(num.size)
        for (number in num.indices) {
            arrayFormation[number] = num[number]
        }
    }

    fun getAll(): Array<Any?> {
        return arrayFormation
    }


    companion object {
        private var COUNT = 0
    }
}


fun findCommonItems(daysOfWeek: List<String>, monthsAndDays: List<String>) {
    for (days in daysOfWeek) {
        if (monthsAndDays.contains(days)) {
            println(days)
        }
    }
}
