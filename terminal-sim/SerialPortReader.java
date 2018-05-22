import jssc.SerialPort; 
import jssc.SerialPortEvent; 
import jssc.SerialPortEventListener; 
import jssc.SerialPortException;
import java.io.UnsupportedEncodingException;
import java.util.Observable;

/*
 * In this class the method serialEvent must be implemented, through it we learn about 
 * events that happened to our port. But we will not report on all 
 * events. We will only report those events that we put in the mask. 
 * In this case the arrival of the data.
 */
class SerialPortReader extends Observable implements SerialPortEventListener {
    private String b;
    private String current_value;
    private SerialPort serialPort;
    public SerialPortReader(SerialPort serialPort)
    {
       this.serialPort = serialPort;
       b = new String("");
    }

    public void printBuffer(byte buffer[]) throws UnsupportedEncodingException
    {
        String str = new String(buffer,"UTF-8");
        System.out.println(str);
    }

    public String getValue()
    {
       return current_value;
    }

   public void setValue(String s)
   {
      current_value = s;
      setChanged();
      notifyObservers();
   }

    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR()){//If data is available
                //Read data, if 10 bytes available 
                try {
                    byte buffer[] = serialPort.readBytes(1);

                    if(buffer[0] == '\n')
                    {
                           setValue(b);
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
