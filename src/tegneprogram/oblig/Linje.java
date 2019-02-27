package tegneprogram.oblig;

import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Line;

public class Linje extends Line implements IGraphicalObject
{
    private TegneprogramOblig tegneprogramOblig;
    private GraphicalObjectEvents eventHandlers;

    //Konstruktør for figur
    Linje (double startX, double startY, double endX, double endY, TegneprogramOblig tegneprogramOblig)
    {
        super(startX, startY, endX, endY);
        this.tegneprogramOblig = tegneprogramOblig;
        SetEventHandlers(new GraphicalObjectEvents(tegneprogramOblig));
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

    //Metode for å beregne omkrets av figur
    public String omkrets() {
        return getLayoutBounds().getWidth() + "";
    }
    //Metode som returnerer navnet på figuren
    public String navn() {
        return "Linje";
    }
    //Metode for å beregne areal av figur
    public String areal() {
        return "?";
    }

    //Metode som setter diverse egenskaper for figuren for å så sette EventHandlers og legger figuren til et Pane
    public void Draw(ColorPicker colorPickerFill, ColorPicker colorPickerLine)
    {
        setStroke(colorPickerLine.getValue());
        setStrokeWidth(5);
        tegneprogramOblig.AddToPane(this);
        this.setOnMousePressed(eventHandlers.shapeOnMousePressedEventHandler);
        this.setOnMouseDragged(eventHandlers.shapeOnMouseDraggedEventHandler);
    }
}
