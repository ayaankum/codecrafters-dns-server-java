package model;

import java.nio.ByteBuffer;

public class DNSHeader {
    private short id = 1234;
    private short flags = (short)0b10000000_00000000;
    private short qdcount = 1;
    private short ancount;
    private short nscount;
    private short arcount;

    public ByteBuffer writeHeader(ByteBuffer buffer) {
        buffer.putShort(id);
        buffer.putShort(flags);
        buffer.putShort(qdcount);
        buffer.putShort(ancount);
        buffer.putShort(nscount);
        buffer.putShort(arcount);
        return buffer;
    }
}