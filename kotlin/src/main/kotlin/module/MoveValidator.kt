package org.example.module

interface MoveValidator {
    fun canMoveTo(position: Vector2d) : Boolean
}