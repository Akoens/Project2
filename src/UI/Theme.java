package UI;

import mdlaf.shadows.DropShadowBorder;

import javax.swing.border.Border;
import java.awt.*;

public class Theme {

    private static final Border defaultBorder = new DropShadowBorder(Color.BLACK, 5, 5, 0.3f, 12, true, true, true, true);

    public static Border getDefaultBorder() {
        return defaultBorder;
    }

}
