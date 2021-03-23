/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtgblocking;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author argeus
 */
public class helpers {
    
    private static String tradePred = "trade";
    private static String survivePred = "surviveBlock";
    private static String sed = "sed -i '39,$d' mtg.pl";
    private static String swipl;
    
    private static String writeInit(String pred, String cr1, String cr2){
        return ":- initialization " + pred + "(" + cr1 + "," + cr2 + ").";    
    }
    
    private static String writeCreature(String crName, String power, String toughness) {
        return "creature" + "(" + crName + "," + power + "," + toughness + ").";
    }
    
    static void writeToFile(File path, String blkName, String blkPow, String blkTou, String attName, String attPow, String attTou) {
        try {
            swipl = "swipl -s mtg.pl -g \"saveQueries('1Test.txt'), survivors(" + blkName + "," + attName + ")," + "stopQueriesSaving, halt\"";
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(writeCreature(blkName, blkPow, blkTou));
            bw.newLine();
            bw.write(writeCreature(attName, attPow, attTou));
            bw.newLine();
            bw.write(writeInit(tradePred, blkName, attName));
            bw.newLine();
            bw.write(writeInit(survivePred, blkName, attName));
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void runSwipl(File dir){
        try {
            ProcessBuilder pb = new ProcessBuilder("/usr/bin/bash", "-c", swipl);
            ProcessBuilder pb2 = new ProcessBuilder("/usr/bin/bash", "-c", sed);
            pb.directory(dir);
            pb2.directory(dir);
            pb.start();
            TimeUnit.MILLISECONDS.sleep(100);
            pb2.start();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    
}
