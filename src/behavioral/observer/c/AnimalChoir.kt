package behavioral.observer.c

typealias Times = Int

enum class SoundPitch { HIGH, LOW }

interface Message {
    val repeat: Times
    val pitch: SoundPitch
}

data class LowMessage(override val repeat: Times) : Message {
    override val pitch: SoundPitch = SoundPitch.LOW
}

data class HighMessage(override val repeat: Times) : Message {
    override val pitch: SoundPitch = SoundPitch.HIGH
}

class Cat {
    private val participants = mutableMapOf<() -> Unit, () -> Unit>()

    fun joinChoir(whatToCall: () -> Unit) {
        participants.put(whatToCall, whatToCall)
    }

    fun leaveChoir(whatNotToCall: () -> Unit) {
        participants.remove(whatNotToCall)
    }

    fun conduct() {
        for (p in participants.values) {
            p()
        }
    }
}

class Bat {
    fun screech(message: Message) {
        when (message) {
            is HighMessage -> {
                for (i in 1..message.repeat) {
                    println("${message.pitch} Eeeeeee")
                }
            }
            else -> println("Can't :(")
        }
    }
}

class Turkey {
    fun gobble(message: Message) {
        when (message) {
            is LowMessage -> {
                for (i in 1..message.repeat) {
                    println("${message.pitch} Gob-gob")
                }
            }
            else -> println("Can't :(")
        }
    }
}

class Dog {
    fun bark(message: Message) {
        when (message) {
            is LowMessage -> {
                for (i in 1..message.repeat) {
                    println("${message.pitch} Woof")
                }
            }

            is HighMessage -> {
                for (i in 1..message.repeat) {
                    println("${message.pitch} Woof")
                }
            }
        }
    }

    fun howl() {
        println("Auuuu")
    }
}

fun main() {
    val catConductor = Cat()
    val bat = Bat()
    val dog = Dog()
    val screech = { bat.screech(HighMessage(5)) }

    catConductor.joinChoir(screech)
    catConductor.joinChoir(dog::howl)
    catConductor.leaveChoir(screech)

    catConductor.conduct()
}