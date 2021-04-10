package pl.lukakan.contactform;


public class Message {

    private final String body;
    private final String title;
    private final String replyTo;
    private final String sendTo;
    private final String sendFrom;

    public Message(String title, String body, String sendTo, String sendFrom, String replyTo) {
        this.sendTo = sendTo;
        this.body = body;
        this.title = title;
        this.replyTo = replyTo;
        this.sendFrom = sendFrom;
    }


    public String getSendTo() {
        return sendTo;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public String getSendFrom() {
        return sendFrom;
    }
}
