package module

import org.example.module.Animal
import org.example.module.MoveDirection
import org.example.module.Vector2d
import org.example.module.util.IncorrectPositionException

class BouncyMap(private val width: Int, private val height: Int) : AbstractWorldMap() {

    init {
        lowerLeft = Vector2d(0, 0)
        upperRight = Vector2d(width - 1, height - 1)
    }

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.x in 0 until width && position.y in 0 until height
    }

    @Throws(IncorrectPositionException::class)
    override fun place(animal: Animal): Boolean {
        if (!canMoveTo(animal.position)) throw IncorrectPositionException(animal.position)

        val existingAnimal = animals[animal.position]
        if (existingAnimal == null) {
            animals[animal.position] = animal
            return true
        }

        val freePositions = findFreePositionsAround(animal.position)
        if (freePositions.isNotEmpty()) {
            val newPosition = freePositions.random()
            animal.position = newPosition
            animals[newPosition] = animal
            return true
        } else {
            val randomPosition = animals.keys.random()
            animals.remove(randomPosition)
            animals[animal.position] = animal
            return true
        }
    }

    private fun findFreePositionsAround(position: Vector2d): List<Vector2d> {
        val directions = listOf(
            Vector2d(1, 0), Vector2d(-1, 0), Vector2d(0, 1), Vector2d(0, -1)
        )
        return directions.map { position + it }
            .filter { canMoveTo(it) && !isOccupied(it) }
    }

    override fun objectAt(position: Vector2d): Animal? = animals[position]

    override fun randomFreePostion(vector2d: Vector2d): Vector2d? {
        return animals.randomFreePosition(vector2d)
    }

    override fun move(animal: Animal, direction: MoveDirection) {
        val oldPosition = animal.position

        animal.move(direction, this)
        if (animal.position != oldPosition)
        {
        if (isOccupied(animal.position)) {
            val freePositions = findFreePositionsAround(animal.position)
            if (freePositions.isNotEmpty()) {
                val newPosition = freePositions.random()
                animals.remove(oldPosition)
                animal.position = newPosition
                animals[newPosition] = animal
            }
        } else {
            animals.remove(oldPosition)
            animals[animal.position] = animal
        }
        }
    }

}