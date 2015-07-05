import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*; // For Database Access

public class PrintUI implements Printable, ActionListener {
    JFrame frameToPrint;

    public int print(Graphics g, PageFormat pf, int page) throws
            PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY() - 55);
        frameToPrint.setVisible(true);
        frameToPrint.print(g);
        return PAGE_EXISTS;
    }

    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {

            }
        }
    }

    public PrintUI(JFrame f) {
        frameToPrint = f;
    }

    public static void main(String args[]) {

        final JButton printButton = new JButton("Print");
        UIManager.put("swing.boldMetal", Boolean.TRUE);
        JFrame f = new JFrame("GatePass");

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Font f1 = new Font("Times New Roman", Font.BOLD, 12);

        printButton.setVisible(true);
        JLabel label1 = new JLabel("Vision Techno Solutions", JLabel.CENTER);
        label1.setBounds(30, 30, 200, 40);
	f.add(label1);
        JLabel label2 = new JLabel("Batch No:");
        label2.setBounds(10, 70, 80, 20);
	f.add(label2);
        JLabel label3 = new JLabel("Date and Time:");
        label3.setBounds(10, 100, 120, 20);
	f.add(label3);
        JLabel label4 = new JLabel("Visitor's Name:");
        label4.setBounds(10, 130, 120, 20);
	f.add(label4);
        JLabel label5 = new JLabel("Concern Person:");
        label5.setBounds(10, 160, 120, 20);
        JLabel label6 = new JLabel("Purpose of Visit:");
        label6.setBounds(10, 190, 120, 20);
        JLabel label7 = new JLabel("Manager's Sign");
        label7.setBounds(140, 230, 120, 20);
        JLabel label8 = new JLabel("Visitor's Sign");
        label8.setBounds(10, 230, 120, 20);

        printButton.addActionListener(new PrintUI(f));
        f.setSize(300, 350);
        f.setLocationRelativeTo(null);
        f.add(printButton, BorderLayout.SOUTH);
        f.setResizable(false);
        f.setVisible(true);
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printButton.setVisible(false);
            }
        });
    }
}
