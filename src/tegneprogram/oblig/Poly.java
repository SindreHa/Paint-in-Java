package tegneprogram.oblig;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public class Poly extends Polygon implements IGraphicalObject
{
    static ArrayList<Double> list = new ArrayList<>();
    private GraphicalObjectEvents eventHandlers;
    private TegneprogramOblig tegneprogramOblig;

    //Konstruktør for figur
    Poly(TegneprogramOblig tegneprogramOblig)
    {
        this.tegneprogramOblig = tegneprogramOblig;
        SetEventHandlers(new GraphicalObjectEvents(tegneprogramOblig));
    }

    //Metode for å beregne omkrets av figur
    public String omkrets() {
        return "?";
    }
    //Metode som returnerer navnet på figuren
    public String navn() {
        return "Polygon";
    }
    //Metode for å beregne areal av figur
    public String areal() {
        return "?";
    }

    //Metode som setter eventhandlers fra objektet GraphicalObjectEvents
    public void SetEventHandlers(GraphicalObjectEvents eventHandlers)
    {
        this.eventHandlers = eventHandlers;
    }

    //Setter referanse for objektet så det får tilgang til diverse fra main klassen
    public void SetTegneprogram(TegneprogramOblig tegneprogramOblig)
    {
        this.tegneprogramOblig = tegneprogramOblig;
    }

    //Metode som setter diverse egenskaper for figuren for å så sette EventHandlers og legger figuren til et Pane
    public void Draw(ColorPicker colorPickerFill, ColorPicker colorPickerLine)
    {
        getPoints().clear();
        getPoints().addAll(list);
        setFill(colorPickerFill.getValue());
        setStroke(colorPickerLine.getValue());
        setStrokeWidth(5);
        tegneprogramOblig.AddToPane(this);
        this.setOnMousePressed(eventHandlers.shapeOnMousePressedEventHandler);
        this.setOnMouseDragged(eventHandlers.shapeOnMouseDraggedEventHandler);
        list = null;
        list = new ArrayList<>();
    }
}
