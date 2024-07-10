package be.hasan.libraSphere.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator class for ISBNs.
 * This class validates both ISBN-10 and ISBN-13 formats.
 */
public class IsbnValidator implements ConstraintValidator<ISBN, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Check if the ISBN is null or blank
        if (value == null || value.isBlank()) {
            return false;
        }

        // Remove all non-numeric characters except 'X' (valid for ISBN-10 check digits)
        var isbn = value.replaceAll("[^0-9X]", "");

        // Validate based on the length of the ISBN
        if (isbn.length() == 10) {
            return isValidISBN10(isbn); // Validate as ISBN-10
        } else if (isbn.length() == 13) {
            return isValidISBN13(isbn); // Validate as ISBN-13
        } else {
            return false; // Invalid length for ISBN
        }
    }

    /**
     * Validates the given ISBN-10 string.
     *
     * @param isbn the ISBN-10 string to validate
     * @return true if the ISBN-10 is valid, false otherwise
     */
    private boolean isValidISBN10(String isbn) {
        int sum = 0;
        // Calculate the sum for the first 9 digits
        for (int i = 0; i < 9; i++) {
            int digit = isbn.charAt(i) - '0'; // Convert character to digit
            // Check if the character is a valid digit
            if (digit < 0 || digit > 9) {
                return false;
            }
            sum += (10 - i) * digit; // Multiply the digit by its weight and add to the sum
        }

        // Check the last character which can be 'X' or a digit
        char lastChar = isbn.charAt(9);
        if (lastChar == 'X') {
            sum += 10; // 'X' represents 10
        } else {
            int lastDigit = lastChar - '0'; // Convert character to digit
            // Check if the character is a valid digit
            if (lastDigit < 0 || lastDigit > 9) {
                return false;
            }
            sum += lastDigit; // Add the last digit to the sum
        }

        // ISBN-10 is valid if the sum is divisible by 11
        return (sum % 11 == 0);
    }

    /**
     * Validates the given ISBN-13 string.
     *
     * @param isbn the ISBN-13 string to validate
     * @return true if the ISBN-13 is valid, false otherwise
     */
    private boolean isValidISBN13(String isbn) {
        int sum = 0;
        // Calculate the sum for the first 12 digits
        for (int i = 0; i < 12; i++) {
            int digit = isbn.charAt(i) - '0'; // Convert character to digit
            // Check if the character is a valid digit
            if (digit < 0 || digit > 9) {
                return false;
            }
            sum += (i % 2 == 0) ? digit : digit * 3; // Multiply odd-position digits by 1 and even-position digits by 3
        }

        // Check the last character (check digit)
        int checkDigit = isbn.charAt(12) - '0'; // Convert character to digit
        // Check if the character is a valid digit
        if (checkDigit < 0 || checkDigit > 9) {
            return false;
        }

        // Calculate the expected check digit
        int calculatedCheckDigit = 10 - (sum % 10);
        if (calculatedCheckDigit == 10) {
            calculatedCheckDigit = 0; // If the calculated check digit is 10, it should be 0
        }

        // ISBN-13 is valid if the check digit matches the calculated check digit
        return (calculatedCheckDigit == checkDigit);
    }
}
