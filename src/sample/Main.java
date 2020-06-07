package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.geometry.Pos;

public class Main extends Application {

    private FlowPane flow;
    Stage window;
    Scene scene1, scene2;
    String [][] recipes = {
            {
                "Yogurt Parfait", "Yogurt", "Granola", "Blackberries"
            },
            {
                "Pancakes", "Milk", "Flour", "Sugar", "Egg", "Butter"
            }
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        GridPane grid = new GridPane();
        grid.setId("pane");
        BackgroundImage image = new BackgroundImage(new Image("background.jpg", 1250, 1500, true, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        grid.setBackground(new Background(image));
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(200);
        grid.setHgap(5);

        TextField ingredients = new TextField();
        ingredients.setFont(Font.font("Ubuntu", 20));
        ingredients.setPromptText("Enter your ingredients:");
        ingredients.setPrefWidth(750);
        ingredients.setPrefHeight(100);
        ingredients.getText();
        GridPane.setConstraints(ingredients, 0, 0);
        grid.getChildren().add(ingredients);

        TextField preferences = new TextField();
        preferences.setFont(Font.font("Ubuntu", 20));
        preferences.setPromptText("Enter your preferences:");
        preferences.setPrefWidth(750);
        preferences.setPrefHeight(100);
        preferences.getText();
        GridPane.setConstraints(preferences, 0, 1);
        grid.getChildren().add(preferences);

        Button submit = new Button("Submit");
        submit.setFont(Font.font("Ubuntu", 20));
        submit.setStyle("-fx-background-color: aquamarine");
        submit.setPrefHeight(100);
        submit.setPrefWidth(250);
        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);

        Button clear = new Button("Clear");
        clear.setFont(Font.font("Ubuntu", 20));
        clear.setStyle("-fx-background-color: aquamarine");
        clear.setPrefWidth(250);
        clear.setPrefHeight(100);
        GridPane.setConstraints(clear, 1, 1);
        grid.getChildren().add(clear);

        Label label = new Label();
        label.setFont(Font.font("Ubuntu", 50));
        label.setTextFill(Color.AQUAMARINE);
        GridPane.setConstraints(label, 0 ,2);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String [] items = ingredients.getText().split(", ");
                int count = 0;
                int closeMatch = 0;
                String found = "";
                if (ingredients.getText() != null && !ingredients.getText().isEmpty() && items.length >= 1) {
                    for (int i = 0; i < recipes.length; i++) {
                        for (int j = 1; j < recipes[i].length; j++) {
                            for (int k = 0; k < items.length; k++) {
                                if (recipes[i][j].equals(items[k])) {
                                    count++;
                                }
                            }
                        }
                        if (count > closeMatch) {
                            found = recipes[i][0];
                            closeMatch = count;
                        }
                        count = 0;
                    }
                    if (closeMatch >= items.length - 1) {
                        label.setText("Here are your suggested recipes:\n" + found);
                    } else {
                        label.setText("No close matches were found :(");
                    }
                } else {
                    label.setText("Please enter more ingredients (minimum 5)");
                }
            }
        });

        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ingredients.clear();
                preferences.clear();
                label.setText(null);
            }
        });

        label.setAlignment(Pos.CENTER);
        scene1 = new Scene(grid, window.getMaxWidth(), window.getMaxHeight());

        window.setScene(scene1);
        window.setTitle("App");
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
