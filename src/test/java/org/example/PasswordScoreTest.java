package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PasswordScoreTest {

    private PasswordScore passwordScore;

    @Test
    void secToCrack() {
    }

    @ParameterizedTest
    @MethodSource("cardinalityArguments")
    void getCardinality(String testPassword, int cardinality) {
        Password password = new Password(testPassword);
        passwordScore = new PasswordScore(password);
        assertEquals(BigInteger.valueOf(cardinality), passwordScore.getCardinality());
    }

    static Stream<Arguments> cardinalityArguments() {
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
    void getCombinations() {
    }

    @Test
    void timeToCrack() {
    }
}
