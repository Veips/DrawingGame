import java.io.*;
import java.net.*;

public class Server extends SetupServer {

    public void StopServer(){
        if(serverSocket != null){
            serverSocket = null;
        }
    }

}

class SetupServer {

    static Socket connection;
    static ServerSocket serverSocket;
    static DataOutputStream output;
    static DataInputStream input;

    public void StartServer(String serverName, int serverPort) throws Exception {

        CreateServer createServer = new CreateServer();
        ServerConsole serverConsole = new ServerConsole();
        StartServerClass startServer = new StartServerClass();
        Thread connectionThread = new Thread(startServer);


        try {
            serverConsole.WriteToConsole("[S] Starting Server");
            serverSocket = new ServerSocket(serverPort);
            serverConsole.WriteToConsole("[S] Server Started");
            serverConsole.WriteToConsole("[S] Starting Connection Thread");
            connectionThread.start();
        } catch (Exception IOE) {
            serverSocket = null;
            StringWriter errors = new StringWriter();
            IOE.printStackTrace(new PrintWriter(errors));
            serverConsole.WriteToConsole("[E] " + errors.toString());
            serverConsole.WriteToConsole("[E] Could Not Start Server");
            IOE.printStackTrace();
            if(!connectionThread.isInterrupted()){
                connectionThread.interrupt();
            }
            createServer.show();
            //serverConsole.hide();
        }
    }

    public void stop(){
        System.out.println("stop()");
    }



    //--------------=-=--=-=-=-=--



    public void setupStreams(){
        ServerConsole serverConsole = new ServerConsole();
        try{
            output = new DataOutputStream(connection.getOutputStream());
            input = new DataInputStream(connection.getInputStream());
            output.flush();
            serverConsole.WriteToConsole("[S] Stream Setup For " + connection.getInetAddress() + " Finished");

        }catch(IOException IOE){
            StringWriter errors = new StringWriter();
            IOE.printStackTrace(new PrintWriter(errors));
            serverConsole.WriteToConsole("[E] " + errors.toString());
            serverConsole.WriteToConsole("[E] Could Not Set Up Streams");
        }
    }

}

class StartServerClass extends SetupServer implements Runnable {
    public void run() {

        ServerConsole serverConsole = new ServerConsole();
        serverConsole.WriteToConsole("[S] Waiting For Connections");

        while(true){
            try {
                connection = serverSocket.accept();
                serverConsole.WriteToConsole("[S] New Connection From " + connection.getInetAddress());
                serverConsole.WriteToConsole("[S] Setting Up Streams For " + connection.getInetAddress());
                setupStreams();
            }catch(IOException IOE){
                StringWriter errors = new StringWriter();
                IOE.printStackTrace(new PrintWriter(errors));
                serverConsole.WriteToConsole("[E] " + errors.toString() + connection.getInetAddress());
                serverConsole.WriteToConsole("[E] New Connection Error " + connection.getInetAddress());
                break;
            }
        }
    }
}

class ServerDataClass extends SetupServer implements Runnable{
    public void run() {

        ServerConsole serverConsole = new ServerConsole();
        String dataIn;

        while(true){
            try {
                dataIn = input.readUTF();
                output.writeUTF(dataIn);
            }catch(IOException IOE){
                StringWriter errors = new StringWriter();
                IOE.printStackTrace(new PrintWriter(errors));
                serverConsole.WriteToConsole("[E] " + errors.toString() + connection.getInetAddress());
            }
        }
    }
}