package behavioral.observer.a

typealias Times = Int

class Cat {
    private val participants = mutableMapOf<() -> Unit, () -> Unit>()

    fun joinChoir(whatToCall: () -> Unit) {
        participants.put(whatToCall, whatToCall)
    }

    fun leaveChoir(whatNotToCall: () -> Unit) {
        participants.remove(whatNotToCall)
    }

    fun conduct(n: Times) {
        for (p in participants.values) {
            for (i in 1..n) {
                p()
            }
        }
    }
}

class Bat {
    fun screech() {
        println("Eeeeeee")
    }
}

class Turkey {
    fun gobble() {
        println("Gob-gob")
    }
}

class Dog {
    fun bark() {
        println("Woof")
    }

    fun howl() {
        println("Auuuu")
    }
}

fun main() {
    val catConductor = Cat()

    val bat = Bat()
    val dog = Dog()
    val turkey = Turkey()

    catConductor.joinChoir(bat::screech)
    catConductor.joinChoir(dog::bark)
    catConductor.joinChoir(turkey::gobble)
    catConductor.leaveChoir(bat::screech)

    catConductor.conduct(1)
}


//class Cat {
//    private val participants = mutableMapOf<(Int) -> Unit, (Int) -> Unit>()
//
//    fun joinChoir(whatToCall: (Int) -> Unit) {
//        participants.put(whatToCall, whatToCall)
//    }
//
//    fun leaveChoir(whatNotToCall: (Int) -> Unit) {
//        participants.remove(whatNotToCall)
//    }
//
//    fun conduct(n: Times) {
//        for (p in participants.values) {
//            p(n)
//        }
//    }
//}
//
//class Bat {
//    fun screech(repeat: Times) {
//        for (i in 1..repeat) {
//            println("Eeeeeee")
//        }
//    }
//}
//
//class Turkey {
//    fun gobble(repeat: Times) {
//        for (i in 1..repeat) {
//            println("Gob-gob")
//        }
//    }
//}
//
//class Dog {
//    fun bark(repeat: Times) {
//        for (i in 1..repeat) {
//            println("Woof")
//        }
//    }
//
//    fun howl(repeat: Times) {
//        for (i in 1..repeat) {
//            println("Auuuu")
//        }
//    }
//}