
package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//*FXMLLoader의 LOAD() METHOD가 하는 일
			//sample.fxl.을 읽어서 각 테그에 해당하는 객체를 생성함
			//FXML 파일의 CONTROLLER속성을 참조해서 컨트롤러 객체를 생성함
			//컨트롤러 객체에 FXML화면요소 객체를 주입함.
			//FMXL 파일에서 최상위 컨테이너 객체를 리턴.
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root);//컨테이너를 화면에 부착
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);//화면을 윈도우창에 부착
			primaryStage.show();//윈도우창 보이기
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
