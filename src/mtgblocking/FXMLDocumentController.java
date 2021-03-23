/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtgblocking;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import static mtgblocking.helpers.runSwipl;
import static mtgblocking.helpers.writeToFile;

/**
 *
 * @author argeus
 */
public class FXMLDocumentController implements Initializable {
    
    private File dir, path;
    private String bN, aN, bP, aP, bT, aT;
    
    @FXML
    private TextField plFilePath, blkName, blkPow, blkTou, attName, attPow, attTou;
    
    @FXML
    private void setPlPath(ActionEvent event) {
        String mtgPlPath = plFilePath.getText();
        dir = new File(mtgPlPath);
        path = new File(mtgPlPath + "/mtg.pl");
    }
    
    @FXML
    private void setPlCreatures(ActionEvent event) {
        bN = blkName.getText();
        bP = blkPow.getText();
        bT = blkTou.getText();
        
        aN = attName.getText();
        aP = attPow.getText();
        aT = attTou.getText();
        
        writeToFile(path, bN, bP, bT, aN, aP, aT);
        runSwipl(dir);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
