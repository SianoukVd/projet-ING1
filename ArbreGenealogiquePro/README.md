
# Arbre Généalogique Pro++
*A Java-based Desktop Application for Family Tree Management*

## 📌 Overview

Arbre Généalogique Pro++ is a user-friendly family tree management desktop system built using Java Swing and SQLite. It enables users to register, log in with secure codes, manage family members with relational visibility, add multimedia files (photos, videos, documents) to the tree, and view structured family data with editing capabilities.

## 🛠️ Features Breakdown

### 1. **User Registration & Authentication**
- Custom account creation with:
  - First name, last name, SSN, birth date, nationality.
  - User-chosen private code, public code, and password.
- Secure login using private code and password.
- Real-time error messages for duplicate or incorrect data.

### 2. **User Dashboard (Main Menu)**
- Large buttons for all main functionalities.
- Options include:
  - Add family media.
  - View family media.
  - Add person to tree.
  - View family tree (text mode).
  - Logout.

### 3. **Add Person to Family Tree**
- Form to enter:
  - Full name, birth date.
  - Relation from a dropdown (Father, Mother, Brother, etc.).
  - Visibility (Private, Protected, Public).
- Option to link person to an already registered user via SSN.

### 4. **View Family Tree (Text Mode)**
- Scrollable text area listing:
  - Member number, relation, full name, DOB, visibility, registration status.
- Includes:
  - Filter by relation.
  - Edit or delete existing persons.
  - Restriction: Cannot delete registered persons.
  - Edit dialog allows changing all fields.

### 5. **Add Media Files**
- Upload files and attach a description.
- Set visibility level.
- Show selected file name.
- Display confirmation of success/failure.
  
### 6. **Data Management & Storage**
- All data is stored in an SQLite database.
- Tables: users, persons, media.
- Each user's data is completely isolated and secure.

## 🧭 How to Run the Project

### Requirements:
- Java JDK 8 or later.
- NetBeans (or other IDE with GUI Builder support).
- SQLite JDBC driver (already included if configured correctly).

### Steps:
1. Open the project in NetBeans.
2. Build the project.
3. Run `LoginFrame.java`.
4. Register a new user if none exist.
5. Explore the dashboard features.

## 📂 Application Windows Overview

| Window / Frame             | Description |
|---------------------------|-------------|
| **LoginFrame**            | Enter Private Code & Password. Link to RegisterFrame. |
| **RegisterFrame**         | Form with validation. User enters SSN, codes, name, birth date, password. |
| **UserDashboard**         | Main control panel with navigation buttons. |
| **AddPersonFrame**        | Add a new family member with relation and visibility. |
| **ViewTreeFrameOld**      | View/edit/delete family members in a textual format. |
| **AddMediaFrame**         | Upload files with description and visibility level. |

## 🧰 Technologies Used

- **Java Swing** for GUI
- **JDBC** for database connection
- **SQLite** as the database
- **NetBeans IDE** (GUI Designer support)
