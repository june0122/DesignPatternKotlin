package structural.composite.c

interface InfantryUnit : CanCountBullets {
}

class Rifleman(initialMagazines: Int = 3) : InfantryUnit {
    private val magazines = List(initialMagazines) { Magazine(30) }
    override fun bulletsLeft(): Int {
        return magazines.sumOf { it.bulletsLeft() }
    }
}

class Sniper(initialBullets: Int = 30) : InfantryUnit {
    private val bullets = List(initialBullets) { Bullet() }
    override fun bulletsLeft(): Int = bullets.size
}

class Squad(val infantryUnits: MutableList<InfantryUnit> = mutableListOf()) : CanCountBullets {
    constructor(vararg units: InfantryUnit) : this(mutableListOf()) {
        for (u in units) {
            this.infantryUnits.add(u)
        }
    }

    override fun bulletsLeft(): Int {
        return infantryUnits.sumOf { it.bulletsLeft() }
    }
}

fun main() {
    val miller = Rifleman()
    val caparzo = Rifleman()
    val jackson = Sniper()

    val squad = Squad(miller, caparzo, jackson)

    println(squad.infantryUnits.size) // 3
    println(squad.bulletsLeft()) // (30 * 3 * 2) + 30 = 210
}

/**
 * 갑작스럽게 소대(platoon)를 구현해야 하는 상황이 발생해도 간단하게 처리할 수 있다.
 */
class Platoon(val squadUnits: MutableList<Squad> = mutableListOf()) : CanCountBullets {
    constructor(vararg squads: Squad) : this(mutableListOf()) {
        for (s in squads) {
            this.squadUnits.add(s)
        }
    }

    override fun bulletsLeft(): Int {
        return squadUnits.sumOf { it.bulletsLeft() }
    }
}

/*
    val john = Sniper()
    val webster = Sniper()
    val nixon = Sniper()
    val sniperSquad = Squad(john, webster, nixon)

    val platoon1 = Platoon(squad, sniperSquad)

    println(platoon1.bulletsLeft()) // 210 + 90 = 300
 */

