package org.example.module.util

import org.example.module.Vector2d

class IncorrectPositionException(val position: Vector2d) : Exception("Position: $position is already occupied")