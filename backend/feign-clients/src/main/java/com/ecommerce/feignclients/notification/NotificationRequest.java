package com.ecommerce.feignclients.notification;

public class NotificationRequest {
    private Long clientId;
    private String clientEmail;
    private String sender;
    private String msg;

    // Default constructor
    public NotificationRequest() {
    }

    // All-args constructor
    public NotificationRequest(Long clientId, String clientEmail, String sender, String msg) {
        this.clientId = clientId;
        this.clientEmail = clientEmail;
        this.sender = sender;
        this.msg = msg;
    }

    // Getters and setters
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // toString method
    @Override
    public String toString() {
        return "NotificationRequest{" +
                "clientId=" + clientId +
                ", clientEmail='" + clientEmail + '\'' +
                ", sender='" + sender + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    // Builder pattern implementation
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long clientId;
        private String clientEmail;
        private String sender;
        private String msg;

        public Builder clientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder clientEmail(String clientEmail) {
            this.clientEmail = clientEmail;
            return this;
        }

        public Builder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public NotificationRequest build() {
            return new NotificationRequest(clientId, clientEmail, sender, msg);
        }
    }
}
