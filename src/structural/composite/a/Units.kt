package structural.composite.a

interface InfantryUnit {
}

class Rifleman : InfantryUnit {
}

class Sniper : InfantryUnit {
}

class Squad(val infantryUnits: MutableList<InfantryUnit> = mutableListOf()) {
}

fun main() {
    val miller = Rifleman()
    val caparzo = Rifleman()
    val jackson = Sniper()

    val squad = Squad()

    squad.infantryUnits.add(miller)
    squad.infantryUnits.add(caparzo)
    squad.infantryUnits.add(jackson)

    println(squad.infantryUnits.size) // Prints 3
}