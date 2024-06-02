package com.ecommerce.notification;

import java.util.List;

public interface NotificationService {
	public Notification getNotification(Long id);
    public List<Notification> getAllNotification();
    public void send(NotificationRequest notificationRequest);
}
