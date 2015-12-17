package tank;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TankClient extends Thread {

    private Socket socket;
    private DataOutputStream dos;

    private boolean connect() {
        try {
            socket = new Socket("127.0.0.1", 6000);
            dos = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("Successfully connected to the server.");
            return true;
        } catch (IOException e) {
            System.out.println("Unable to connect to the address: ");
            return false;
        }
    }

    public void run(String msg) {
        connect();
        try {
            dos.writeBytes(msg);
            dos.flush();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(TankClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
