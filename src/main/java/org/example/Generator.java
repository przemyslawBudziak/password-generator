package org.example;

public class Generator {
    private final int length;
    private final boolean includeAlphaLower;
    private final boolean includeAlphaUpper;
    private final boolean includeNumbers;
    private final boolean includeSpecial;

    private static final String ALPHA_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHA_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    public Generator(int length, boolean includeAlphaLower, boolean includeAlphaUpper, boolean includeNumbers, boolean includeSpecial) {
        this.length = length;
        this.includeAlphaLower = includeAlphaLower;
        this.includeAlphaUpper = includeAlphaUpper;
        this.includeNumbers = includeNumbers;
        this.includeSpecial = includeSpecial;
    }

    public Password generate() {
        String charsToInclude = charsToInclude();
        StringBuilder stringBuilder = new StringBuilder();
        int length = this.length;
        while (length > 0) {
            int rand = (int) (Math.random() * charsToInclude.length());
            stringBuilder.append(charsToInclude.charAt(rand));
            length--;
        }
        return new Password(stringBuilder.toString());
    }

    private String charsToInclude() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.includeAlphaLower) {
            stringBuilder.append(ALPHA_LOWER);
        }
        if (this.includeAlphaUpper) {
            stringBuilder.append(ALPHA_UPPER);
        }
        if (this.includeNumbers) {
            stringBuilder.append(NUMBERS);
        }
        if (this.includeSpecial) {
            stringBuilder.append(SPECIAL_CHARS);
        }
        return stringBuilder.toString();
    }
}