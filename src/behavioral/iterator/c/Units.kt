package behavioral.iterator.c

interface InfantryUnit {
}

class Rifleman(val name: String) : InfantryUnit {
    override fun toString(): String {
        return "Rifleman: $name"
    }
}

class Sniper(val name: String) : InfantryUnit {
    override fun toString(): String {
        return "Sniper: $name"
    }
}

class Sergeant : InfantryUnit {
    override fun toString(): String {
        return "Sergeant"
    }
}

// 중위
class Lieutenant : InfantryUnit {
    override fun toString(): String {
        return "Lieutenant"
    }
}

class Squad(val infantryUnits: MutableList<InfantryUnit> = mutableListOf()) {
    val commander = Sergeant()

    constructor(vararg units: InfantryUnit) : this(mutableListOf()) {
        for (u in units) {
            this.infantryUnits.add(u)
        }
    }

    operator fun iterator() = object : Iterator<InfantryUnit> {
        var i = 0
        override fun hasNext(): Boolean {
            return i < infantryUnits.size + 1 // sergeant가 있으므로 +1
        }

        override fun next(): InfantryUnit =
            when (i) {
                0 -> commander
                else -> infantryUnits[i - 1]
            }.also { i++ } // 다음 유닛을 반환하는 동시에 카운터를 증가시킨다.
    }

    /**
     * Since it's just a simple function that returns an iterator now,
     * we don't need the operator keyword
     */
    fun reverseIterator() = object : Iterator<InfantryUnit> {
        var i = 0
        override fun hasNext(): Boolean {
            return i < infantryUnits.size + 1 // sergeant가 있으므로 +1
        }

        override fun next(): InfantryUnit =
            when (i) {
                infantryUnits.size -> commander
                else -> infantryUnits[infantryUnits.size - i - 1]
            }.also { i++ }
    }
}

class Platoon(val squads: MutableList<Squad> = mutableListOf()) {
    val commander = Lieutenant()

    constructor(vararg squads: Squad) : this(mutableListOf()) {
        for (s in squads) {
            this.squads.add(s)
        }
    }

    operator fun iterator() = object : Iterator<InfantryUnit> {
        var i = 0
        var j = 0
        var count = 0

        override fun hasNext(): Boolean {
            return count < squads.sumOf { it.infantryUnits.size } + squads.size + 1 // squad 내부의 보병수 + (병장 * squad 수) + 중위
        }

        override fun next(): InfantryUnit =
            when (i) {
                0 -> commander
                else -> {
                    when (j) {
                        0 -> squads[i - 1].commander
                        else -> squads[i - 1].infantryUnits[j - 1]
                    }.also { j++ }
                }
            }.also {
                if (i == 0 || j > squads[i - 1].infantryUnits.size) {
                    i++
                    j = 0
                }
                count++
            }
    }
}

// iterator를 인자로 받는 함수를 정의
fun <T> printAll(iter: Iterator<T>) {
    while (iter.hasNext()) {
        println(iter.next())
    }
}

fun main() {
    val josh = Rifleman("Josh")
    val ewan = Rifleman("Ewan")
    val tom = Sniper("Tom")
    val sam = Rifleman("Sam")
    val eric = Sniper("Eric")
    val william = Sniper("William")

    val rangers = Squad(josh, ewan, tom)
    val deltaForce = Squad(sam, eric, william)
    val blackHawk = Platoon(rangers, deltaForce)

    printAll(rangers.iterator())

    println()

    printAll(blackHawk.iterator())

    println()

    for (u in blackHawk) {
        println(u)
    }
}