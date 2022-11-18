package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PasswordTest {
    private Password password;

    @Test
    void getLength() {
        password = new Password("newPassword");
        assertEquals(11, password.getLength());
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    void getCardinality(String newPassword, int expected) {
        password = new Password(newPassword);
        assertEquals(expected, password.getCardinality());
    }

    static Stream<Arguments> argumentsProvider() {
        return Stream.of(
                arguments("", 0),
                arguments("asdf", 26),
                arguments("asdf12", 36),
                arguments("AAAAasd", 52),
                arguments("asdf^%", 58),
                arguments("AAAAasd11", 62),
                arguments("AAAAasd$", 84),
                arguments("asdf*&^876", 68),
                arguments("AAAAasd11$", 94),
                arguments("AAAA", 26),
                arguments("AAAA12", 36),
                arguments("KJHF&^%", 58),
                arguments("SDLFJK123^*&", 68),
                arguments("123", 10),
                arguments("@#!876", 42),
                arguments("%^*^", 32)
        );
    }

    @Test
    void testToString() {
        password = new Password("password");
        assertEquals("password", password.toString());
    }
}