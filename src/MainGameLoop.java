import javafx.application.Application;
import javafx.stage.Stage;

public class MainGameLoop extends Application {

    public static void main(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        //Display.main(primaryStage);
        System.out.println("Start");

        Boneyard.main();
        //System.out.println(Boneyard.boneyard);

        Human human = new Human();
        //System.out.println(human);

        Computer computer = new Computer();
        //System.out.println(computer);

        //System.out.println(Boneyard.boneyard);
        System.out.println("Stop");
    }
}