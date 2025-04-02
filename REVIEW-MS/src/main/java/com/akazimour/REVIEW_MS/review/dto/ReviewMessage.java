package com.akazimour.REVIEW_MS.review.dto;

public class ReviewMessage {

    private long messageId;
    private String title;

    public ReviewMessage() {
    }

    public ReviewMessage(long messageId, String event) {
        this.messageId = messageId;
        this.title = event;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ReviewMessage{" +
                "messageId=" + messageId +
                ", event='" + title + '\'' +
                '}';
    }
}
