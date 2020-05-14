package controller;

import javafx.stage.Stage;

public class StageChanger {
    public static void switchRegister(Stage window, Registration app) {
        try {
            app.start(window);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchLogin(Stage window, Loging app) {
        try {
            app.start(window);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchDashboard(Stage window, dashboardStage app) {
        try {
            app.start(window);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
