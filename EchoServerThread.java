import java.net.*;
import java.io.*;

public class EchoServerThread {
    public static void main(String args[]) throws IOException {

        ServerSocket listener = null; //initialisation listener
        System.out.println("Serveur lancé sur le port : "+ Integer.parseInt(args[0]));
        System.out.println("Ecoute...");
        
        try {
            listener = new ServerSocket(Integer.parseInt(args[0])); //on crée un serveur listener
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            while (true) {
                Socket socketOfServer = listener.accept();  //quand quelqu'un se connecte (ici EchoClient)
                new ClientHandler(socketOfServer).start();  //on lance notre classe qui va gerer individuellement chaque client
            }
        } finally {
            listener.close();   //on ferme le serveur listerner
        }
    }
}

class ClientHandler extends Thread {
    private Socket socketOfServer;
    
    public ClientHandler(Socket socketOfServer) {
        this.socketOfServer = socketOfServer;
    }
    
    @Override
    public void run(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            PrintWriter writer = new PrintWriter(socketOfServer.getOutputStream(), true);
        
            while (true){
                String texte = reader.readLine();
                if (texte.equals("quit")){
                    break;
                }
                writer.println(texte.toUpperCase());
            }
        } catch (IOException e ){
            System.err.println(e);
        }
    }
}