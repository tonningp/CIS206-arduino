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
        serialPort.setParams(9600, 8, 1, 0); //Set params
        int mask = SerialPort.MASK_RXCHAR; //Prepare the event mask
        serialPort.setEventsMask(mask); //Set mask
        serialPort.addEventListener(new SerialPortReader()); //Add SerialPortEventListener
    }
    catch (SerialPortException ex) {
        System.out.println(ex);
    }
}

/*
 * In this class must implement the method serialEvent, through it we learn about 
 * events that happened to our port. But we will not report on all 
 * events. We will only report those events that we put in the mask. 
 * In this case the arrival of the data.
 */
static class SerialPortReader implements SerialPortEventListener {
    private String b;
    public SerialPortReader()
    {
       b = new String("");
    }
    public void printBuffer(byte buffer[]) throws UnsupportedEncodingException
    {
        String str = new String(buffer,"UTF-8");
        System.out.println(str);
    }

    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR()){//If data is available
                //Read data, if 10 bytes available 
                try {
                    byte buffer[] = serialPort.readBytes(1);
                    if(buffer[0] == '\n')
                    {
                           System.out.println(b);
                           b = "";
                    }
                    else 
                    {
                           try 
                           {
                              b += new String(buffer,"UTF-8");
                           }
                           catch(UnsupportedEncodingException ex)
                           {
                              System.out.println(ex);
                           }
                    }
                }
                catch (SerialPortException ex) 
                {
                  System.out.println(ex);
                }
        }
    }
}
}
