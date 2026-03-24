package com.gestionbaux;

import javafx.application.Application;
import javafx.stage.Stage;
import com.gestionbaux.util.NavigationUtil;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        NavigationUtil.setStage(stage);
        NavigationUtil.naviguerVers("accueil");
        stage.setTitle("LocaGest");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
