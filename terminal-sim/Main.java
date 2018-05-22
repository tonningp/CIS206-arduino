import jssc.SerialPort; 
import jssc.SerialPortEvent; 
import jssc.SerialPortEventListener; 
import jssc.SerialPortException;
import java.util.Observer;
import java.util.Observable;

import javax.swing.*;  
import java.awt.event.*;  

public class Main implements ActionListener,Observer {  
    static SerialPort serialPort;
    static SerialPortReader portReader;
    JLabel l1,l2;
    JTextArea area;  
    JButton b;  
    Timer timer;

    Main() {  
       serialPort = new SerialPort("/dev/tty.usbmodem1441"); 
       try {
           serialPort.openPort();//Open port
           serialPort.setParams(9600, 8, 1, 0); //Set params
           int mask = SerialPort.MASK_RXCHAR; //Prepare the event mask
           serialPort.setEventsMask(mask); //Set mask
           portReader = new SerialPortReader(serialPort);
           serialPort.addEventListener(portReader); //Add SerialPortEventListener
           portReader.addObserver(this);
       }
       catch (SerialPortException ex) {
           System.out.println(ex);
       }
       JFrame f= new JFrame();  
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       l1=new JLabel();  
       l1.setBounds(50,25,100,30);
       l2=new JLabel();  
       l2.setBounds(50,25,100,75);
       area=new JTextArea();  
       area.setBounds(20,75,250,200);  
       b=new JButton("Send");  
       b.setBounds(100,300,120,30);  
       b.addActionListener(this);  
       f.add(l1);
       f.add(l2);
       f.add(area);
       f.add(b);  
       f.setSize(450,450);  
       f.setLayout(null);  
       f.setVisible(true);  
       timer = new Timer(1000, this);
       timer.setInitialDelay(1000);
       timer.start();
    }  

   public void update(Observable obs, Object obj)
   {
      if (obs == portReader)
      {
         System.out.println(portReader.getValue());
      }
   }

    public void actionPerformed(ActionEvent e){  
        String text=area.getText();  
        if(e.getSource() == this) {
            l2.setText("Send: "+text);  
        }
        else if(e.getSource() == timer) {
            System.out.println("Timer hit");
        }
    }  

    public static void main(String[] args) 
    {  
       new Main();  
    }  

}   // end of Main class definition
