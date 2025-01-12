import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.nio.ByteBuffer;
import model.DNSHeader;

public class Main {
  public static void main(String[] args){
    try(DatagramSocket serverSocket = new DatagramSocket(2053)) {
      while(true) {
        final byte[] buf = new byte[512];
        final DatagramPacket packet = new DatagramPacket(buf, buf.length);
        serverSocket.receive(packet);
        System.out.println("Received data");
        
        DNSHeader header = DNSHeader.fromByteArray(buf);
        header.setReply();
        header.setQdCount(0);
        header.setAnCount(0);
        header.setNsCount(0);
        header.setArCount(0);
        header.setRecursionDesired(true);
        final var bufResponse =
            ByteBuffer.allocate(512).put(header.toByteArray()).array();

        final DatagramPacket packetResponse = new DatagramPacket(bufResponse, bufResponse.length, packet.getSocketAddress());
        serverSocket.send(packetResponse);
      }
    } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
    }
  }
}

