package main.java;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ComprehensiveEmailValidator {
    private static final String EMAIL_REGEX =
        "^(?!.*\\.\\.)(?!.*\\.$)(?!^\\.)[a-zA-Z0-9]+([._-][a-zA-Z0-9]+)*" +  
        "@" +
        "(?!-)[A-Za-z0-9]+([.-][A-Za-z0-9]+)*" +  
        "\\.[A-Za-z]{2,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static String getInvalidReason(String email) {
        if (email == null || email.isEmpty()) return "Email cannot be empty.";
        if (!email.contains("@")) return "Email must contain '@' symbol.";
        if (!email.contains(".")) return "Email must contain a domain (e.g., '.com', '.org').";
        if (email.startsWith(".") || email.startsWith("-") || email.startsWith("_")) return "Email cannot start with '.', '-', or '_'.";
        if (email.endsWith(".")) return "Email cannot end with a '.'";
        if (email.matches(".*\\.\\..*")) return "Email cannot contain consecutive dots ('..').";
        if (email.contains("@-") || email.contains("@.")) return "Domain cannot start with '-' or '.'";
        if (email.matches(".*@.*-\\..*")) return "Domain cannot have a hyphen immediately before the dot.";
        if (!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) return "Email format is incorrect.";
        return "Unknown reason.";
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nEnter an email to validate (or type 'exit' to quit):");
                String email = scanner.nextLine().trim();

                if (email.equalsIgnoreCase("exit") || email.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                }

                if (isValidEmail(email)) {
                    System.out.println(email + " is a VALID email address.");
                } else {
                    System.out.println(email + " is NOT a valid email address. Reason: " + getInvalidReason(email));
                }
            }
        }
    }
}
