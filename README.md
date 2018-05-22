# CIS206-arduino
## To run an application in a subfolder with the serial port
    cd <subfolder>
    javac -cp jssc.jar *.java
    java -cp '.:jssc.jar' Main

## To run an application in a subfolder with the serial port
    cd <subfolder>
    javac *.java
    java Main

## Writing to the Serial Port
    /**
     * Write String to port
     *
     * @return If the operation is successfully completed, the method returns true, otherwise false
     *
     * @throws SerialPortException
     *
     */
    public boolean writeString(String string) throws SerialPortException

    /**
     * Write String to port
     *
     * @return If the operation is successfully completed, the method returns true, otherwise false
     *
     * @throws SerialPortException
     *
     */
    public boolean writeString(String string, String charsetName) throws SerialPortException, UnsupportedEncodingException 
