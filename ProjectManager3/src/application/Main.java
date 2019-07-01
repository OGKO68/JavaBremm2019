package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage; 
 
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	String TITLENAME = new String("Project Manager");
    	
    	primaryStage.setTitle(TITLENAME);
    	
    	Text staticText_top = new Text(TITLENAME);
    	staticText_top.setTextAlignment(TextAlignment.CENTER);
    	Text staticText_bottom = new Text(TITLENAME);
    	
        /*TextField txt = new TextField("Hello World");
        txt.setMaxWidth(Double.MAX_VALUE);
        txt.setOnAction(new EventHandler<ActionEvent>() { 
        	@Override
            public void handle(ActionEvent event) {
            System.out.println(txt.getText());
            }
        });*/
    	
    	CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Devices");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");

        BarChart barChart = new BarChart(xAxis, yAxis);
        
        HBox vbox = new HBox(barChart);
        
        barChart.setMaxWidth(Double.MAX_VALUE / 2);
        vbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        BorderPane borderPane = new BorderPane();
        
        borderPane.setTop(staticText_top);
        BorderPane.setAlignment(staticText_top, Pos.CENTER);
        
        borderPane.setCenter(vbox);
        
        Button buttonDepartment = new Button();
        buttonDepartment.setText("Department");
        Button buttonProject = new Button();
        
        HBox hBoxBottom = new HBox(buttonDepartment, buttonProject);
        
        borderPane.setBottom(hBoxBottom);
        BorderPane.setAlignment(staticText_bottom, Pos.CENTER);
       
        Scene scene = new Scene(borderPane, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        //https://o7planning.org/de/10629/anleitung-javafx-borderpane-layout
        //https://www.tutorialspoint.com/javafx/javafx_text.htm
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html
        //http://tutorials.jenkov.com/javafx/barchart.html
    }
}