//Sender

import java.net.*;
import java.io.*;

public class slidsender {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(4000);
        Socket s = ss.accept();
        DataInputStream cin = new DataInputStream(System.in);
        DataInputStream rin = new DataInputStream(s.getInputStream());
        PrintStream pout = new PrintStream(s.getOutputStream());

        String buff[] = new String[8];
        int ws = 8, sp = 0, nf, ack;
        String ch;

        do {
            System.out.print("Enter no. of frames: ");
            nf = Integer.parseInt(cin.readLine());
            pout.println(nf);

            if (nf <= ws - 1) {
                System.out.println("Enter " + nf + " Messages:");
                for (int i = 0; i < nf; i++) {
                    buff[sp] = cin.readLine();
                    pout.println(buff[sp]);
                    sp = (sp + 1) % 8;
                }
                ws -= nf;
                ack = Integer.parseInt(rin.readLine());
                System.out.println("Acknowledgment received for " + ack + " frames");
                ws += nf;
            } else {
                System.out.println("Frames exceed window size");
                break;
            }

            System.out.print("Send more frames? ");
            ch = cin.readLine();
            pout.println(ch);

        } while (ch.equals("yes"));

        s.close();
        ss.close();
    }
}



//Reciever

import java.net.*;
import java.io.*;

public class slidreceiver {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket(InetAddress.getLocalHost(), 4000);
        DataInputStream rin = new DataInputStream(s.getInputStream());
        PrintStream pout = new PrintStream(s.getOutputStream());

        String buff[] = new String[8];
        int ws = 8, rp = -1, nf;
        String ch;

        do {
            nf = Integer.parseInt(rin.readLine());

            if (nf <= ws - 1) {
                for (int i = 0; i < nf; i++) {
                    rp = (rp + 1) % 8;
                    buff[rp] = rin.readLine();
                    System.out.println("Received Frame " + rp + ": " + buff[rp]);
                }
                ws -= nf;
                System.out.println("\nAcknowledgment sent\n");
                pout.println(rp + 1);
                ws += nf;
            } else break;

            ch = rin.readLine();
        } while (ch.equals("yes"));

        s.close();
    }
}
