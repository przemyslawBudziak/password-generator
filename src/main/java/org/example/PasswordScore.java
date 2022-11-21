package org.example;

import java.math.BigInteger;

public class PasswordScore {

    private final Password password;

    public PasswordScore(Password password) {
        this.password = password;
    }

    BigInteger secToCrack() {
        BigInteger HashPerSec = BigInteger.valueOf(632_000_000_000L);
        return getCombinations().divide(HashPerSec);
    }

    BigInteger getCombinations() {
        return getCardinality().pow(password.getLength());
    }

    BigInteger getCardinality() {
        return BigInteger.valueOf(password.getCardinality());
    }

    void timeToCrack() {
        BigInteger timeInSeconds = secToCrack();
        BigInteger minutes = BigInteger.valueOf(60L);
        BigInteger hours = BigInteger.valueOf(3600L);
        BigInteger days = BigInteger.valueOf(86400L);
        BigInteger years = BigInteger.valueOf(31536000L);

        String MESSAGE = "It will take %d %s to crack your password\n\n";
        if (timeInSeconds.compareTo(BigInteger.valueOf(1)) < 0) {
            System.out.println("It will take less than second to crack your password\n");
        } else if (timeInSeconds.compareTo(minutes) < 0) {
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
            System.out.println("It will take millennia to crack your password\n");
        }
    }

}

