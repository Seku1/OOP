package org.example.module

import module.toUnitVector

data class Animal(
    var position: Vector2d = Vector2d(2,2),
    var direction: MapDirection = MapDirection.NORTH
) {
    fun move(moveDirection: MoveDirection, map: WorldMap) {
        when (moveDirection) {
            MoveDirection.RIGHT -> direction = direction.next()
            MoveDirection.LEFT -> direction = direction.prev()
            MoveDirection.FORWARD -> {
                val potentialNewPosition = position + direction.toUnitVector()
                if (map.canMoveTo(potentialNewPosition)) {
                    position = potentialNewPosition
                }
            }
            MoveDirection.BACKWARD -> {
                val potentialNewPosition = position - direction.toUnitVector()
                if (map.canMoveTo(potentialNewPosition)) {
                    position = potentialNewPosition
                }
            }
        }
    }

    override fun toString(): String = direction.toString()

    fun isAt(position: Vector2d): Boolean = this.position == position
}