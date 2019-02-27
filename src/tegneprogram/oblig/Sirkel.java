package tegneprogram.oblig;

import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Circle;

public class Sirkel extends Circle implements  IGraphicalObject
{
    private GraphicalObjectEvents eventHandlers;
    private TegneprogramOblig tegneprogramOblig;

    //Konstruktør for figur
    Sirkel (double centerX, double centerY, int radius, TegneprogramOblig tegneprogramOblig)
    {
        super(centerX, centerY, radius);
        this.tegneprogramOblig = tegneprogramOblig;
        SetEventHandlers(new GraphicalObjectEvents(tegneprogramOblig));
    }

    //Metode for å beregne omkrets av figur
    public String omkrets() {
        return getLayoutBounds().getWidth()*3.14 + "";
    }
    //Metode som returnerer navnet på figuren
    public String navn() {
        return "Sirkel";
    }
    //Metode for å beregne areal av figur
    public String areal() {
        double tmp = getRadius()*2;
        return 3.14 * tmp + "";
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
        setFill(colorPickerFill.getValue());
        setStroke(colorPickerLine.getValue());
        setStrokeWidth(5);
        tegneprogramOblig.AddToPane(this);
        this.setOnMousePressed(eventHandlers.shapeOnMousePressedEventHandler);
        this.setOnMouseDragged(eventHandlers.shapeOnMouseDraggedEventHandler);
    }
}
