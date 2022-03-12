import java.net.*;
import java.io.*;

public class EchoServer {
    private int socket;
    public EchoServer(int socket){
        this.socket=socket;
    }

    public static void main(String[] args) throws IOException {
        EchoServer echoServ = new EchoServer(Integer.parseInt(args[0]));
        echoServ.run();

    }

    public void run() throws IOException{
        try {
            ServerSocket echoServ = new ServerSocket(socket); // on crée le serveur qu'on ne fermera pas
            System.out.println("Serveur lancé sur le port : "+ socket);
            System.out.println("Ecoute...");
            while (true){
                Socket socketClient = echoServ.accept();
                System.out.println("Connexion : "+socketClient.getInetAddress());
                
                //lecture
                BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));//on transforme la donnée en reader
                OutputStream output = socketClient.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                String texte="";
                while (!texte.equals("quit")){
                    texte = reader.readLine();//on en obtient le string
                    writer.println(texte.toUpperCase());
                }
                echoServ.close();
            }

        } catch(Exception e){
            System.err.println(e);
        }
    }
}