package org.example.module


import org.example.module.util.Boundary
import org.example.module.util.IncorrectPositionException
import java.util.*

interface WorldMap : MoveValidator {

    /**
     * Place an animal on the map.
     * @param animal The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the move is not valid.
     * @throws IncorrectPositionException if the position is invalid.
     */
    @Throws(IncorrectPositionException::class)
    fun place(animal: Animal): Boolean

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    fun move(animal: Animal, direction: MoveDirection)

    /**
     * Return true if the given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    fun isOccupied(position: Vector2d): Boolean

    /**
     * Return an element at a given position.
     * @param position The position of the animal.
     * @return WorldElement or null if the position is not occupied.
     */
    fun objectAt(position: Vector2d): Animal?

    /**
     * Return a collection of all elements (animals and grass) on the map.
     */
    fun getElements(): List<Animal>

    fun getCurrentBounds(): Boundary

    override fun toString(): String

    fun getID(): UUID

    fun getOrderedAnimals(): List<Animal>
}
