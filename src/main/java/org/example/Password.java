package org.example;

public class Password {
    private final String password;

    public Password(String password) {
        this.password = password;
    }

    public int getLength() {
        return password.length();
    }

    public int getCardinality() {
        int cardinality = 0;
        if (password.matches(".*[a-z].*")) {
            cardinality += 26;
        }
        if (password.matches(".*[A-Z].*")) {
            cardinality += 26;
        }
        if (password.matches(".*\\d.*")) {
            cardinality += 10;
        }
        if (password.matches(".*\\W.*")) {
            cardinality += 32;
        }
        return cardinality;
    }

    @Override
    public String toString() {
        return password;
    }
}
