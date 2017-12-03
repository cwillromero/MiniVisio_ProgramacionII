/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Will
 */
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintActionListener implements Runnable {

    private final BufferedImage image;

    public PrintActionListener(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void run() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(new ImagePrintable(printJob, image));
        if (printJob.printDialog()) {
            try {
                printJob.print();
            } catch (PrinterException prt) {
            }
        }
    }
}
