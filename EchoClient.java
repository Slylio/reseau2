import java.net.*;
import java.io.*;

public class EchoClient {
    private int numSocket;
    private String host;

    public EchoClient(String host,int numSocket){
        this.host=host;
        this.numSocket=numSocket;
    }

    public static void main(String[] args) throws IOException{
        if (args[0]=="-h" || args[0]==null){
            System.err.println("err");
        } else {
            EchoClient echoClient = new EchoClient(args[0],Integer.parseInt(args[1]));
            echoClient.run();
        }
        
    }
    
    public void run() throws IOException{
        try {
            System.out.println("Connexion au serveur "+host+" au port "+numSocket+"...");
            Socket socketClient = new Socket(host, numSocket);    //on fait la connexion
            
            BufferedReader in2=new BufferedReader(new InputStreamReader(System.in));    //entrées claviers
            PrintWriter writer = new PrintWriter(socketClient.getOutputStream(), true);
            String s=in2.readLine();
            
            while (!s.equals("quit")){
                  
            }
            socketClient.close();
        }
        catch (IOException e){
            System.out.println(e);
        } 
    }

    public void help() {
        System.out.println("Veuillez entrer en paramètre le port sur lequel vous voulez vous connecter.");
    }
}  