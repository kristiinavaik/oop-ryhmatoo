import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Kasutajaliides extends Application {

	public static final String PEALKIRI = "Õpime eesti keelt";
	
	private static int LAIUS = 500;
	private static int KORGUS = 300;
	
	private static BorderPane alusPaan = new BorderPane();
    private static GridPane grid;
    private static MenuBar menuBar;  
    
    private static Text ekraaniPealkiri = new Text();
    private static Text kirjeldus = new Text();
    
    static Text valik1Text = new Text();
    static Text valik2Text = new Text();
    static Text tulemusText = new Text();
    
    static TextField textField = new TextField();
    
    static Button valik1Nupp = new Button();
    static Button valik2Nupp = new Button();
    static Button stoppNupp = new Button("Aitab!");
	
	public static void main(String[] args) {
		launch(args);
	}

	private static Region getTyhiRuum() {
		Region tyhiRuum = new Region();
		tyhiRuum.setMinHeight(5);
		tyhiRuum.setMinWidth(0);
        tyhiRuum.setPrefSize(0, 5);
        return tyhiRuum;
	}
	
	private static void initGrid(String pealkiri, String kirjeldusText) {
		grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setVgap(10);
	    
	    ekraaniPealkiri.setText(pealkiri);
	    ekraaniPealkiri.setFont(Font.font(null, FontWeight.BOLD, 16));
	    grid.add(ekraaniPealkiri, 0, 0);
	    
	    if (kirjeldusText != null) {
	    	kirjeldus.setText(kirjeldusText);
		    grid.add(kirjeldus, 0, 1);
	    }
	}
	
	@Override
	public void start(Stage pealava) throws Exception {
        menuBar = koostaMenuu();
        manguValik();
        
        alusPaan.setTop(menuBar);
        alusPaan.setCenter(grid);
	    
        tulemusText.setFont(Font.font(null, FontPosture.ITALIC, 14));
        tulemusText.setWrappingWidth(LAIUS - 50);
        valik1Text.setWrappingWidth(LAIUS - 50);
        valik2Text.setWrappingWidth(LAIUS - 50);
        textField.setMaxWidth(LAIUS - 50);
        
		Scene stseen = new Scene(alusPaan, LAIUS, KORGUS);
		pealava.setScene(stseen);
		pealava.setTitle(PEALKIRI);
		pealava.setMinHeight(KORGUS);
		pealava.setMinWidth(LAIUS);
		pealava.show();
	}

	public static void manguValik() {
		initGrid("Palun valige harjutus", null);
		
		VBox vbNupud = new VBox();
	    vbNupud.setSpacing(10);
	    
		Button oigekirjaNupp = new Button("Õigekirjaharjutus");
	    Button kirjavahemarkideNupp = new Button("Kirjavahemärkide harjutus");
	    
	    oigekirjaNupp.setMinSize(200, 30);
	    kirjavahemarkideNupp.setMinSize(200, 30);

	    oigekirjaNupp.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	oigekirjaHarjutus();
            }
        });
	    kirjavahemarkideNupp.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                kirjavahemarkideHarjutus();
            }
         });

	    vbNupud.getChildren().addAll(oigekirjaNupp, kirjavahemarkideNupp);
	    
	    grid.add(vbNupud, 0, 1);
	}
	
	public static void naitaTulemust(String harjutuseNimi) {
		initGrid(harjutuseNimi + " oli teie tulemuseks:", null);

		VBox tulemused = new VBox();
	    tulemused.setSpacing(10);
	    
	    Text oigedText = new Text("Õigeid: " + Mangud.oiged);
	    Text valedText = new Text("Valed: " + Mangud.valed);
	    
	    oigedText.setFont(Font.font(14));
	    valedText.setFont(Font.font(14));
	    tulemused.getChildren().addAll(oigedText, valedText);
	    
		grid.add(tulemused, 0, 1);
		alusPaan.setCenter(grid);
	}
	
	public static MenuBar koostaMenuu() {
		MenuBar menuBar = new MenuBar();
        Menu menuHarjutused = new Menu("Mäng");
        menuBar.getMenus().add(menuHarjutused);
        
        MenuItem itemValju = new MenuItem("Välju");
        MenuItem itemOigekirjaharjutus = new MenuItem("Õigekirjaharjutus");
        MenuItem itemKirjavahemarkideharjutus = new MenuItem("Kirjavahemärkide harjutus");
        
        itemOigekirjaharjutus.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                oigekirjaHarjutus();
            }
        });

        itemKirjavahemarkideharjutus.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                kirjavahemarkideHarjutus();
            }
        });
        
        itemValju.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        menuHarjutused.getItems().addAll(itemOigekirjaharjutus, itemKirjavahemarkideharjutus, new SeparatorMenuItem(), itemValju);
        return menuBar;
	}
	
	private static void oigekirjaHarjutus() {
		tulemusText.setText("");
		
		initGrid("Õigekirjaharjutus", "Valige õige variant");

		VBox valikud = new VBox();
	    valikud.setSpacing(5);
	    
	    valik1Text.setFont(Font.font(14));
	    valik2Text.setFont(Font.font(14));
	    valikud.getChildren().addAll(getTyhiRuum(), tulemusText, getTyhiRuum(), valik1Text, valik2Text);
	    
		valik1Nupp.setMinSize(50, 20);
		valik1Nupp.setText("Õige on a)");
		
		valik2Nupp.setMinSize(50, 20);
		valik2Nupp.setText("Õige on b)");
		
		stoppNupp.setMinSize(50, 20);
		stoppNupp.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				naitaTulemust("Õigekirjaharjutuses");
			}
		});
	    HBox nupud = new HBox(20, valik1Nupp, valik2Nupp, stoppNupp);
	    
		grid.add(valikud, 0, 2);
		grid.add(nupud, 0, 3);
		alusPaan.setCenter(grid);
		
	    Mangud.mangiOigekirjaMangu(null, false);
	}
    
	private static void kirjavahemarkideHarjutus() {
		tulemusText.setText("");
		
		initGrid("Kirjavahemärkide harjutus", "Lisage puuduvad kirjavahemärgid");

		VBox tekstid = new VBox();
		tekstid.setSpacing(5);
		
	    valik1Text.setFont(Font.font(14));
	    textField.setFont(Font.font(14));
	    
	    tekstid.getChildren().addAll(getTyhiRuum(), tulemusText, getTyhiRuum(), valik1Text, textField);
	    
	    valik1Nupp.setMinSize(50, 20);
	    valik1Nupp.setText("Kontrolli");
	    stoppNupp.setMinSize(50, 20);
		stoppNupp.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				naitaTulemust("Kirjavahemärkide harjutuses");
			}
		});
	    HBox nupud = new HBox(20, valik1Nupp, stoppNupp);
	    
		grid.add(tekstid, 0, 2);
		grid.add(nupud, 0, 3);
		alusPaan.setCenter(grid);
		
	    Mangud.mangiKirjavahemarkideMangu(null, false);
	}
}
