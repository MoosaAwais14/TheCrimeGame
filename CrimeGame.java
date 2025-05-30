
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CrimeGame {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean weaponPresent = false;
        String weapon = null;

        System.out.println("Welcome to the scene of the crime. You have been identified as a witness. Please provide the following information:\n");

        System.out.print("Name of witness (you): ");
        String witnessName = s.nextLine();

        System.out.print("Date of crime: ");
        String date = s.nextLine();

        System.out.print("Time of crime (approximate): ");
        String time = s.nextLine();

        System.out.print("Type of crime: ");
        String crimeType = s.nextLine();

        if (crimeType.equalsIgnoreCase("murder") || crimeType.equalsIgnoreCase("assault")) {
            System.out.print("Weapon present? (true/false): ");
            weaponPresent = s.nextBoolean();
            s.nextLine();

            if (weaponPresent) {
                System.out.print("Weapon type: ");
                weapon = s.nextLine();
            }
        }

        System.out.print("Number of victims: ");
        int numVictims = s.nextInt();
        s.nextLine();

        System.out.print("Number of casualties: ");
        int casualties = s.nextInt();
        s.nextLine();

        ArrayList<String> victimNames = new ArrayList<>();
        for (int i = 0; i < numVictims; i++) {
            System.out.print("Name of victim " + (i + 1) + ": ");
            victimNames.add(s.nextLine());
        }

        System.out.print("Location: ");
        String location = s.nextLine();

        System.out.print("Name of suspect: ");
        String suspectName = s.nextLine();

        System.out.print("Enter filename for the crime report: ");
        String filename = s.nextLine();

        try {
            File file = new File(filename);
            if (file.exists()) {
                throw new IOException("File already exists: " + filename);
            }

            PrintWriter w = new PrintWriter(new FileWriter(file));

            w.println("=========================================");
            w.println("             C R I M E   R E P O R T     ");
            w.println("=========================================");
            w.println();
            w.println("Date of Crime       : " + date);
            w.println("Time of Crime       : " + time);
            w.println("-----------------------------------------");
            w.println("Witness             : " + witnessName);
            w.println("Crime Type          : " + crimeType);
            w.println("Weapon Present      : " + (weaponPresent ? "Yes" : "No"));
            w.println("Weapon Type         : " + (weaponPresent ? weapon : "N/A"));
            w.println("-----------------------------------------");
            w.println("Number of Victims   : " + numVictims);
            for (int i = 0; i < victimNames.size(); i++) {
                w.printf("  Victim %-11d: %s%n", (i + 1), victimNames.get(i));
            }
            w.println("Number of Casualties: " + casualties);
            w.println("-----------------------------------------");
            w.println("Location            : " + location);
            w.println("Primary Suspect     : " + suspectName);
            w.println();
            w.println("=========================================");
            w.println("            E N D   O F   R E P O R T    ");
            w.println("=========================================");

            w.close(); // After writing to the file

            System.out.println("\n=== Printing Report ===\n");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Failed to read the report file.");
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println("Reason: " + e.getMessage());
        }

        s.close();
    }
}
