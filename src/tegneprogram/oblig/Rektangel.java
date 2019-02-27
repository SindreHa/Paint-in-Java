package tegneprogram.oblig;

import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;

public class Rektangel extends Rectangle implements IGraphicalObject {

    private GraphicalObjectEvents eventHandlers;
    private TegneprogramOblig tegneprogramOblig;

    //Konstruktør for figur, som et lite notat er det ingen modifier på konstruktør for å sette den til package private
    Rektangel(double x, double y, double height, double width, TegneprogramOblig tegneprogramOblig)
    {
        super(x, y, width, height);
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
    public String omkrets()
    {
        return getHeight()*2 + getWidth()*2 +"";
    }
    //Metode for å beregne areal av figur
    public String areal()
    {
        return getHeight() * getWidth() +"";
    }
    //Metode som returnerer navnet på figuren
    public String navn()
    {
        return "Rektangel";
    }

    //Metode som setter diverse egenskaper for figuren for å så sette EventHandlers og legger figuren til et Pane
    public void Draw(ColorPicker colorPickerFill, ColorPicker colorPickerLine)
    {
        setFill(colorPickerFill.getValue()); //Setter fyll og linjefarge fra valgt i colorPicker
        setStroke(colorPickerLine.getValue());
        setStrokeWidth(5);
        tegneprogramOblig.AddToPane(this);
        this.setOnMousePressed(eventHandlers.shapeOnMousePressedEventHandler);
        this.setOnMouseDragged(eventHandlers.shapeOnMouseDraggedEventHandler);
    }
}