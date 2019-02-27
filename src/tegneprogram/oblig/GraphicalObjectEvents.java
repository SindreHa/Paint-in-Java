package tegneprogram.oblig;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

class GraphicalObjectEvents {

    private TegneprogramOblig tegneprogramOblig;

    private double sceneX, sceneY;
    private double translateX, translateY;

    //Leger referanse for events
    GraphicalObjectEvents(TegneprogramOblig tegneprogramOblig)
    {
        this.tegneprogramOblig = tegneprogramOblig;
    }

    //EventHandler for hva som skjer på mosepress
    EventHandler<MouseEvent> shapeOnMousePressedEventHandler =
            new EventHandler<MouseEvent>()
            { @Override
            /* Flytting av figurer:
             * Første EventHandler henter inn de gjeldende verdiene for figuren
             * som har blitt selektert. SceneX, SceneY and translateX, translateY.
             */
            public void handle(MouseEvent e)
            {
                sceneX = e.getSceneX();
                sceneY = e.getSceneY();
                //System.out.println("SceneX = " + sceneX + " " + "sceneX = " + sceneY);
                translateX = ((Shape)(e.getSource())).getTranslateX();
                translateY = ((Shape)(e.getSource())).getTranslateY();
                //System.out.println("translateX = " + translateX + " " + "translateY = " + translateY);
                if (tegneprogramOblig.moveFrwd.isSelected())
                {
                    ((Shape) (e.getSource())).toFront();
                }
                else if (tegneprogramOblig.moveBckw.isSelected())
                {
                    ((Shape) (e.getSource())).toBack();
                }
                else if (tegneprogramOblig.changeColor.isSelected())
                {
                    ((Shape) (e.getSource())).setFill(tegneprogramOblig.cpFill.getValue());
                    ((Shape) (e.getSource())).setStroke(tegneprogramOblig.cpLine.getValue());
                }
                else if (tegneprogramOblig.getInfo.isSelected())
                {
                    //figFill = ((Color)((Shape)(e.getSource())).getFill().hashCode())+"";
                    //tegneprogramOblig.infoPane.setVisible(true);
                    Color figFill = (Color) ((Shape)(e.getSource())).getFill();
                    try { //Gjør om til en hex fargeverdi med hjelp av String.format og ganging med 255 for konvertering
                        String figFHex = String.format("#%02X%02X%02X",
                                (int) (figFill.getRed() * 255),
                                (int) (figFill.getGreen() * 255),
                                (int) (figFill.getBlue() * 255));

                        Color figStroke = (Color) ((Shape) (e.getSource())).getStroke();
                        String figSHex = String.format("#%02X%02X%02X",
                                (int) (figStroke.getRed() * 255),
                                (int) (figStroke.getGreen() * 255),
                                (int) (figStroke.getBlue() * 255));

                        tegneprogramOblig.fillL.setText("Fyllfarge: " + figFHex);
                        tegneprogramOblig.strokeL.setText("Linjefarge: " + figSHex);
                        tegneprogramOblig.nameL.setText("Figur: " + ((IGraphicalObject) (e.getSource())).navn());
                        tegneprogramOblig.omkretsL.setText("Omkrets: " + ((IGraphicalObject) (e.getSource())).areal() + "px");
                        tegneprogramOblig.arealL.setText("Areal: " + ((IGraphicalObject) (e.getSource())).omkrets() + "px");
                        //System.out.println(figSHex);
                        //System.out.println(figFHex);
                        //figStroke = ((Shape)(e.getSource())).getFill().toString();
                    }
                    catch (Exception t)
                    { //Kunne gjort bedre løsning på Linje her men tiden rant ut
                        System.out.println("Kan ikke sette/hente fyll på linje");
                    }
                }
            }
            };

    //EventHandler for hva som skjer når man drar noe med musen
    EventHandler<MouseEvent> shapeOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>()
            { @Override
            /*
             * Andre event handler kalkulerer ut de nye kordinatene imens
             * figuren lir dratt rundt og setter disse verdiene inn
             */
            public void handle(MouseEvent e)
            {
                double offsetX = e.getSceneX() - sceneX, offsetY = e.getSceneY() - sceneY;
                //System.out.println("offsetX = " + offsetY + " " + "offsetX = " + offsetY);
                double newTranslateX = translateX + offsetX, newTranslateY = translateY + offsetY;
                //System.out.println("newTranslateX = " + newTranslateX + " " + "newTranslateY = " + newTranslateY);
                ((Shape)(e.getSource())).setTranslateX(newTranslateX);
                ((Shape)(e.getSource())).setTranslateY(newTranslateY);
            }
            };
}
