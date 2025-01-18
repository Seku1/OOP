package module.util

import org.example.module.Vector2d
import org.example.module.WorldMap

class MapVisualizer(private val map: WorldMap) {
    companion object {
        private const val EMPTY_CELL = " "
        private const val FRAME_SEGMENT = "-"
        private const val CELL_SEGMENT = "|"
    }

    fun draw(lowerLeft: Vector2d, upperRight: Vector2d): String {
        val builder = StringBuilder()
        builder.append(drawHeader(lowerLeft, upperRight))

        for (y in upperRight.y downTo lowerLeft.y) {
            builder.append(String.format("%3d: ", y))
            for (x in lowerLeft.x..upperRight.x) {
                builder.append(CELL_SEGMENT).append(drawObject(Vector2d(x, y)))
            }
            builder.append(CELL_SEGMENT).append(System.lineSeparator())
        }

        builder.append(drawFooter(lowerLeft, upperRight))
        return builder.toString()
    }

    private fun drawHeader(lowerLeft: Vector2d, upperRight: Vector2d): String {
        val builder = StringBuilder(" y\\x ")
        for (x in lowerLeft.x..upperRight.x) {
            builder.append(String.format("%3d", x))
        }
        builder.append(System.lineSeparator())
        return builder.toString()
    }

    private fun drawFooter(lowerLeft: Vector2d, upperRight: Vector2d): String {
        val builder = StringBuilder("    ")
        for (x in lowerLeft.x..upperRight.x) {
            builder.append(FRAME_SEGMENT.repeat(3))
        }
        builder.append(System.lineSeparator())
        return builder.toString()
    }

    private fun drawObject(currentPosition: Vector2d): String {
        return map.objectAt(currentPosition)?.toString() ?: EMPTY_CELL
    }
}
