package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PasswordScoreTest {

    private PasswordScore passwordScore;
    private Password password;

    @ParameterizedTest
    @MethodSource("secToCrackArguments")
    void secToCrack(String newPassword, long sec) {
        password = new Password(newPassword);
        passwordScore = new PasswordScore(password);
        assertEquals(BigInteger.valueOf(sec), passwordScore.secToCrack());
    }

    static Stream<Arguments> secToCrackArguments() {
        return Stream.of(
                arguments("aabbaabba", 8L),
                arguments("aaaaabbbbb12345", 349800505887L),
                arguments("aaaaabbbbbAB", 618476276L),
                arguments("aaaaabbbbbABABC", 86962712227877L),
                arguments("ABCDE12345&*(^", 71516738374622L),
                arguments("aBCDE1234&*(^", 70785616443892L)
        );
    }

    @ParameterizedTest
    @MethodSource("cardinalityArguments")
    void getCardinality(String testPassword, int cardinality) {
        password = new Password(testPassword);
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

    @ParameterizedTest
    @MethodSource("getCombinationsArguments")
    void getCombinations(String testPassword, long combinations) {
        password = new Password(testPassword);
        passwordScore = new PasswordScore(password);
        assertEquals(BigInteger.valueOf(combinations), passwordScore.getCombinations());
    }

    static Stream<Arguments> getCombinationsArguments() {
        return Stream.of(
                arguments("", 1L),
                arguments("5", 10L),
                arguments("abcdefgh", 208827064576L),
                arguments("aaaaabbAB", 2779905883635712L)
        );
    }
}
