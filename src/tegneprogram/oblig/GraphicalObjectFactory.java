package tegneprogram.oblig;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GraphicalObjectFactory {

    private static TegneprogramOblig tegneprogramOblig;

    //Setter referanse til main klassen
    static void SetTegneprogramOblig(TegneprogramOblig _tegneprogramOblig) {
        tegneprogramOblig = _tegneprogramOblig;
    }

    //Konstruktør
    public GraphicalObjectFactory() {}

    /*
    * Metode som tar inn en string verdi som er navnet på ønsket figur.
    * Utfører så nødvendige operasjoner for å lage et objekt av den figuren
    * ved å sette new Figur som går innom egendefinert konstruktør i objekt klassen for figurene.
    * Det er ikke satt noe modifier slik at metoden er package private.
    */
    static IGraphicalObject CreateObject(String type, MouseEvent event)
    {
        double posX = event.getX();
        double posY = event.getY();

        if (type.equals("Rectangle"))
        {
            Rektangel rektangel = new Rektangel(posX, posY, 100, 100, tegneprogramOblig);
            rektangel.SetTegneprogram(tegneprogramOblig);
            return rektangel;
        }
        else if (type.equals("Circle"))
        {
            Sirkel sirkel = new Sirkel(posX, posY, 50, tegneprogramOblig);
            sirkel.SetTegneprogram(tegneprogramOblig);
            return sirkel;
        }
        else if (type.equals("Text"))
        {
            Txt txt = new Txt(posX, posY, tegneprogramOblig.textA.getText(), tegneprogramOblig);
            txt.SetTegneprogram(tegneprogramOblig);
            return txt;
        }
        else if (type.equals("Polygon"))
        {
            MouseButton button = event.getButton();
            if (button != MouseButton.SECONDARY)
            {
                Poly.list.add(posX);
                Poly.list.add(posY);
            }
            else
            {
                Poly poly =  new Poly(tegneprogramOblig);
                poly.SetTegneprogram(tegneprogramOblig);
                return poly;
            }
            return null;

        }
        else {
            //throw new IllegalArgumentException("Unknown object type: " + type);
            return null;
        }

    }

    //Samme som over bare at her er det sluttpunktene for Linje objetet som blir satt
    static IGraphicalObject CreateObject(String type, double posX, double posY, double endX , double endY )
    {
         if (type.equals("Line"))
        {
            Linje linje =  new Linje(posX, posY, endX,  endY, tegneprogramOblig);
            linje.SetTegneprogram(tegneprogramOblig);

            return linje;
        }
        else {
            //throw new IllegalArgumentException("Unknown object type: " + type);
            return null;
        }

    }
}
