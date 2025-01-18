package module

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.example.module.Animal
import org.example.module.MapDirection
import org.example.module.MoveDirection
import org.example.module.Vector2d

class BouncyMapTest : StringSpec({

    "Place animal on the map" {
        val map = BouncyMap(5,5)
        val animal = Animal(Vector2d(2, 2))

        map.place(animal) shouldBe true
        map.isOccupied(Vector2d(2, 2)) shouldBe true
    }

    "Animal bounces when colliding with another animal" {
        val map = BouncyMap(5,5)
        val animal1 = Animal(Vector2d(2, 2))
        val animal2 = Animal(Vector2d(2, 2))

        map.place(animal1) shouldBe true

        val result = runCatching { map.place(animal2) }
        result.isSuccess shouldBe true
        map.isOccupied(Vector2d(2, 2)) shouldBe true
    }

    "No free position on the map" {
        val map = BouncyMap(1,1)
        val animal = Animal(Vector2d(0, 0))

        map.place(animal) shouldBe true
        val randomFreePos = map.randomFreePostion(Vector2d(1, 1))

        randomFreePos shouldBe null
    }

    "Animal should move to a free position" {
        val bouncyMap = BouncyMap(5,5)
        val animal = Animal(Vector2d(2, 2), MapDirection.NORTH)

        bouncyMap.place(animal) shouldBe true

        bouncyMap.move(animal, MoveDirection.FORWARD)
        animal.position shouldBe Vector2d(2, 3)
    }

    "Animal should bounce off another animal" {
        val bouncyMap = BouncyMap(5,5)
        val animal1 = Animal(Vector2d(2, 2), MapDirection.NORTH)
        val animal2 = Animal(Vector2d(2, 3), MapDirection.SOUTH)

        bouncyMap.place(animal1)
        bouncyMap.place(animal2)

        bouncyMap.move(animal1, MoveDirection.FORWARD)
        animal1.position shouldNotBe Vector2d(2, 3)
    }

    "Animal should not move outside the map boundaries" {
        val bouncyMap = BouncyMap(5,5)
        val animal = Animal(Vector2d(4, 4), MapDirection.NORTH)

        bouncyMap.place(animal) shouldBe true
        bouncyMap.move(animal, MoveDirection.FORWARD)
        animal.position shouldBe Vector2d(4, 4)
    }
})
