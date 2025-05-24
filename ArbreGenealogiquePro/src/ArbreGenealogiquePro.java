import dao.UserDAO;
import dao.PersonDAO;
import model.User;
import model.Person;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ArbreGenealogiquePro {
    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        while (true) {
            System.out.println("\n=== Arbre Généalogique Pro++ ===");
            System.out.println("1. Register new user");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleRegistration();
                    break;
                case "2":
                    handleLogin();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private static void handleRegistration() {
        System.out.println("\n[Register New User]");
        try {
            System.out.print("SSN (or 99 for foreigner): ");
            String ssn = scanner.nextLine();

            System.out.print("First name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Birth date (YYYY-MM-DD): ");
            String birthDate = scanner.nextLine();

            System.out.print("Nationality: ");
            String nationality = scanner.nextLine();

            String password = firstName;
            String publicCode = UUID.randomUUID().toString();
            String privateCode = UUID.randomUUID().toString();

            User user = new User(ssn, firstName, lastName, birthDate, nationality, password, publicCode, privateCode);

            if (UserDAO.registerUser(user)) {
                System.out.println("✅ Registration successful!");
                System.out.println("Your public code: " + publicCode);
                System.out.println("Your private code: " + privateCode);
                System.out.println("Initial password is your first name. Please change it after login.");
            } else {
                System.out.println("❌ Registration failed. SSN or code may already exist.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void handleLogin() {
        System.out.println("\n[User Login]");
        try {
            System.out.print("Private code: ");
            String privateCode = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = UserDAO.loginUser(privateCode, password);

            if (user != null) {
                currentUser = user;
                System.out.println("✅ Login successful! Welcome, " + user.getFirstName() + ".");
                showUserMenu();
            } else {
                System.out.println("❌ Invalid private code or password.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Menu for logged-in user
    private static void showUserMenu() {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Add person to family tree");
            System.out.println("2. View tree (text mode)");
            System.out.println("3. Edit a person in the tree");
            System.out.println("4. Delete a person from the tree");
            System.out.println("5. Logout");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                    case "1":
                        addPersonToTree();
                        break;
                     case "2":
                        viewFamilyTree();
                        break;
                    case "3":
                        editPersonInTree();
                        break;
                    case "4":
                        deletePersonFromTree();
                        break;
                    case "5":
                        currentUser = null;
                System.out.println("Logged out successfully.");
                return;
            default:
                System.out.println("❌ Invalid choice. Try again.");
}
        }
    }

    
    // Allow the user to edit an existing person in their tree
private static void editPersonInTree() {
    System.out.println("\n[Edit Person in Tree]");

    List<Person> persons = PersonDAO.getPersonsByUser(currentUser.getId());

    if (persons.isEmpty()) {
        System.out.println("No persons to edit.");
        return;
    }

    // Display a numbered list of persons
    for (int i = 0; i < persons.size(); i++) {
        System.out.println((i + 1) + ". " + persons.get(i).getRelation() + ": " + persons.get(i).getName());
    }

    System.out.print("Select person number to edit: ");
    int index = Integer.parseInt(scanner.nextLine()) - 1;

    if (index < 0 || index >= persons.size()) {
        System.out.println("❌ Invalid selection.");
        return;
    }

    Person person = persons.get(index);

    // Prompt for new values
    System.out.print("New name (leave blank to keep \"" + person.getName() + "\"): ");
    String newName = scanner.nextLine();
    if (!newName.isEmpty()) person.setName(newName);

    System.out.print("New birth date (YYYY-MM-DD, leave blank to keep \"" + person.getBirthDate() + "\"): ");
    String newDate = scanner.nextLine();
    if (!newDate.isEmpty()) person.setBirthDate(newDate);

    System.out.print("New relation (leave blank to keep \"" + person.getRelation() + "\"): ");
    String newRelation = scanner.nextLine();
    if (!newRelation.isEmpty()) person.setRelation(newRelation);

    System.out.print("New visibility (public/private/protected, leave blank to keep \"" + person.getVisibility() + "\"): ");
    String newVisibility = scanner.nextLine();
    if (!newVisibility.isEmpty()) person.setVisibility(newVisibility);

    boolean updated = PersonDAO.updatePerson(person);
    if (updated) {
        System.out.println("✅ Person updated successfully.");
    } else {
        System.out.println("❌ Failed to update person.");
    }
}


// Allow the user to delete a person from their tree
      private static void deletePersonFromTree() {
    System.out.println("\n[Delete Person from Tree]");

    List<Person> persons = PersonDAO.getPersonsByUser(currentUser.getId());

    if (persons.isEmpty()) {
        System.out.println("No persons to delete.");
        return;
    }

    // Display a numbered list of persons
    for (int i = 0; i < persons.size(); i++) {
        System.out.println((i + 1) + ". " + persons.get(i).getRelation() + ": " + persons.get(i).getName());
    }

    System.out.print("Select person number to delete: ");
    int index = Integer.parseInt(scanner.nextLine()) - 1;

    if (index < 0 || index >= persons.size()) {
        System.out.println("❌ Invalid selection.");
        return;
    }

    Person person = persons.get(index);

    // Confirm deletion if the person is linked to a registered user
    if (person.getRegisteredUserId() != null) {
        System.out.println("⚠️ This person is linked to a registered user. Deletion requires their approval (not implemented).");
        return;
    }

    boolean deleted = PersonDAO.deletePerson(person.getId());
    if (deleted) {
        System.out.println("✅ Person deleted successfully.");
    } else {
        System.out.println("❌ Failed to delete person.");
    }
}




    // Add a person to the current user's tree
    private static void addPersonToTree() {
        System.out.println("\n[Add Person to Family Tree]");
        try {
            System.out.print("Full name: ");
            String name = scanner.nextLine();

            System.out.print("Birth date (YYYY-MM-DD): ");
            String birthDate = scanner.nextLine();

            System.out.print("Relation (e.g. father, mother, brother): ");
            String relation = scanner.nextLine();

            System.out.print("Is this person a registered user? (yes/no): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            Integer registeredUserId = null;

            if (answer.equals("yes")) {
                System.out.print("Enter the SSN of the registered user: ");
                String ssn = scanner.nextLine();
                // You may implement lookup of user by SSN later
                System.out.println("🔔 For now, linking to registered user is not implemented.");
            }

            System.out.print("Visibility (public/private/protected): ");
            String visibility = scanner.nextLine().trim().toLowerCase();

            Person person = new Person(currentUser.getId(), name, birthDate, relation, registeredUserId, visibility);
            boolean success = PersonDAO.addPerson(person);

            if (success) {
                System.out.println("✅ Person added to your family tree.");
            } else {
                System.out.println("❌ Failed to add person.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Display the family tree (text mode)
    private static void viewFamilyTree() {
        System.out.println("\n[Your Family Tree]");
        List<Person> persons = PersonDAO.getPersonsByUser(currentUser.getId());

        if (persons.isEmpty()) {
            System.out.println("No persons in your tree yet.");
            return;
        }

        for (Person p : persons) {
            String reg = (p.getRegisteredUserId() != null) ? "✅ Registered" : "❌ Not registered";
            System.out.println("- " + p.getRelation() + ": " + p.getName() +
                    " | DOB: " + p.getBirthDate() +
                    " | Visibility: " + p.getVisibility() +
                    " | " + reg);
        }
    }
}