import java.net.*;
import java.io.*;

public class DateClient {
    private int numSocket;
    private String host;

    public DateClient(String host,int numSocket){
        this.host=host;
        this.numSocket=numSocket;
    }

    public static void main(String[] args) throws IOException{
        if (args[0]=="-h" || args[0]==null){
            System.err.println("err");
        } else {
            DateClient dateClient = new DateClient(args[0],Integer.parseInt(args[1]));
            dateClient.run();
        }
        
    }
    
    public void run() throws IOException{
        try {
            System.out.println("Connexion au serveur "+host+" au port "+numSocket+"...");
            Socket socket = new Socket(host, numSocket);    //on fait la connexion
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //wraps
            System.out.println(reader.readLine());  //on écrit l'entrée reçu
            socket.close(); //on se déconnecte

        }
        catch (IOException e){
            System.out.println(e);
        } 
    }

    public void help() {
        System.out.println("Veuillez entrer en paramètre le port sur lequel vous voulez vous connecter.");
    }
}  