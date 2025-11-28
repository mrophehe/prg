package cnlab;
import java.util.*;

class Crc {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter message bits: ");
String msg = sc.nextLine();
System.out.print("Enter generator: ");
String gen = sc.nextLine();
int[] data = new int[msg.length() + gen.length() - 1];
int[] div = new int[gen.length()];
for (int i = 0; i < msg.length(); i++)
data[i] = msg.charAt(i) - '0';
for (int i = 0; i < gen.length(); i++)
div[i] = gen.charAt(i) - '0';
for (int i = 0; i < msg.length(); i++)
if (data[i] == 1)
for (int j = 0; j < gen.length(); j++)
data[i + j] ^= div[j];
System.out.print("The checksum code is: ");
for (int i = 0; i < msg.length(); i++)
data[i] = msg.charAt(i) - '0';
for (int d : data) System.out.print(d);
System.out.println();
System.out.print("Enter checksum code: ");
msg = sc.nextLine();
System.out.print("Enter generator: ");
gen = sc.nextLine();
data = new int[msg.length() + gen.length() - 1];
div = new int[gen.length()];
for (int i = 0; i < msg.length(); i++)
data[i] = msg.charAt(i) - '0';
for (int i = 0; i < gen.length(); i++)
div[i] = gen.charAt(i) - '0';
for (int i = 0; i < msg.length(); i++)
if (data[i] == 1)
for (int j = 0; j < gen.length(); j++)
data[i + j] ^= div[j];
boolean ok = true;
for (int d : data)
if (d == 1) ok = false;
System.out.println(ok ? "Data stream is valid" : "Data stream is invalid. CRC error occurred.");
}
}
