package ee.ivkhkdev.Clothing_StoreJavaFX;
import ee.ivkhkdev.Clothing_StoreJavaFX.tools.FormLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClothingStoreApp extends Application {
	public static ConfigurableApplicationContext applicationContext;
	public static Stage primaryStage;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(ClothingStoreApp.class, args);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		FormLoader formLoader = applicationContext.getBean(FormLoader.class);
		formLoader.loadLoginForm();

	}
}
