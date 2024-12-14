package Rental_Car_System.src;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CredentialStore {
    private static final String CUSTOMER_FILE = "customer_credentials.txt";
    private static final String ADMIN_FILE = "admin_credentials.txt";
    private static final String OWNER_FILE = "owner_credentials.txt";

    // Helper method to generate random strings
    private static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // Load credentials from file or generate and save them if file doesn't exist
    private static Map<String, String> loadOrCreateCredentials(String filePath, String prefix, int count, int passwordLength) {
        Map<String, String> credentials = new HashMap<>();
        File file = new File(filePath);

        if (file.exists()) {
            // Load credentials from file
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        credentials.put(parts[0], parts[1]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Generate and save credentials
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (int i = 1; i <= count; i++) {
                    String username = prefix + generateRandomString(5); // Example: customerAbc12
                    String password = generateRandomString(passwordLength); // Example: XyZ12345
                    credentials.put(username, password);
                    bw.write(username + ":" + password);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return credentials;
    }

    // Get Customer Credentials
    public static Map<String, String> getCustomerCredentials() {
        return loadOrCreateCredentials(CUSTOMER_FILE, "customer", 50, 10);
    }

    // Get Admin Credentials
    public static Map<String, String> getAdminCredentials() {
        return loadOrCreateCredentials(ADMIN_FILE, "admin", 5, 10);
    }

    // Get Car Owner Credentials
    public static Map<String, String> getCarOwnerCredentials() {
        return loadOrCreateCredentials(OWNER_FILE, "owner", 50, 10);
    }
}

