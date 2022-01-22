# 컴포지트 패턴<small>(Composite Pattern)</small>

> 부분과 전체의 계층을 표현하기 위해 객체들을 모아 트리 구조로 구성합니다. 사용자로 하여금 개별 객체와 복합 객체를 모두 동일하게 다룰 수 있도록 하는 패턴

> 이름이 혼란을 줄 수 있는데, 컴포지트 패턴은 객체를 구성<small>(composite)</small>하는 것이 아니라 다른 유형의 객체들을 동일한 트리의 노드로 취급하는 것에 관한 것이다. 이를 위해서는 모두 동일한 인터페이스를 구현해야 한다.

## 활용성

컴포지트 패턴은 다음과 같은 경우에 사용한다.

- 부분-전체의 객체 계통을 표현하고 싶을 때
- 사용자가 객체의 합성으로 생긴 복합 객체와 개개의 객체 사이의 차이를 알지 않고도 자기 일을 할 수 있도록 만들고 싶을 때. 사용자는 복합 구조<small>(composite structure)</small>의 모든 객체를 똑같이 취급하게 된다.

## 구조

<p align = 'center'>
<img width = '700' src = 'https://user-images.githubusercontent.com/39554623/150236872-2679f7ef-f684-47d4-aaa0-845a9730dd79.png'>
</p>

- **Component**: 집합 관계에 정의될 모든 객체에 대한 인터페이스를 정의한다. 모든 클래스에 해당하는 인터페이스에 대해서는 공통의 행동을 구현한다. 전체 클래스에 속한 요소들을 관리하는 데 필요한 인터페이스를 정의한다. 순환 구조에서 요소들을 포함하는 전체 클래스로 접근하는 데 필요한 인터페이스를 정의하며, 적절하다면 그 인터페이스를 구현한다.
- **Leaf**: 가장 말단의 객체, 즉 자식이 없는 객체를 나타낸다. 객체 합성에 가장 기본이 되는 객체의 행동을 정의한다.
- **Composite**: 자식이 있는 구성요소에 대한 행동을 정의한다. 자신이 복합하는 요소들을 저장하면서, Component 인터페이스에 정의된 자식 관련 연산을 구현한다.
- **Client**: Component 인터페이스를 통해 복합 구조 내의 객체들을 조작한다.

## 협력 방법

- 사용자는 복합 구조 내 객체 간의 상호작용을 위해 Component 클래스 인터페이스를 사용한다. 요청받은 대상이 Leaf 인스턴스이면 자신이 정의한 행동을 직접 수행하고, 대상이 Composite이면 자식 객체들에게 요청을 위임한다. 위임하기 전후에 다른 처리를 수행할 수도 있다.

## 결과

복합체 패턴으로 발생하는 결과는 다음과 같다.

- 기본 객체와 복합 객체로 구성된 하나의 일관된 클래스 계통을 정의한다. 기본 객체는 더 복합적인 객체들에 속해있을 수 있다. 물론 이 복합 객체 역시 다른 것에 속해있는 것일 수 있다. 그러나 사용자 코드는 일반화된 상위 개념의 객체를 조작하는 방식으로 프로그래밍하면, 런타임 기본 객체와 복합 객체를 구분하지 않고 일관되게 프로그래밍할 수 있게 된다.
- 사용자의 코드가 단순해진다. 사용자 코드는 복합 구조이나 단일 객체와 동일하게 다루는 코드로 작성되기 때문이다. 즉, 사용자는 객체의 특성이 복합 구조인지 단일 구조인지조차 모르고 개발할 수 있다. 이런 구분이 필요치 않으므로 개발자의 코드에 "꼬리표-case-문장" 스타일의 함수를 쓸 필요가 없어지므로 코드가 단순해진다.
- 새로운 종류의 구성요소를 쉽게 추가할 수 있다. 새롭게 정의된 Composite나 Leaf의 서브클래스들은 기존에 존재하는 구조들과 독립적으로 동작이 가능하게 된다. 그러므로 요소가 추가되었다고 해서 사용자의 프로그램이 변경될 필요는 전혀 없다.
- 설계가 지나치게 범용성을 많이 가진다. 새로운 요소를 쉽게 추가할 때의 단점은 복합체의 구성요소에 제약을 가하기 힘들다는 것이다. 가끔 복합체가 오직 한 개의 구성요소만 가졌으면 할 때가 있다. Composite 클래스만으로 타입 시스템을 통해 이런 제약을 가할 수 없다. 런타임 점검이 들어가야 한다.

## 관련 패턴

- 구성요소-부모 간의 연결은 책임 연쇄 패턴에서 많이 사용되는 예이다.
- 장식자 패턴은 자주 컴포지트 패턴과 함께 사용된다. 이 두 패턴이 함께 사용될 때는 둘 다 동일한 하나의 부모 클래스를 상속받게 된다. 따라서 장식자는 add(), remove(), getChild()와 같은 연산을 통해서 Component의 인터페이스를 지원해야 한다.
- 플라이급 패턴으로 구성요소의 공유 방법을 얻을 수 있다. 단, 공유되는 구성요소의 부모는 참조할 수 없다.
- 반복자 패턴을 이용하면, 구성요소를 순회하는 방법을 얻을 수 있다.
- 방문자 패턴을 이용하면, 이 패턴을 사용하지 않을 대 Composite와 Leaf 클래스에 걸쳐 분산될 수 있는 행동을 국소화시킬 수 있다.

## 예제 코드 1

<p align = 'center'>
<img width = '700' src = 'https://user-images.githubusercontent.com/39554623/150518863-99a71d2b-4048-4886-97b1-9814ae6f9dca.png'>
</p>

```kotlin
import java.util.*

/** "Component" */
interface Graphic {
    fun print()
}
/** "Composite" */
class CompositeGraphic(val graphics: ArrayList<Graphic> = ArrayList()) : Graphic {
    //Prints the graphic.
    override fun print() = graphics.forEach(Graphic::print)
    //Adds the graphic to the composition.
    fun add(graphic: Graphic) {
        graphics.add(graphic)
    }
    //Removes the graphic from the composition.
    fun remove(graphic: Graphic) {
        graphics.remove(graphic)
    }
}
class Ellipse : Graphic {
    override fun print() = println("Ellipse")
}
class Square : Graphic {
    override fun print() = println("Square")
}
fun main(args: Array<String>) {
    //Initialize four ellipses
    val ellipse1 = Ellipse()
    val ellipse2 = Ellipse()
    val ellipse3 = Ellipse()
    val ellipse4 = Ellipse()
    //Initialize four squares
    val square1 = Square()
    val square2 = Square()
    val square3 = Square()
    val square4 = Square()
    //Initialize three composite graphics
    val graphic = CompositeGraphic()
    val graphic1 = CompositeGraphic()
    val graphic2 = CompositeGraphic()
    //Composes the graphics
    graphic1.add(ellipse1)
    graphic1.add(ellipse2)
    graphic1.add(square1)
    graphic1.add(ellipse3)
    graphic2.add(ellipse4)
    graphic2.add(square2)
    graphic2.add(square3)
    graphic2.add(square4)
    graphic.add(graphic1)
    graphic.add(graphic2)
    //Prints the complete graphic
    graphic.print()
}
```

## 예제 코드 2

> 전체 코드 : https://github.com/june0122/DesignPatternKotlin/tree/master/src/structural/composite

### 1.

#### Units.kt

```kotlin
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
```

### 2.

#### Units.kt

```kotlin
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
```


### 3.

#### Ammunition.kt

```kotlin
interface CanCountBullets {
    fun bulletsLeft(): Int
}

class Bullet {
}

class Magazine(capacity: Int) : CanCountBullets {
    private val bullets = List(capacity) { Bullet() }

    override fun bulletsLeft(): Int = bullets.size
}
```
#### Units.kt

```kotlin
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


```

## References

1. GoF의 디자인 패턴(개정판). 챕터 4. 226쪽
2. Hands-on Design Patterns with Kotlin
3. https://chercher.tech/kotlin/composite-design-pattern-kotlin