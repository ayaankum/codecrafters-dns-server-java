package model;

import java.nio.ByteBuffer;

public class DNSMessage {

    public DNSMessage() {}

    public byte[] createMessage() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        DNSHeader header = new DNSHeader();
        header.writeHeader(byteBuffer);
        DNSQuestion question = new DNSQuestion();
        question.writeQuestion(byteBuffer);
        return byteBuffer.array();
    }
}
