package module

import org.example.module.Vector2d
import kotlin.random.Random

fun Map<Vector2d, *>.randomPosition(): Vector2d? {
    return keys.randomOrNull()
}

fun Map<Vector2d, *>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    var attempts = 0
    while (attempts < 100) {
        val randomX = Random.nextInt(0, mapSize.x )
        val randomY = Random.nextInt(0, mapSize.y )
        val potentialPosition = Vector2d(randomX, randomY)

        if (!containsKey(potentialPosition)) {
            return potentialPosition
        }
        attempts++
    }
    return null
}