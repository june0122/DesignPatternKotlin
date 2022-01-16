package structural.composite.c

interface CanCountBullets {
    fun bulletsLeft(): Int
}

class Bullet {
}

class Magazine(capacity: Int) : CanCountBullets {
    private val bullets = List(capacity) { Bullet() }

    override fun bulletsLeft(): Int = bullets.size
}