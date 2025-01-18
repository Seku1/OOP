package module

import org.example.module.MapDirection
import org.example.module.Vector2d

fun MapDirection.toUnitVector(): Vector2d {
    return when (this) {
        MapDirection.NORTH -> Vector2d(0, 1)
        MapDirection.SOUTH -> Vector2d(0, -1)
        MapDirection.EAST -> Vector2d(1, 0)
        MapDirection.WEST -> Vector2d(-1, 0)
    }
}