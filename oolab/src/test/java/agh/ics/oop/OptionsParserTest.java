package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionsParserTest {
    @Test
    void ForwardTest() {
        String[] args = {"f"};
        List<MoveDirection> expected = List.of(MoveDirection.FORWARD);
        assertEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void BackwardTest() {
        String[] args = {"b"};
        List<MoveDirection> expected = List.of(MoveDirection.BACKWARD);
        assertEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void RightTest() {
        String[] args = {"r"};
        List<MoveDirection> expected = List.of(MoveDirection.RIGHT);
        assertEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void LeftLeft() {
        String[] args = {"l"};
        List<MoveDirection> expected = List.of(MoveDirection.LEFT);
        assertEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void parse_shouldThrowExceptionForUnknownDirection() {
        String[] args = {"x"};
        List<MoveDirection> expected = List.of();
        assertEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void translate_shouldTranslateMultipleDirections() {
        String[] args = {"f", "b", "r", "l"};
        List<MoveDirection> expected = List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        );
        assertEquals(expected, OptionsParser.parse(args));
    }
}