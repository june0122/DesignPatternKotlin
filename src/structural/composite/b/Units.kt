package structural.composite.b

interface InfantryUnit {
}

class Rifleman : InfantryUnit {
}

class Sniper : InfantryUnit {
}

/**
 * this()를 사용하여 같은 클래스의 다른 생성자에게 위임
 * 주생성자가 존재한다면 부생성자는 무조건 주생성자에게 직간접적으로 생성을 위임해야 한다.
 */
class Squad(val infantryUnits: MutableList<InfantryUnit> = mutableListOf()) {
    constructor(first: InfantryUnit) : this(mutableListOf()) {
        this.infantryUnits.add(first)
    }

    constructor(first: InfantryUnit, second: InfantryUnit) : this(first) {
        this.infantryUnits.add(second)
    }

    constructor(first: InfantryUnit, second: InfantryUnit, third: InfantryUnit) : this(first, second) {
        this.infantryUnits.add(third)
    }
}

fun main() {
    val miller = Rifleman()
    val caparzo = Rifleman()
    val jackson = Sniper()

    /**
     * 여러 줄에 걸쳐 squad 내부의 컬렉션에 병사들을 추가하는 형태가 아닌 초기화와 동시에 병사들을 추가하고 싶다면?
     *
     * 1. 여러 개의 부생성자를 정의 -> 몇 개의 요소가 전달될지 예측할 수 없으므로 좋은 방법이 아님
     * 2. 부생성자 정의 + vararg 키워드
     */
    val squad = Squad(miller, caparzo, jackson)

    println(squad.infantryUnits.size) // Prints 3
}