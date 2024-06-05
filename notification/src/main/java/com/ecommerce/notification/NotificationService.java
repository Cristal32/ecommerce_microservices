package com.ecommerce.notification;

import java.util.List;

public interface NotificationService {
	public Notification getNotification(Long id);
    public List<Notification> getAllNotifications();
    public List<Notification> getNotificationsByClientId(Long clientId);
    public void send(NotificationRequest notificationRequest);
}
