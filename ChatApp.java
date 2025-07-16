import java.util.Scanner;

public class ChatApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HuffmanCoding hc = new HuffmanCoding();

        System.out.print("Enter message to send: ");
        String message = sc.nextLine();

        // Sender side
        String encoded = hc.encode(message);
        System.out.println("\n📤 Sent (Compressed): " + encoded);

        // Receiver side
        String decoded = hc.decode(encoded);
        System.out.println("📥 Received (Decompressed): " + decoded);

        sc.close();
    }
}

