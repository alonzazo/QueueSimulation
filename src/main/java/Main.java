//package java;

import Controller.Controller;
import Model.SistemaColas;
import Model.Distribucion;
import Model.DistribucionGamma;
import Model.DistribucionExponencial;
import Model.DistribucionUniforme;
import Model.DistribucionPoisson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("View/sample.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("View/sample.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Queues Simulation");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();

        Controller controller = loader.getController();
        controller.setData();



    }


    public static void main(String[] args) {

        launch(args);
    }


}
