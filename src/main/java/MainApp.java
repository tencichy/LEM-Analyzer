import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane root;

    private RootController rootController;

    private static boolean DEBUG = false;
    private static boolean RAW = false;
    private static String PATH = null;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LEM - Analyzer");
        this.primaryStage.setResizable(false);
        this.primaryStage.setOnCloseRequest(event -> System.exit(0));

        RootController.stage = this.primaryStage;

        MainController.DEBUG = DEBUG;
        RootController.DEBUG = DEBUG;
        RootController.RAW = RAW;

        initRootLayout();

        initMainLayout();

    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    DEBUG = true;
                    break;
                case "-f":
                    if (args.length > i + 1) {
                        PATH = args[i + 1];
                    }
                    break;
                case "-r":
                    RAW = true;
                    break;
            }
        }
        Application.launch(args);
    }

    private void initMainLayout(){
        try {
            FXMLLoader loaderMain = new FXMLLoader();
            loaderMain.setLocation(MainApp.class.getResource("mainLayout.fxml"));
            AnchorPane anchorPane = loaderMain.load();
            RootController.mainController = loaderMain.getController();
            if(PATH != null) {
                rootController.handleOpen(PATH);
            }
            root.setCenter(anchorPane);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
            new TextAlertGenerator(e, Alert.AlertType.ERROR);
        }
    }

    private void initRootLayout(){
        try {
            FXMLLoader loaderMain = new FXMLLoader();
            loaderMain.setLocation(MainApp.class.getResource("rootLayout.fxml"));
            BorderPane borderPane = loaderMain.load();
            rootController = loaderMain.getController();
            root = borderPane;
            Scene scene = new Scene(borderPane);
            primaryStage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
            new TextAlertGenerator(e, Alert.AlertType.ERROR);
        }
    }
}