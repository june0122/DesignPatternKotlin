package behavioral.iterator.b

interface InfantryUnit {
}

class Rifleman : InfantryUnit {
}

class Sniper : InfantryUnit {
}

class Sergeant : InfantryUnit

// 중위
class Lieutenant : InfantryUnit

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

// iterator를 인자로 받는 함수를 정의
fun <T> printAll(iter: Iterator<T>) {
    while (iter.hasNext()) {
        println(iter.next())
    }
}

class Platoon(val squads: MutableList<Squad> = mutableListOf()) {
    val commander = Lieutenant()

    constructor(vararg squads: Squad) : this(mutableListOf()) {
        for (s in squads) {
            this.squads.add(s)
        }
    }

//    operator fun iterator() = object : Iterator<InfantryUnit> {
//        var i = 0
//        override fun hasNext(): Boolean {
//        }
//
//        override fun next(): InfantryUnit {
//
//        }
//    }
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

    printAll(rangers.iterator())
//    printAll(rangers.reverseIterator())
}