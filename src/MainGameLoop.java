import javafx.application.Application;
import javafx.stage.Stage;

public class MainGameLoop extends Application {

    public void main(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Display.main(primaryStage);
        System.out.println("AAA");
        DominosBoard.main();
        //System.out.println(toString(DominosBoard.dominosSet));
        System.out.println("BBB");
    }

    @Override
    public String toString() {
        // Creates StringBuilder
        StringBuilder holder = new StringBuilder();

        // Adds strings to StringBuilder
        for (int i = 0; i < DominosBoard.dominosSet.size(); i++) {
            holder.append("[").append(DominosBoard.dominosSet.get(i).left);
            holder.append(",").append(DominosBoard.dominosSet.get(i).right).append("]");
        }
        return holder.toString();
    }
}