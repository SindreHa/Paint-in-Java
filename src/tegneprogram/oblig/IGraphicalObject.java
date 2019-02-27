package tegneprogram.oblig;

import javafx.scene.control.ColorPicker;

public interface IGraphicalObject {
    void Draw(ColorPicker colorPickerFill, ColorPicker colorPickerLine);
    String omkrets();
    String areal();
    String navn();
    void SetEventHandlers(GraphicalObjectEvents eventHandlers);
    void SetTegneprogram(TegneprogramOblig tegneprogramOblig);
}