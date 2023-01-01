package bgu.spl.net.impl.stomp.Backend.Frames;

public class HeaderLine{
    public final String headerName;
    public final String headerValue;

    public HeaderLine(String key, String value) {
        this.headerName = key;
        this.headerValue = value;
    }
}