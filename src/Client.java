import java.io.*;
import java.net.Socket;

public class Client extends Thread{

    static DataInputStream input;
    static DataOutputStream output;
    static Socket connection;
    static String username;

    Game game = new Game();

    public static void Connect(String ip, int port, String userName){
        try {
            connection = new Socket(ip, port);
            username = userName;

            SetupStreams setupStreams = new SetupStreams();
            Thread streamThread = new Thread(setupStreams);
            streamThread.start();

        }catch(IOException IOE){
            System.out.println(port);
            IOE.printStackTrace();
        }
    }

    public void processData(String dataIn){
        game.chatText(dataIn);
    }

    public void sendMessage(String message){
        try {
            output.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername(){
        return username;
    }

}

class SetupStreams extends Client implements Runnable {

    public void run() {
            ServerConsole serverConsole = new ServerConsole();
            String dataIn;
            try{
                output = new DataOutputStream(connection.getOutputStream());
                input = new DataInputStream(connection.getInputStream());
                output.flush();
                System.out.println("Stream setup finished");

                output.writeUTF(username + " Has connected to the server");

                while(true){
                    dataIn = input.readUTF();
                    processData(dataIn);
                }

            }catch(IOException IOE){
                StringWriter errors = new StringWriter();
                IOE.printStackTrace(new PrintWriter(errors));
                serverConsole.WriteToConsole("[E] " + errors.toString());
                serverConsole.WriteToConsole("[E] Could Not Set Up Streams");
            }
    }
}