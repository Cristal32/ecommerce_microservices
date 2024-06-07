package com.ecommerce.notification;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private String clientEmail;
    private String sender;
    private String msg;
    private LocalDateTime sentAt;
    
    // Constructors
    public Notification() {}
    
    public Notification(Long id, Long clientId, String clientEmail, String sender, String msg, LocalDateTime sentAt) {
    	this.id = id;
        this.clientId = clientId;
        this.clientEmail = clientEmail;
        this.sender = sender;
        this.msg = msg;
        this.sentAt = sentAt;
    }
    
 // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    // toString method
    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", clientEmail='" + clientEmail + '\'' +
                ", sender='" + sender + '\'' +
                ", msg='" + msg + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }

    // Builder pattern for creating instances of Notification
    public static NotificationBuilder builder() {
        return new NotificationBuilder();
    }
    
    public static class NotificationBuilder {
        private Long id;
        private Long clientId;
        private String clientEmail;
        private String sender;
        private String msg;
        private LocalDateTime sentAt;

        public NotificationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public NotificationBuilder clientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public NotificationBuilder clientEmail(String clientEmail) {
            this.clientEmail = clientEmail;
            return this;
        }

        public NotificationBuilder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public NotificationBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public NotificationBuilder sentAt(LocalDateTime sentAt) {
            this.sentAt = sentAt;
            return this;
        }

        public Notification build() {
            return new Notification(id, clientId, clientEmail, sender, msg, sentAt);
        }
    }
}
