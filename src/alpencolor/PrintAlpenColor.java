
package alpencolor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import org.w3c.dom.*;
import org.w3c.dom.html.HTMLDOMImplementation;


public class PrintAlpenColor implements Printable {
    //JWindow printWindow = new JWindow();
    Printable thisPrintJob;
    JFrame printDialog = new JFrame();
    JPanel printerSelection = new JPanel(new GridLayout(4,1));
    JComboBox printerList;
    JButton printIt;
    int selectedPrinter = 0;
    PrintService[] service;
    PrintService[] pservices;
    DocPrintJob job;
    Graphics2D graphicOfFormula;
    //String defaultPrinter = javax.print.PrintServiceLookup.lookupDefaultPrintService().getName();   
    
    PrintAlpenColor(Printable givenFormula, JTextArea givenTextArea, ViewOutput view) throws IOException{
        

        
        System.out.println(givenTextArea.getText());
        
        //InputStream is = new ByteArrayInputStream(givenFormula.getBytes());

        DocFlavor doc = new javax.print.DocFlavor("text/html; charset=utf-8","java.io.InputStream");
        //Doc document = new SimpleDoc(is, doc, null);
        
        AttributeSet aset = new javax.print.attribute.HashAttributeSet();

        service = javax.print.PrintServiceLookup.lookupPrintServices(null, null); //(doc, aset);
        pservices = PrintServiceLookup.lookupPrintServices(doc, aset);
        
        java.awt.print.Paper A4 = new java.awt.print.Paper();
        java.awt.print.PageFormat orientation = new java.awt.print.PageFormat();
        
        A4.setSize(595.224, 841.824);
//        A4.setSize(94.936, 841.536);
        A4.setImageableArea(56, 80, 509, 756);
        
        orientation.setOrientation(java.awt.print.PageFormat.PORTRAIT);
        orientation.setPaper(A4);
        
                
        //PrinterJob.getPrinterJob();
        
        //System.out.println(javax.print.PrintServiceLookup.lookupDefaultPrintService());
        printDialog.setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
        printDialog.setMaximumSize(new Dimension(300,400));
        printDialog.setMinimumSize(new Dimension(300,400));
        //printWindow.add(printDialog);
        printDialog.setVisible(true);
        
        printerList = new JComboBox();
        printIt = new JButton("Print");
        printerList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent printerSelectionEvent){
                selectedPrinter= printerList.getSelectedIndex();
            }
        });
        
        for(PrintService servs : service){

            printerList.addItem(servs.toString());
            
            
        }
        
        
        printerSelection.add(printIt);
        printerSelection.add(printerList);
        printDialog.add(printerSelection);

        
        printIt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //DocPrintJob pri = (DocPrintJob) printerList.getSelectedItem();
                System.out.println(selectedPrinter);
                
                /*Printing HTML
                PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
                PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
                PrintService service = ServiceUI.printDialog(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration(), 200, 200, printService, defaultService, flavor, pras);
                if (service != null) {
                    DocPrintJob job = service.createPrintJob();
                    FileInputStream fis = new FileInputStream(doc);
                    DocAttributeSet das = new HashDocAttributeSet();
                    Doc document = new SimpleDoc(fis, flavor, das);
                    job.print(document, pras);
                }
                */
                
                PrinterJob pri = PrinterJob.getPrinterJob();
                //job = service[0].createPrintJob();
                try {
                    pri.setPrintService(service[selectedPrinter]);
                } catch (PrinterException ex) {
                    Logger.getLogger(PrintAlpenColor.class.getName()).log(Level.SEVERE, null, ex);
                }
                pri.setJobName("Stampa ricetta - MIXA");
                pri.setCopies(PAGE_EXISTS);
                pri.defaultPage(orientation);
                
                pri.setPrintable(givenFormula);
                try {
                    pri.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(PrintAlpenColor.class.getName()).log(Level.SEVERE, null, ex);
                }
                //pri.setPageable(givenFormula);
                 
                //following code disabled because object "job" - class DocPrintJob - is no more used 
                    /*job.addPrintJobListener(new PrintJobListener(){
                    
                    
                    @Override
                    public void printDataTransferCompleted(PrintJobEvent pje) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                    @Override
                    public void printJobCompleted(PrintJobEvent pje) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                    @Override
                    public void printJobFailed(PrintJobEvent pje) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                    @Override
                    public void printJobCanceled(PrintJobEvent pje) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                    @Override
                    public void printJobNoMoreEvents(PrintJobEvent pje) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                    @Override
                    public void printJobRequiresAttention(PrintJobEvent pje) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                    });*/
                
                //closes frame after print is clicked
                printDialog.setVisible(false);
                printDialog.dispose();
            }
        });
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        graphics.drawString("", 1, 1);
        return 1;
    }
    
    
}

