import jssc.SerialPort; import jssc.SerialPortException;
import java.io.UnsupportedEncodingException;

public class Main {

public static void main(String[] args) throws InterruptedException,UnsupportedEncodingException{
    SerialPort serialPort = new SerialPort("/dev/tty.usbmodem1441");
    try {
        serialPort.openPort();//Open serial port
        serialPort.setParams(9600, 8, 1, 0);//Set params.
        for(int i=0;i<20;i++)
        {
            byte[] buffer = serialPort.readBytes(10);//Read 10 bytes from serial port
            String str = new String(buffer,"UTF-8");
            System.out.println(str);
            Thread.sleep(1000);
        }
        serialPort.closePort();//Close serial port
    }
    catch (SerialPortException ex) {
        System.out.println(ex);
    }
}
}
