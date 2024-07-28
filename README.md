# Task-Manager-Application
The Task Manager Application is a Java-based project that helps users manage their tasks efficiently. The application provides features to add, update, delete, and filter tasks based on various criteria.
## Features
- **Add Task**: Add a new task with details such as title, description, due date, and priority.
- **Update Task**: Modify the details of an existing task.
- **Delete Task**: Remove a task from the list.
- **Filter Tasks**: Filter tasks based on priority.

## Technologies Used
- **Java**: The core programming language used.
- **JavaFX**: Used for building the graphical user interface.
- **SQLite**: Used for database management.
- ##.vscode/: This folder contains VS Code-specific configuration files.

launch.json: Contains launch configurations for debugging your application in VS Code.
settings.json: Contains project-specific settings for VS Code.

## Setup Instructions
### Prerequisites
- Java Development Kit (JDK) 17 or higher
- SQLite JDBC driver

### Steps to Run
1. **Clone the repository**:
    ```sh
    git clone https://github.com/your-username/task-manager-application.git
    cd task-manager-application
    ```
2. **Ensure dependencies are in place**:
    - The `sqlite-jdbc` library should be included in your project's `lib` folder or as a dependency in your build tool (like Maven or Gradle).

3. **Compile and run the application**:
    - Use your preferred IDE (such as IntelliJ IDEA, Eclipse, or VS Code) to open the project.
    - Compile the project.
    - Run the `TaskManagerApp` class.

### Usage
- Launch the application.
- Use the provided form to add tasks.
- View the tasks in the list.
- Select tasks to update or delete them.
- Filter tasks by priority.

## Database Configuration
The application uses an SQLite database (`tasks.db`) to store task information. Ensure that the `tasks.db` file is in the correct location or update the database connection settings in the `Database` class.
