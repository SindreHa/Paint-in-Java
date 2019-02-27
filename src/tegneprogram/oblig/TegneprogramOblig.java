package tegneprogram.oblig;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/*
* Utviklet av: Sindre Haavaldsen
* Dato: Oktober 2018
* Studentnummer: 888591
 */

public class TegneprogramOblig extends Application implements IGraphicalOperations{
    //Oppretter figurobjekter og andre variabler på klassenivå
    private IGraphicalObject graphicalObject;
    private double startX, startY;
    private String selectedObjectType = "nothing";
    private Pane center;
    private HBox infoPane = new HBox(10);
    ToggleButton changeColor = new ToggleButton("Change Color");
    ToggleButton moveFrwd = new ToggleButton("Move Forward");
    ToggleButton moveBckw = new ToggleButton("Move Backwards");
    ToggleButton getInfo = new ToggleButton("Get Info");
    ColorPicker cpFill = new ColorPicker(Color.TRANSPARENT);
    ColorPicker cpLine = new ColorPicker(Color.BLACK);
    TextArea textA = new TextArea();
    Label fillL = new Label();
    Label strokeL = new Label();
    Label nameL = new Label();
    Label omkretsL = new Label();
    Label arealL = new Label();// = new Label("Fyllfarge");

    public void start(Stage primaryStage) {
        //Oppretter selve tegnefeltet som en Pane
        center = new Pane();
        //Knapper
        ToggleButton linebtn = new ToggleButton("Line");
        ToggleButton rectbtn = new ToggleButton("Rectangle");
        ToggleButton circlebtn = new ToggleButton("Circle");
        ToggleButton polygon = new ToggleButton("Polygon");
        ToggleButton textbtn = new ToggleButton("Text");
        //Lager array for knapper og setter egenskaper for alle i en for loop
        ToggleButton[] knappArr = {getInfo, changeColor, moveFrwd, moveBckw, linebtn, rectbtn,
                circlebtn, polygon, textbtn};
        //Oppretter en ToggleGroup som gjør at kun en og en knapp kan være aktivert
        ToggleGroup tgGroup = new ToggleGroup();

        //For hver togglebutton i knappArr sett disse egenskapene
        for (ToggleButton knapp : knappArr) {
            knapp.setToggleGroup(tgGroup);//Legger knappene inn i ToggleGroup
            knapp.setMinWidth(100);//Setter bredde på knapper
            knapp.setCursor(Cursor.HAND);
            knapp.setOnAction((ActionEvent e) ->
            {
                // Set which object type is selected
                String text = ((ToggleButton) e.getSource()).getText();
                if (knapp.isSelected()) {
                    selectedObjectType = text;
                } else {
                    selectedObjectType = "nothing"; }
            });
        }

        //Setter slik at getInfo viser/skjuler info panelet
        getInfo.setOnAction((ActionEvent v) ->
        {
            if (getInfo.isSelected()) {
                selectedObjectType = "nothing";
                infoPane.setVisible(true);
            } else {
                infoPane.setVisible(false); }
        });

        //Label stil
        Label line_color = new Label("Line Color");
        Label fill_color = new Label("Fill Color");
        Label[] labelArr = {nameL, arealL, omkretsL, fillL, strokeL, line_color, fill_color};

        for (Label label : labelArr) {
            label.setTextFill(Color.web("#fff"));
        }

        GraphicalObjectFactory.SetTegneprogramOblig(this);
        //Tegner figurer ved å lage objekter ut ifra navnet på togglbutton
        center.setOnMousePressed(e ->
        {
            startX = e.getX();
            startY = e.getY();

            graphicalObject = GraphicalObjectFactory.CreateObject(selectedObjectType, e);

            if (graphicalObject != null) {
                graphicalObject.Draw(cpFill, cpLine);
            }
        });

        //Setter opp hendelse på museslipp for figurer som tegnes ved å dra
        center.setOnMouseReleased(e ->
        {
            graphicalObject = GraphicalObjectFactory.CreateObject(selectedObjectType, startX, startY, e.getX(), e.getY());

            if (graphicalObject != null) {
                graphicalObject.Draw(cpFill, cpLine);
            }
        });

        //Setter opp en vertikal boks som knappene legges inn i
        VBox btns = new VBox(10);
        btns.getChildren().addAll(getInfo, changeColor, moveFrwd, moveBckw, linebtn, rectbtn, circlebtn, polygon,
                                textbtn, textA, line_color, cpLine, fill_color, cpFill, omkretsL);
        btns.setPadding(new Insets(5));
        btns.setStyle("-fx-background-color: #222222");
        btns.setPrefWidth(100);
        textA.setPrefRowCount(1);

        infoPane.setStyle("-fx-background-color: #222222");
        infoPane.setPadding(new Insets(5));
        infoPane.getChildren().addAll(nameL, omkretsL, arealL, fillL, strokeL);

        //Oppretter en BorderPane som knappfelt og tegnefelt legges inn i
        BorderPane pane = new BorderPane();
        pane.setRight(btns);
        pane.setCenter(center);
        pane.setBottom(infoPane);
        infoPane.setVisible(false);

        //pane.getChildren().indexOf((Shape)(e.getSource()))

        //Oppretter selve vinduet med en bestemt bredde og høyde
        Scene scene = new Scene(pane, 1200, 800);
        primaryStage.setTitle("Tegneprogram");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Metode for å legge figurene til feltet Pane etter de er blitt opprettet
    public void AddToPane(Shape shape)
    {
        center.getChildren().add(shape);
    }
}