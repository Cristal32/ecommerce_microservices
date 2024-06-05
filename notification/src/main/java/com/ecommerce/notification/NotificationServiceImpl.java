package com.ecommerce.notification;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private NotificationDao notificationDao;

	// ---------------------------- get all notifications ----------------------------
    @Override
    public List<Notification> getAllNotifications() {
        return notificationDao.findAll();
    }
	
    // ---------------------------- get a notification by its id ----------------------------
    @Override
    public Notification getNotification(Long id) {
    	return notificationDao.findById(id).orElseThrow(() ->
                new IllegalStateException("Notification not found"));
    }
    
 // ---------------------------- get a notification by its client id ----------------------------
    @Override
    public List<Notification> getNotificationsByClientId(Long clientId) {
    	return notificationDao.findByClientId(clientId).orElseThrow(() ->
                new IllegalStateException("Notifications not found for client " + clientId));
    }

 // ---------------------------- send a notification ----------------------------
    @Override
    public void send(NotificationRequest notificationRequest) {
    	Notification notification = Notification.builder()
    			.clientId(notificationRequest.getClientId())
                .clientEmail(notificationRequest.getClientEmail())
                .sender("ecommerce")
                .msg(notificationRequest.getMsg())
                .sentAt(LocalDateTime.now())
                .build();
        notificationDao.saveAndFlush(notification);
    }
}
