package module

import module.util.MapVisualizer
import org.example.module.Animal
import org.example.module.MoveDirection
import org.example.module.Vector2d
import org.example.module.WorldMap
import org.example.module.util.Boundary
import org.example.module.util.IncorrectPositionException
import java.util.*
import kotlin.collections.ArrayList

abstract class AbstractWorldMap : WorldMap {
    private val id: UUID = UUID.randomUUID()
    protected var lowerLeft: Vector2d = Vector2d(Int.MIN_VALUE, Int.MIN_VALUE)
    protected var upperRight: Vector2d = Vector2d(Int.MAX_VALUE, Int.MAX_VALUE)
    protected val animals = mutableMapOf<Vector2d, Animal>()
    protected val visualizer = MapVisualizer(this)


    override fun canMoveTo(position: Vector2d): Boolean =
        objectAt(position)?.let { false } ?: true

    @Throws(IncorrectPositionException::class)
    override fun place(animal: Animal): Boolean {
        if (canMoveTo(animal.position)) {
            animals[animal.position] = animal
            return true
        }
        throw IncorrectPositionException(animal.position)
    }

    override fun move(animal: Animal, direction: MoveDirection) {
        val oldPosition = animal.position
        animal.move(direction, this)
        animals.remove(oldPosition)
        animals[animal.position] = animal
    }

    override fun isOccupied(position: Vector2d): Boolean = animals.containsKey(position)

    override fun objectAt(position: Vector2d): Animal? = animals[position]

    override fun getElements(): List<Animal> = animals.values.toList()

    override fun getCurrentBounds(): Boundary = Boundary(lowerLeft, upperRight)

    override fun getID(): UUID = id

    override fun getOrderedAnimals(): ArrayList<Animal> =
        animals.values.sortedWith(compareBy({ it.position.x }, { it.position.y }))
            .toCollection(ArrayList())

    override fun toString(): String =
        visualizer.draw(getCurrentBounds().lowerLeft, getCurrentBounds().upperRight)

    abstract fun randomFreePostion(vector2d: Vector2d): Vector2d?
}