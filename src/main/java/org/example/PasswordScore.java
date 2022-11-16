package org.example;

import java.math.BigInteger;

public class PasswordScore {
    private final int length;
    private final int cardinality;

    public PasswordScore(int length, int cardinality) {
        this.length = length;
        this.cardinality = cardinality;
    }

    private BigInteger secToCrack() {
        BigInteger bigCardinality = BigInteger.valueOf(cardinality);
        BigInteger combinations = bigCardinality.pow(length);
        BigInteger HashPerSec = BigInteger.valueOf(632_000_000_000L);
        return combinations.divide(HashPerSec);
    }

    public void timeToCrack() {
        BigInteger timeInSeconds = secToCrack();
        BigInteger minutes = BigInteger.valueOf(60L);
        BigInteger hours = BigInteger.valueOf(3600L);
        BigInteger days = BigInteger.valueOf(86400L);
        BigInteger years = BigInteger.valueOf(31536000L);

        String MESSAGE = "It will take %d %s to crack your password\n";
        if (timeInSeconds.compareTo(minutes) < 0) {
            System.out.printf(MESSAGE, timeInSeconds, "sec");
        } else if (timeInSeconds.compareTo(hours) < 0) {
            System.out.printf(MESSAGE, timeInSeconds.divide(minutes), "minute(s)");
        } else if (timeInSeconds.compareTo(days) < 0) {
            System.out.printf(MESSAGE, timeInSeconds.divide(hours), "hour(s)");
        } else if (timeInSeconds.compareTo(years) < 0) {
            System.out.printf(MESSAGE, timeInSeconds.divide(days), "day(s)");
        } else if (timeInSeconds.compareTo(BigInteger.valueOf(31536000000L)) < 0) {
            System.out.printf(MESSAGE, timeInSeconds.divide(years), "year(s)");
        } else {
            System.out.println("It will take millennia to crack your password");
        }
    }

}

