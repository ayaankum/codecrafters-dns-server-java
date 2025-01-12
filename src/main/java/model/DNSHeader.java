package model;
public class DNSHeader {
  private int id;
  private int flags;
  private int qdCount;
  private int anCount;
  private int nsCount;
  private int arCount;
  public DNSHeader() {}
  private DNSHeader(int id, int flags, int qdCount, int anCount, int nsCount,
                    int arCount) {
    this.id = id;
    this.flags = flags;
    this.qdCount = qdCount;
    this.anCount = anCount;
    this.nsCount = nsCount;
    this.arCount = arCount;
  }
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }
  public int getFlags() { return flags; }
  public void setFlags(int flags) { this.flags = flags; }
  public int getQdCount() { return qdCount; }
  public void setQdCount(int qdCount) { this.qdCount = qdCount; }
  public int getAnCount() { return anCount; }
  public void setAnCount(int anCount) { this.anCount = anCount; }
  public int getNsCount() { return nsCount; }
  public void setNsCount(int nsCount) { this.nsCount = nsCount; }
  public int getArCount() { return arCount; }
  public void setArCount(int arCount) { this.arCount = arCount; }
  // Flag manipulation methods
  public boolean isQuery() { return (flags & 0x8000) == 0; }
  public boolean isAuthoritativeAnswer() { return (flags & 0x0400) != 0; }
  public void setAuthoritativeAnswer(boolean aa) {
    if (aa) {
      this.flags |= 0x0400;
    } else {
      this.flags &= ~0x0400;
    }
  }
  public boolean isTruncated() { return (flags & 0x0200) != 0; }
  public void setTruncated(boolean tc) {
    if (tc) {
      this.flags |= 0x0200;
    } else {
      this.flags &= ~0x0200;
    }
  }
  public boolean isRecursionDesired() { return (flags & 0x0100) != 0; }
  public void setRecursionDesired(boolean rd) {
    if (rd) {
      this.flags |= 0x0100;
    } else {
      this.flags &= ~0x0100;
    }
  }
  public boolean isRecursionAvailable() { return (flags & 0x0080) != 0; }
  public void setRecursionAvailable(boolean ra) {
    if (ra) {
      this.flags |= 0x0080;
    } else {
      this.flags &= ~0x0080;
    }
  }
  public int getOpcode() { return (flags >> 11) & 0xF; }
  public void setOpcode(int opcode) {
    this.flags = (this.flags & 0x87FF) | ((opcode & 0xF) << 11);
  }
  public int getRcode() { return flags & 0xF; }
  public void setRcode(int rcode) {
    this.flags = (this.flags & 0xFFF0) | (rcode & 0xF);
  }
  public void setQuery() {
    this.flags &= ~0x8000; // Ensure QR bit is 0
  }
  public void setReply() {
    this.flags |= 0x8000; // Ensure QR bit is 1
  }
  public byte[] toByteArray() {
    byte[] data = new byte[12];
    data[0] = (byte)(id >> 8);
    data[1] = (byte)(id & 0xFF);
    data[2] = (byte)(flags >> 8);
    data[3] = (byte)(flags & 0xFF);
    data[4] = (byte)(qdCount >> 8);
    data[5] = (byte)(qdCount & 0xFF);
    data[6] = (byte)(anCount >> 8);
    data[7] = (byte)(anCount & 0xFF);
    data[8] = (byte)(nsCount >> 8);
    data[9] = (byte)(nsCount & 0xFF);
    data[10] = (byte)(arCount >> 8);
    data[11] = (byte)(arCount & 0xFF);
    return data;
  }
  public static DNSHeader fromByteArray(byte[] data) {
    if (data.length < 12) {
      throw new IllegalArgumentException("Invalid DNS header length");
    }
    int id = (data[0] << 8) | (data[1] & 0xFF);
    int flags = (data[2] << 8) | (data[3] & 0xFF);
    int qdCount = (data[4] << 8) | (data[5] & 0xFF);
    int anCount = (data[6] << 8) | (data[7] & 0xFF);
    int nsCount = (data[8] << 8) | (data[9] & 0xFF);
    int arCount = (data[10] << 8) | (data[11] & 0xFF);
    return new DNSHeader(id, flags, qdCount, anCount, nsCount, arCount);
  }
}