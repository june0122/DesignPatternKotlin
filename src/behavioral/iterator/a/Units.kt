package behavioral.iterator.a

interface InfantryUnit {
}

class Rifleman : InfantryUnit {
}

class Sniper : InfantryUnit {
}

class Sergeant: InfantryUnit

// 중위
class Lieutenant: InfantryUnit

class Squad(val infantryUnits: MutableList<InfantryUnit> = mutableListOf()) {
    val commander = Sergeant()

    constructor(vararg units: InfantryUnit) : this(mutableListOf()) {
        for (u in units) {
            this.infantryUnits.add(u)
        }
    }
}

class Platoon(val squads: MutableList<Squad> = mutableListOf()) {
    val commander = Lieutenant()

    constructor(vararg squads: Squad) : this(mutableListOf()) {
        for (s in squads) {
            this.squads.add(s)
        }
    }
}

fun main() {
    val josh = Rifleman()
    val ewan = Rifleman()
    val tom = Sniper()
    val sam = Rifleman()
    val eric = Sniper()
    val william = Sniper()

    val rangers = Squad(josh, ewan, tom)
    val deltaForce = Squad(sam, eric, william)
    val blackHawk = Platoon(rangers, deltaForce)

    // For-loop range must have an 'iterator()' method
    /*
    for (u in blackHawk) {
        println(u)
    }
     */
}