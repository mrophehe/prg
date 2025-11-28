//Client:

package pgm7;
import java.io.*;
import java.net.*;

public class TCPC {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 4000);

        System.out.println("Enter the filename:");
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println(kb.readLine());

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line;
        while ((line = in.readLine()) != null)
            System.out.println(line);

        kb.close();
        in.close();
        out.close();
        s.close();
    }
}



//Server:

package pgm7;
import java.io.*;
import java.net.*;

public class TCPS {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(4000);
        System.out.println("Server ready...");
        Socket s = ss.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader fr = new BufferedReader(new FileReader(br.readLine()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        String line;
        while ((line = fr.readLine()) != null)
            out.println(line);

        fr.close();
        br.close();
        out.close();
        s.close();
        ss.close();
    }
}
