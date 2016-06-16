
package alpencolor;
/*import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.*;
import jxl.read.biff.BiffException;
import javax.swing.*;
import java.awt.*;*/


public class AlpenColor {

    public static void main(String[] args) {
        

            ViewOutput view = new ViewOutput();
            
            view.createMenu();
            view.createProductComboBox(AlpenController.AVAILABLE_PRODUCTS);
            view.createSelectionCombobox(AlpenController.AVAILABLE_SELECTIONS);
//            AlpenController cont1 = new AlpenController();
            view.createTonesComboBox();
//            view.createTonesComboBox(new String[]{"","seleziona un prodotto"});
            view.createQuantityField();
            view.createButton("Visualizza ricetta");
            view.createPrintButton();
            view.createOperatingFrame();
            //PrintAlpenColor alp = new PrintAlpenColor();
            
            
    }
    
}
