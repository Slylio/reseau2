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
        if (args[0]=="-h" || args[0]==null || args.length!=2){
            System.err.println("err");
        } else {
            EchoClient echoClient = new EchoClient(args[0],Integer.parseInt(args[1]));
            echoClient.run();
        }
        
    }
    
    public void run() throws IOException{
        try (
            Socket socketClient = new Socket(host,numSocket);
            PrintWriter out =  new PrintWriter(socketClient.getOutputStream(), true);              
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in))
            
        ){
            String texte;
            while (!(texte = in2.readLine()).equals("quit")) {
                out.println(texte);
                System.out.println(in.readLine());
            }
            out.println("quit");
            socketClient.close();
        }catch (IOException e){
            System.out.println(e);
        } 
        
    }

    public void help() {
        System.out.println("Veuillez entrer en paramètre le hostnameport sur lequel vous voulez vous connecter.");
    }
}  