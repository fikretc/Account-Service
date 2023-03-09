package account.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface PasswordParams {
    public static final String messageMinPasswordLength = "Password length must be 12 chars minimum!";
    public static final String messageMinPasswordLengthOriginal = "The password length must be at least 12 chars!";
    public static final String messageBreachedPassword = "The password is in the hacker's database!";
    public static final String messageSamePassword = "The passwords must be different!";
    public static final String messagePasswordChanged = "The password has been updated successfully";
    public static final String messagePasswordMustChange = "Password must be changed!";

    public static final int minPasswordLength = 12;

    public static final Set<String> breachedPasswords = new HashSet<>(Arrays.asList("PasswordForJanuary", "PasswordForFebruary", "PasswordForMarch", "PasswordForApril",
            "PasswordForMay", "PasswordForJune", "PasswordForJuly", "PasswordForAugust",
            "PasswordForSeptember", "PasswordForOctober", "PasswordForNovember", "PasswordForDecember"));

}
