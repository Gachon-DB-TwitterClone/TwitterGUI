package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	
	public static void display(String title, String message) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setWidth(500);
		
		window.setHeight(200);
		window.setResizable(false);
		
		
		Label label = new Label();
		label.setText(message);
		label.setFont(new Font("Calibri", 24));
		label.setPadding(new Insets(0,0,10,0));
		Button closeButton = new Button("Ok");
		closeButton.setPrefHeight(33);
		closeButton.setPrefWidth(50);
		closeButton.setFont(new Font("Calibri bold", 17));
		closeButton.setOnAction(e -> window.close());
		closeButton.setStyle("-fx-background-color:#00acee; -fx-text-fill : white; "
				+ "-fx-background-radius:30px;");
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll( label, closeButton);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color:white;");
		
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
}
