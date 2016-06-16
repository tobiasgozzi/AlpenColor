package alpencolor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.SwingConstants;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.input.Clipboard;
import jxl.CellType;

class ViewOutput {
        static String SELECTED_PRODUCT = "PUR Hochglanz";
        static String SELECTED_SELECTION = "";
        static String SELECTED_TONE = "";
        
        AlpenController cont1 = new AlpenController();
        
        JComboBox selectionList;
        JComboBox prodList;
        JComboBox list;
        JTextField quantity;
        JTextArea formulaField = new JTextArea();
        JTextArea formulaFielExport = new JTextArea();
        Printable formulaPrintable;
        JButton button1;          
        JButton print;
        JFrame frame = new JFrame("MIXA - AlpenColor Mixing System - by NORDWAL");
        JPanel pane1 = new JPanel(new java.awt.GridLayout(3,3));
        JPanel pane2 = new JPanel(new java.awt.GridLayout(3,1));
        JPanel pane3 = new JPanel(new java.awt.GridLayout(1,1));
        JPanel paneLogo = new JPanel();
        JPanel paneNull = new JPanel();
        ImageIcon alpenColorLogo;
        JLabel logoLabel;
        
        float[] hsbvals = new float[3];
        
        public String formula;
        
        //check if this exemplarinitialisierer causes problems due to different settings in different points in timeline
        {
            //pane1.setBounds(800, 100, 200, 0);
            pane1.setSize(600, 150);
            pane2.setSize(180, 80);
            pane3.setSize(800, 250);
            pane3.setLocation(0, 330);
            pane2.setLocation(605, 180);
            pane1.setLocation(0,150);
            paneLogo.setSize(800, 60);
            paneLogo.setLocation(0,0);
            //pane2.setBounds(400, 100, 100, 100);
            
            //Set margin for textfield
            formulaField.setBorder(BorderFactory.createCompoundBorder(
            formulaField.getBorder(), 
            BorderFactory.createEmptyBorder(25, 25, 25, 25)));
            
            //Set margin for pane1
            pane1.setBorder(BorderFactory.createCompoundBorder(
            pane1.getBorder(), 
            BorderFactory.createEmptyBorder(25, 25, 25, 25)));

            //Set margin for LogoPanel
            paneLogo.setBorder(BorderFactory.createCompoundBorder(
            paneLogo.getBorder(), 
            BorderFactory.createEmptyBorder(0, 0, 0, 0)));

            
            pane3.add(formulaField);
            formulaField.setFont(new Font("Helvetica",10,14));
            
            //float[] hsbvals = new float[3];
            Color.RGBtoHSB(101, 201, 218, hsbvals);
            paneNull.setBackground(Color.getHSBColor(hsbvals[0], hsbvals[1],hsbvals[2]));
            
            pane1.setBackground(paneNull.getBackground());
            pane2.setBackground(paneNull.getBackground());
            paneLogo.setBackground(Color.WHITE);
            /*
            pane3.setBackground(Color.red);
            */
//            pane1.setLayout(new java.awt.GridLayout(3,3));
//            pane3.setLayout(new java.awt.);
            //pane2.setLayout(new java.awt.GridLayout(3,1));
            

            //absolute
            //alpenColorLogo = new ImageIcon("//Users/tobiasgozzi/Dropbox/Alpencolor_MIXA_Formulas/FORMULAZIONI/AlpenColor.png");
            //relative
            alpenColorLogo = new ImageIcon("tables/AlpenColor.png");

            logoLabel = new JLabel();
            logoLabel.setIcon(alpenColorLogo);
            logoLabel.setLocation(0, 0);
            paneLogo.add(logoLabel);
            
            paneLogo.setVisible(true);
            //paneLogo.setOpaque(false);
            
            frame.add(pane1);
            frame.add(pane2);
            frame.add(pane3);
            frame.add(paneLogo);
            frame.add(paneNull);
        }
        
        public void createMenu(){
            JMenuItem mi = new JMenuItem();
            JMenuItem close = new JMenuItem();
            JMenuItem version = new JMenuItem();
            JMenuItem db = new JMenuItem();
            
            mi.setText("Help");
            close.setText("Chiudi");
            version.setText("Versione applicazione 0.2 beta-stat");
            db.setText("Versione database 2016.AC:02.VE:01");
            close.addMouseListener(new MouseListener(){

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    System.exit(0);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            JMenu file = new JMenu();
            file.setText("File");

            JMenu help = new JMenu();
            help.setText("?");
            
            JMenuBar mb = new JMenuBar();
            mb.setForeground(Color.getHSBColor(hsbvals[0], hsbvals[1],hsbvals[2]));
            file.add(close);
            mb.add(file);
            help.add(mi);
            help.add(version);
            help.add(db);
            mb.add(help);
            
            frame.setJMenuBar(mb);
        }
        
        public void createOperatingFrame(){
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(800, 650));
            frame.setMaximumSize(new Dimension(800, 650));
            //frame.setLayout(new java.awt.GridLayout(3,0));
            //frame.setLayout(new java.awt.BorderLayout(1, 1));
            //frame.setLocationRelativeTo(null);
            //frame.add(pane1);
            //frame.add(pane2);
            //frame.add(pane3);
            frame.pack();
            frame.setVisible(true);
            //frame.add(pane1);
        }
        void createProductComboBox(String[] products){
            
            
            prodList = new JComboBox();
            pane1.add(prodList);
            for(int i = 0;i<products.length;i++){
                prodList.addItem(products[i]);
                //pane1.add(prodList);
                //frame.add(pane1);
                prodList.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        SELECTED_PRODUCT = (String)prodList.getSelectedItem();
                        //System.out.println(SELECTED_PRODUCT);
                        updateSelectionCombobox(AlpenController.AVAILABLE_SELECTIONS);
                        
                    }
                });
                

            }
            //pane1.add(prodList);
            //frame.add(pane1);

            
        }        
        

//            view.createTonesComboBox(cont1.getDataFromXLS());

        void createTonesComboBox(){
            

            list = new JComboBox();
            list.setToolTipText("Scelga la tinta");
            list.setPrototypeDisplayValue("Scelga la Sua tinta");

            pane1.add(list);
            list.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    setSelectedTone();
                }
            });
            
        }

        void updateTonesComboBox(JComboBox selectionList){
            String[] tones;
            
            if(selectionList.getSelectedItem()!=null) {
                //System.out.println("selection NOT null");
                SELECTED_SELECTION = (String) selectionList.getSelectedItem();
            }
            else {
                //System.out.println("selection null");
            }
                
            
            
            list.removeAllItems();
            if((SELECTED_PRODUCT.equals("PUR Hochglanz"))||(SELECTED_PRODUCT.equals("PUR Color"))||(SELECTED_PRODUCT.equals("Kompakt Color"))) {
                if((SELECTED_SELECTION.equals("RAL"))||(SELECTED_SELECTION.equals("NCS"))||(SELECTED_SELECTION.equals("SIKKENS"))||(SELECTED_SELECTION.equals("SF"))){
                    cont1.setTones(SELECTED_PRODUCT+SELECTED_SELECTION);
                    tones = cont1.getDataFromXLS();
                }
//                else if(SELECTED_SELECTION.equals("NCS")){
//                    cont1.setTones(SELECTED_PRODUCT+SELECTED_SELECTION);
//                    tones = cont1.getDataFromXLS();
//                }
//                else if(SELECTED_SELECTION.equals("SF")){
//                    cont1.setTones(SELECTED_PRODUCT+SELECTED_SELECTION);
//                    tones = cont1.getDataFromXLS();
//                }
//                else if(SELECTED_SELECTION.equals("SIKKENS")){
//                    cont1.setTones(SELECTED_PRODUCT+SELECTED_SELECTION);
//                    tones = cont1.getDataFromXLS();
//                }
                else {
                    tones = new String[]{"keine Daten","keine Daten"};
                }
            }
//            else if(SELECTED_PRODUCT.equals("PUR Color")){
//                if(SELECTED_SELECTION.equals("RAL")){
//                    cont1.setTones(SELECTED_PRODUCT+SELECTED_SELECTION);
//                    tones = cont1.getDataFromXLS();
//                }
//                else if(SELECTED_SELECTION.equals("NCS")){
//                    cont1.setTones(SELECTED_PRODUCT+SELECTED_SELECTION);
//                    tones = cont1.getDataFromXLS();
//                }
//                else if(SELECTED_SELECTION.equals("SF")){
//                    cont1.setTones(SELECTED_PRODUCT+SELECTED_SELECTION);
//                    tones = cont1.getDataFromXLS();
//                }
//                else if(SELECTED_SELECTION.equals("SIKKENS")){
//                    cont1.setTones(SELECTED_PRODUCT+SELECTED_SELECTION);
//                    tones = cont1.getDataFromXLS();
//                }
//
//                else {
//                    tones = new String[]{"keine Daten","keine Daten"};
//                }
//            }
//            else if(SELECTED_PRODUCT.equals("Kompakt Color")){
//                if(SELECTED_SELECTION.equals("RAL")){
//                    cont1.setTones(SELECTED_PRODUCT+SELECTED_SELECTION);
//                    tones = cont1.getDataFromXLS();
//                }
//                else if(SELECTED_SELECTION.equals("NCS")){
//                    cont1.setTones(SELECTED_PRODUCT+SELECTED_SELECTION);
//                    tones = cont1.getDataFromXLS();
//                }
//                else if(SELECTED_SELECTION.equals("SF")){
//                    cont1.setTones(SELECTED_PRODUCT+SELECTED_SELECTION);
//                    tones = cont1.getDataFromXLS();
//                }
//                else {
//                    tones = new String[]{"keine Daten","keine Daten"};
//                }
//            }
            else {
                tones = new String[]{"keine Daten","keine Daten"};
            }
            
            for(int i = 1;i<tones.length;i++){
                list.addItem(tones[i]);
                
                pane1.add(list);
                //frame.add(pane1);

            }
            
            ViewOutput.SELECTED_TONE = (String)list.getSelectedItem();
            //System.out.println(SELECTED_TONE);
            
        }
        void setSelectedTone(){
            ViewOutput.SELECTED_TONE = (String)list.getSelectedItem();
        }
        void createQuantityField(){
            
            quantity = new JTextField();
            quantity.setVisible(true);
            pane2.add(quantity);
        }
        void createButton(String text) {
            button1 = new JButton(text);
            button1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(cont1.workbook==null)
                        return;
//                    pane3.removeAll();
//                    pane3.add(formulaField);
                    formulaField.setText("");
                    
                    formulaField.setEditable(false);
                    String text ="";
                    StringBuilder formulaToPrint = new StringBuilder();
                    //for(int i=0;i<cont1.workbook.getSheet(0).getRows();i++){
                    int foundRow = cont1.workbook.getSheet(0).findCell(SELECTED_TONE).getRow();
                    Float factor=Float.parseFloat(cont1.workbook.getSheet(0).getCell(2, foundRow).getContents().trim().replace(",", "."));
                    Float totalPrice = 0f;
                    Float totalCost = 0f;
                    Float mulitplicatedQuantity = 0f;
                    boolean totalPriceSet = false;
                    int columns = cont1.workbook.getSheet(0).getColumns();
                    //System.out.println(columns);
                    
                    
                    Integer multiplicator;
                    if(quantity.getText().equals("")||quantity.getText()==null){
                        multiplicator = 1;
                    }
                    else {
                        multiplicator = Integer.parseInt(quantity.getText());
                    } 
                        
                    for(int j=0,c=0;j<columns;j++,c++){
                        String quantity;
                        String price ="";
                        String priceVK ="";
                        String s = cont1.workbook.getSheet(0).getCell(j, foundRow).getContents().replace("_", " ").trim();
                        
                        //adds content of excel file to text object,only first and uneven columns
                        if(j==0||j%2!=0)
                            text += s;
                        
/*  !   */              formulaToPrint.append(s);
                            
                        text += "\t";
                        
                        if(j==0)
                            text +="pv\tcmp\tquantità in grammi";
                        
                        //gets executed at uneven columns - gets executed before other 
                        if(j!=0&&((j%2)!=0)){
                            
                            String test = cont1.workbook.getSheet(0).getCell(j, foundRow).getContents().trim();

                            try{
                                Prices p;
                                Float ek;
                                Float vk;
                                
                                //absolutePrice is true when a structure paste is used 
                                boolean absolutePrice = false;
                                
                                p = Prices.valueOf(test.toUpperCase().replace(" ", "_"));
                                
                                    try{
                                        mulitplicatedQuantity = Float.parseFloat(cont1.workbook.getSheet(0).getCell(j+1, foundRow).getContents().trim().replace(",", "."));
                                        mulitplicatedQuantity *= multiplicator;
                                        quantity = cont1.workbook.getSheet(0).getCell(j+1, foundRow).getContents().trim().replace(",", ".");
                                        if(quantity!=""){
                                            factor=Float.parseFloat(quantity);
                                        }
                                        //check for structurePaste 
                                        
                                        if (cont1.workbook.getSheet(0).getCell(j, foundRow).getContents().trim()!=null ) {
                                            String prod = cont1.workbook.getSheet(0).getCell(j, foundRow).getContents().trim();
                                            if ((prod.startsWith("GF1")) || (prod.startsWith("GF2")) || (prod.startsWith("GF3"))) {
                                                absolutePrice = true;
                                            }
                                        }
                                        
                                    }
                                    catch(NumberFormatException falseFormat){
                                        //System.out.println(falseFormat.toString());
                                    }

                                
                                    if (absolutePrice == true) {
                                        System.out.println("true");
                                        ek = (float) p.EP;
                                        vk = (float) p.VK;
                                    } else {
                                        System.out.println("nix true");
                                        ek = (float) (Math.round((factor/1000*p.EP)*100.0)/100.0);
                                        vk = (float) (Math.round((factor/1000*p.VK)*100.0)/100.0);
                                    }
                                totalPrice +=vk;
                                totalCost +=ek;
                                text +=vk+" €\t"+ek+" €";
                                price = Double.toString(ek);
                                priceVK = Double.toString(vk);
                            }
                            catch(IllegalArgumentException err) {
                                //System.out.println(err.toString());
                            }
                        }
                        
                        //gets executed at even columns, gets executed before
                        if(j!=0&&((j%2)==0)){
                            text += price;
                            text += priceVK;
                        }
                        
                        
                        //sets an return after every line
                        if((j%2)==0){
                            if(j==0)
                                text += "\n";
                            else {
                                if(mulitplicatedQuantity!=null)
                                text += mulitplicatedQuantity+"\n";
                                mulitplicatedQuantity = null;
                            }
                            
                        }
                        
                        //adds total price
                        if(totalPriceSet!=true&&(cont1.workbook.getSheet(0).getCell(j, foundRow).getContents().equals(""))){
                            //totalPrice = (float) (Math.round((factor/1000*totalPrice)*100.0)/100.0);
                            text +=totalPrice+"€ totale\t"+totalCost+" € "; 
                            totalPriceSet = true;
                        }
                        else if(totalPriceSet!=true&&j==columns-1) {
                            //totalPrice = (float) (Math.round((factor/1000*totalPrice)*100.0)/100.0);
                            text +="\t"+totalPrice+"€ totale\t"+totalCost+" €"; 
                            totalPriceSet = true;
                        }
                        
                    }
                    //System.out.println(text);
                    
                    //}
                        
                    
//                  formulaField = new JTextArea();
                    formulaField.setTabSize(18);
                    formulaField.setText(text);
                    formula = text;
                    formulaField.setVisible(true);
                    formulaField.copy();
                    formulaFielExport.setText(text);
                    formulaFielExport.setTabSize(14);
                    formulaFielExport.setFont(new Font("Helvetica",8,9));
//                    formulaGraphics = formulaFielExport.getGraphics();

                    formulaPrintable = formulaFielExport.getPrintable(null, null);
                    //formulaGraphics.create(1, 1, 123, 123);
                    //formulaGraphics.drawString("Hello", 20, 20);
                    print.setEnabled(true);
                    
//                    formulaField.removeAll();
               

            }});
            pane2.add(button1);
           // frame.add(pane2);
        }
        
        void createPrintButton(){
            print = new JButton("Stampa ricetta");
            //print.setText("Stampa ricetta");
            print.setEnabled(false);
            print.setVisible(true);
            pane2.add(print);
            
            print.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try {
                        PrintAlpenColor printJob = new PrintAlpenColor(formulaPrintable,formulaFielExport,ViewOutput.this);
                    } catch (IOException ex) {
                        Logger.getLogger(ViewOutput.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
            
            
                
                
        }
            
        

        void createSelectionCombobox(String[][] selections){
            selectionList = new JComboBox();
            for(int i = 0;i<selections.length;i++){
                if(selections[i][0].equals(SELECTED_PRODUCT)){
                    for(int j = 1;j<selections[i].length;j++){
                        selectionList.addItem(selections[i][j]);
                        //pane1.add(selectionList);
                        //frame.add(pane1);
                    }
                }
            }
            SELECTED_SELECTION = (String)selectionList.getItemAt(0);
            //System.out.println("selected "+SELECTED_SELECTION);
            pane1.add(selectionList);
            //frame.add(pane1);
            selectionList.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent act){
                    //SELECTED_SELECTION = (String)selectionList.getSelectedItem();
                    updateTonesComboBox(selectionList);
                    selectTonesAccordingToSelection(selectionList);
                    
                    //System.out.println("ActionListener of CreateSelectionComboBox"+SELECTED_PRODUCT+SELECTED_SELECTION);

                    
                }
            });
            SELECTED_SELECTION = (String)selectionList.getSelectedItem();

        }
        void updateSelectionCombobox(String[][] selections){
            selectionList.removeAllItems();
            for(int i = 0;i<selections.length;i++){
                if(selections[i][0].equals(SELECTED_PRODUCT)){
                    for(int j = 1;j<selections[i].length;j++){
                        selectionList.addItem(selections[i][j]);
                    }
                    //System.out.println("right entry");
                }
                else {
                    //System.out.println("wrong entry");
                }
            }
            SELECTED_SELECTION = (String)selectionList.getSelectedItem();
            //System.out.println("Sel2 "+SELECTED_SELECTION);
            selectTonesAccordingToSelection(selectionList);

        }
        void selectTonesAccordingToSelection(JComboBox selectionList){
            //selectionList.addActionListener(new ActionListener(){
            //public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = selectionList;
                Object selected = comboBox.getSelectedItem();
                //System.out.println("selectedd");
                
        }
//            });        }
    }
        


