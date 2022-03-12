import java.net.*;
import java.io.*;
import java.util.Date;
public class DateServer {
    private int socket;
    public DateServer(int socket){
        this.socket=socket;
    }

    public static void main(String[] args) throws IOException{
        if (args[0]=="-h" || args[0]==null){
            System.err.println("err");
        }
        DateServer dateServer = new DateServer(Integer.parseInt(args[0]));
        dateServer.run();
    }
    
    public void run() throws IOException{
        try {
            ServerSocket socketServer = new ServerSocket(socket);
            System.out.println("Serveur lancé sur le port : "+ socket);
            System.out.println("Ecoute...");
            while (true){
                Socket socketClient = socketServer.accept();
                System.out.println("Connexion : "+socketClient.getInetAddress());

                OutputStream output = socketClient.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(new Date().toString());
                
                socketServer.close();
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public void help() {
        System.out.println("Veuillez entrer en paramètre le port sur lequel vous voulez vous connecter.");
    }
}  