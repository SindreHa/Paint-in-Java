package tegneprogram.oblig;

import javafx.scene.control.ColorPicker;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Txt extends Text implements IGraphicalObject {

    private TegneprogramOblig tegneprogramOblig;

    //Konstruktør for figur
    Txt(double posX, double posY, String tekst, TegneprogramOblig tegneprogramOblig)
    {
        super(posX, posY,tekst);
        SetEventHandlers(new GraphicalObjectEvents(tegneprogramOblig));
    }

    private GraphicalObjectEvents eventHandlers;

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
        return "?";
    }
    //Metode som returnerer navnet på figuren
    public String navn() {
        return "Tekst";
    }
    //Metode for å beregne areal av figur
    public String areal() {
        return "?";
    }

    //Metode som setter diverse egenskaper for figuren for å så sette EventHandlers og legger figuren til et Pane
    public void Draw(ColorPicker colorPickerFill, ColorPicker colorPickerLine)
    {
        setFill(colorPickerFill.getValue()); //Setter fyll og linjefarge fra valgt i colorPicker
        setStroke(colorPickerLine.getValue());
        setFont(new Font(40));
        setStrokeWidth(1);
        tegneprogramOblig.AddToPane(this);
        this.setOnMousePressed(eventHandlers.shapeOnMousePressedEventHandler);
        this.setOnMouseDragged(eventHandlers.shapeOnMouseDraggedEventHandler);
        //AddToFigurer(Txt);
    }
}
