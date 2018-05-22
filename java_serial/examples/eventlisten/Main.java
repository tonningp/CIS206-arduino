import jssc.SerialPort; 
import jssc.SerialPortEvent; 
import jssc.SerialPortEventListener; 
import jssc.SerialPortException;
import java.io.UnsupportedEncodingException;

public class Main {

static SerialPort serialPort;

public static void main(String[] args) {
    serialPort = new SerialPort("/dev/tty.usbmodem1441"); 
    try {
        serialPort.openPort();//Open port
        serialPort.setParams(9600, 8, 1, 0);//Set params
        int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
        serialPort.setEventsMask(mask);//Set mask
        serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
    }
    catch (SerialPortException ex) {
        System.out.println(ex);
    }
}

/*
 * In this class must implement the method serialEvent, through it we learn about 
 * events that happened to our port. But we will not report on all events but only 
 * those that we put in the mask. In this case the arrival of the data and change the 
 * status lines CTS and DSR
 */
static class SerialPortReader implements SerialPortEventListener {
   
    public void printBuffer(byte buffer[]) throws UnsupportedEncodingException
    {
        String str = new String(buffer,"UTF-8");
        System.out.println(str);
    }

    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR()){//If data is available
                //Read data, if 10 bytes available 
                try {
                    byte buffer[] = serialPort.readBytes();
                    try {
                        printBuffer(buffer);
                    }
                    catch(UnsupportedEncodingException ex) {
                     System.out.println(ex);
                    }
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
        }
        else if(event.isCTS()){//If CTS line has changed state
            if(event.getEventValue() == 1){//If line is ON
                System.out.println("CTS - ON");
            }
            else {
                System.out.println("CTS - OFF");
            }
        }
        else if(event.isDSR()){///If DSR line has changed state
            if(event.getEventValue() == 1){//If line is ON
                System.out.println("DSR - ON");
            }
            else {
                System.out.println("DSR - OFF");
            }
        }
    }
}
}
