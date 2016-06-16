package alpencolor;

import java.awt.Color;
import jxl.*;
import java.io.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.read.biff.BiffException;
       
        


/**
 *
 * @author tobiasgozzi
 */
    public class AlpenController extends Tone implements Tintable {
        
        /*
        final static String[] AVAILABLE_PRODUCTS = {"PUR Hochglanz","PUR Color","Kompakt Color"};
        final static String[][] AVAILABLE_SELECTIONS = {{"PUR Hochglanz","NCS","RAL","SIKKENS","SF"},{"PUR Color","NCS","RAL","SIKKENS","SF"},{"Kompakt Color","NCS","RAL","SIKKENS"}};
        final static String PUR_COLOR_RAL_PATH =     "//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/PUR_Color_RAL.xls";
        final static String PUR_COLOR_NCS_PATH =     "//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/PUR_Color_NCS.xls";
        final static String PUR_COLOR_SF_PATH =     "//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/PUR_Color_SF.xls";
        final static String PUR_COLOR_SIKKENS_PATH =     "//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/PUR_Color_SIKKENS.xls";
        final static String KOMPAKT_COLOR_RAL_PATH = "//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/Kompakt_Color_RAL.xls";
        final static String KOMPAKT_COLOR_NCS_PATH = "//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/Kompakt_Color_NCS.xls";        
        final static String KOMPAKT_COLOR_SIKKENS_PATH = "//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/Kompakt_Color_SIKKENS.xls";        
        final static String PUR_HOCHGLANZ_RAL_PATH = "//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/PUR_Hochglanz_RAL.xls";
        final static String PUR_HOCHGLANZ_NCS_PATH = "//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/PUR_Hochglanz_NCS.xls";
        final static String PUR_HOCHGLANZ_SF_PATH = "//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/PUR_Hochglanz_SF.xls";
        final static String PUR_HOCHGLANZ_SIKKENS_PATH = "//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/PUR_Hochglanz_SIKKENS.xls";
        */
      
        
        
        final static String[] AVAILABLE_PRODUCTS = {"PUR Hochglanz","PUR Color","Kompakt Color"};
        final static String[][] AVAILABLE_SELECTIONS = {{"PUR Hochglanz","NCS","RAL","SIKKENS","SF"},{"PUR Color","NCS","RAL","SIKKENS","SF"},{"Kompakt Color","NCS","RAL","SIKKENS"}};
        final static String PUR_COLOR_RAL_PATH =     "tables/PUR_Color_RAL.xls";
        final static String PUR_COLOR_NCS_PATH =     "tables/PUR_Color_NCS.xls";
        final static String PUR_COLOR_SF_PATH =     "tables/PUR_Color_SF.xls";
        final static String PUR_COLOR_SIKKENS_PATH =     "tables/PUR_Color_SIKKENS.xls";
        final static String KOMPAKT_COLOR_RAL_PATH = "tables/Kompakt_Color_RAL.xls";
        final static String KOMPAKT_COLOR_NCS_PATH = "tables/Kompakt_Color_NCS.xls";        
        final static String KOMPAKT_COLOR_SIKKENS_PATH = "tables/Kompakt_Color_SIKKENS.xls";        
        final static String KOMPAKT_COLOR_SF_PATH = "tables/Kompakt_Color_SF.xls";        
        final static String PUR_HOCHGLANZ_RAL_PATH = "tables/PUR_Hochglanz_RAL.xls";
        final static String PUR_HOCHGLANZ_NCS_PATH = "tables/PUR_Hochglanz_NCS.xls";
        final static String PUR_HOCHGLANZ_SF_PATH = "tables/PUR_Hochglanz_SF.xls";
        final static String PUR_HOCHGLANZ_SIKKENS_PATH = "tables/PUR_Hochglanz_SIKKENS.xls";
        
        String tonePath = "";
        
        Workbook workbook = null;
        protected AlpenController(){
            
        };
        
        void setTones(String toene){
            String settedTones;
            switch(toene){
                case "PUR HochglanzNCS":
                    //System.out.println("switch "+toene+1);
                    settedTones =PUR_HOCHGLANZ_NCS_PATH;
                    break;
                case "PUR HochglanzRAL":
                    //System.out.println("switch "+toene+2);
                    settedTones =PUR_HOCHGLANZ_RAL_PATH;
                    break;
                case "PUR HochglanzSF":
                    settedTones =PUR_HOCHGLANZ_SF_PATH;
                    break;
                case "PUR HochglanzSIKKENS":
                    //System.out.println("switch "+toene+1);
                    settedTones =PUR_HOCHGLANZ_SIKKENS_PATH;
                    break;
                case "PUR ColorNCS":
                    //System.out.println("switch "+toene+3);
                    settedTones =PUR_COLOR_NCS_PATH;
                    break;
                case "PUR ColorRAL":
                    //System.out.println("switch "+toene+4);
                    settedTones =PUR_COLOR_RAL_PATH;
                    break;
                case "PUR ColorSF":
                    settedTones =PUR_COLOR_SF_PATH;
                    break;
                case "PUR ColorSIKKENS":
                    settedTones =PUR_COLOR_SIKKENS_PATH;
                    break;
                case "Kompakt ColorNCS":
                    //System.out.println("switch "+toene+5);
                    settedTones = KOMPAKT_COLOR_NCS_PATH;
                    break;
                case "Kompakt ColorRAL":
                    //System.out.println("switch "+toene+6);
                    settedTones = KOMPAKT_COLOR_RAL_PATH;
                    break;
                case "Kompakt ColorSIKKENS":
                    //System.out.println("switch "+toene+6);
                    settedTones = KOMPAKT_COLOR_SIKKENS_PATH;
                break;
                default:
                        //System.out.println("switch "+toene+7);
                        //System.out.println(toene.equals("PUR HochglanzRAL"));
                        //System.out.println(toene.equals("PUR HochglanzNCS"));
                        //System.out.println("errorFile");
                        //System.exit(14);
                        settedTones="";
                break;
                    
            }
                
            try {
                workbook = Workbook.getWorkbook(new File(settedTones));
                
            }
            catch (IOException | BiffException ex) {
                Logger.getLogger(AlpenColor.class.getName()).log(Level.SEVERE, null, ex);
                //System.out.println("workbook error");
            }


        }
        
        String[] tones;

        String[] getDataFromXLS() {
            Sheet sheet1 = workbook.getSheet(0);
            int rowsQuant = sheet1.getRows();
            tones = new String[rowsQuant];
            for(int i = 1;i<rowsQuant;i++){
                tones[i] = sheet1.getCell(0, i).getContents();
            }
            return tones;
        }

        
        @Override
        void makeColor(){
        };
        public double calPrice(){
            return 2.4;
        };
        public Color getColor(){
            return new Color(23,23,23);
        };
        @Override
        int refillBase(int a){
            return a;
        }
    
    }

