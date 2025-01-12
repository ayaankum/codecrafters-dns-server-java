package model;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class DNSQuestion {

    public ByteBuffer writeQuestion(ByteBuffer buffer) {
        buffer.put(encodeDomainName("codecrafters.io"));
        buffer.putShort((short)1); // Type = A
        buffer.putShort((short)1); // Class = IN
        return buffer;
    }
  
    private byte[] encodeDomainName(String domainName) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (String label : domainName.split("\\.")) {
            out.write(label.length());
            out.writeBytes(label.getBytes());
        }
        out.write(0);
        return out.toByteArray();
    }
}