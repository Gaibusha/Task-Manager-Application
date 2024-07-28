import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskManagerApp extends Application {
    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Task Manager");

        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();

        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField();

        Label dueDateLabel = new Label("Due Date:");
        TextField dueDateField = new TextField();

        Label priorityLabel = new Label("Priority:");
        TextField priorityField = new TextField();

        Button addButton = new Button("Add Task");
        Button updateButton = new Button("Update Task");
        Button deleteButton = new Button("Delete Task");
        Button filterButton = new Button("Filter");

        addButton.setOnAction(e -> {
            try {
                int priority = Integer.parseInt(priorityField.getText());

                Task task = new Task();
                task.setTitle(titleField.getText());
                task.setDescription(descriptionField.getText());
                task.setDueDate(dueDateField.getText());
                task.setPriority(priority);

                TaskDAO.addTask(task);
                tasks.add(task);

                titleField.clear();
                descriptionField.clear();
                dueDateField.clear();
                priorityField.clear();
                System.out.println("Task added: " + task.getTitle());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid priority: " + priorityField.getText());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Priority must be an integer.");
                alert.showAndWait();
            }
        });

        ListView<Task> taskListView = new ListView<>(tasks);
        taskListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null || task.getTitle() == null) {
                    setText(null);
                } else {
                    setText(task.getTitle());
                }
            }
        });

        taskListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                titleField.setText(newValue.getTitle());
                descriptionField.setText(newValue.getDescription());
                dueDateField.setText(newValue.getDueDate());
                priorityField.setText(String.valueOf(newValue.getPriority()));
            }
        });

        updateButton.setOnAction(e -> {
            Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                try {
                    int priority = Integer.parseInt(priorityField.getText());

                    selectedTask.setTitle(titleField.getText());
                    selectedTask.setDescription(descriptionField.getText());
                    selectedTask.setDueDate(dueDateField.getText());
                    selectedTask.setPriority(priority);

                    TaskDAO.updateTask(selectedTask);
                    taskListView.refresh();
                    System.out.println("Task updated: " + selectedTask.getTitle());
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid priority: " + priorityField.getText());
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Priority must be an integer.");
                    alert.showAndWait();
                }
            }
        });

        deleteButton.setOnAction(e -> {
            Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                TaskDAO.deleteTask(selectedTask.getId());
                tasks.remove(selectedTask);
                System.out.println("Task deleted: " + selectedTask.getId());
            }
        });

        filterButton.setOnAction(e -> {
            try {
                String priorityText = priorityField.getText();
                if (!priorityText.isEmpty()) {
                    int priority = Integer.parseInt(priorityText);
                    tasks.setAll(TaskDAO.getAllTasks().stream()
                            .filter(task -> task.getPriority() == priority)
                            .toList());
                    System.out.println("Tasks filtered by priority: " + priority);
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid priority: " + priorityField.getText());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Priority must be an integer.");
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                new HBox(10, titleLabel, titleField),
                new HBox(10, descriptionLabel, descriptionField),
                new HBox(10, dueDateLabel, dueDateField),
                new HBox(10, priorityLabel, priorityField),
                new HBox(10, addButton, updateButton, deleteButton, filterButton),
                taskListView);

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        tasks.addAll(TaskDAO.getAllTasks());
        System.out.println("Tasks loaded: " + tasks.size());
    }

    public static void main(String[] args) {
        DatabaseInitializer.initialize();
        launch(args);
    }
}
