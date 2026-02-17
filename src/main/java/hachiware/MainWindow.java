package hachiware;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Hachiware hachiware;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/chiikawa.png"));
    private Image hachiwareImage = new Image(this.getClass().getResourceAsStream("/images/hachiware.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
                dialogContainer.getChildren().addAll(
                DialogBox.getHachiwareDialog(Ui.printWelcomeMessage(), hachiwareImage)
        );
        userInput.clear();
    }

    /** Injects the Hachiware instance */
    public void setHachiware(Hachiware h) {
        hachiware = h;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Hachiware's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // handle bye command
        if (input.toLowerCase().equals("bye")) {
            System.exit(0);
        }

        String response = hachiware.takeSingleCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHachiwareDialog(response, hachiwareImage)
        );
        userInput.clear();
    }
}
