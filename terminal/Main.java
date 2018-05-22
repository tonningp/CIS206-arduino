import jssc.SerialPort; 
import jssc.SerialPortEvent; 
import jssc.SerialPortEventListener; 
import jssc.SerialPortException;

import javax.swing.*;  
import java.awt.event.*;  
public class Main implements ActionListener{  
    static SerialPort serialPort;
    JLabel l1,l2;  
    JTextArea area;  
    JButton b;  
    Main() {  
        JFrame f= new JFrame();  
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1=new JLabel();  
        l1.setBounds(50,25,100,30);  
        l2=new JLabel();  
        l2.setBounds(160,25,100,30);  
        area=new JTextArea();  
        area.setBounds(20,75,250,200);  
        b=new JButton("Count Words");  
        b.setBounds(100,300,120,30);  
        b.addActionListener(this);  
        f.add(l1);f.add(l2);f.add(area);f.add(b);  
        f.setSize(450,450);  
        f.setLayout(null);  
        f.setVisible(true);  
    }  
    public void actionPerformed(ActionEvent e){  
        String text=area.getText();  
        String words[]=text.split("\\s");  
        l1.setText("Words: "+words.length);  
        l2.setText("Characters: "+text.length());  
    }  
    public static void main(String[] args) {  
       serialPort = new SerialPort("/dev/tty.usbmodem1441"); 
       try {
           serialPort.openPort();//Open port
           serialPort.setParams(9600, 8, 1, 0); //Set params
           int mask = SerialPort.MASK_RXCHAR; //Prepare the event mask
           serialPort.setEventsMask(mask); //Set mask
           serialPort.addEventListener(new SerialPortReader(serialPort)); //Add SerialPortEventListener
       }
       catch (SerialPortException ex) {
           System.out.println(ex);
       }

       new Main();  
    }  

}   // end of Main class definition
