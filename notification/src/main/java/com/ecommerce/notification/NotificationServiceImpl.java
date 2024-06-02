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
    public List<Notification> getAllNotification() {
        return notificationDao.findAll();
    }
	
    // ---------------------------- get a notification by its id ----------------------------
    @Override
    public Notification getNotification(Long id) {
    	return notificationDao.findById(id).orElseThrow(() ->
                new IllegalStateException("Notification not found"));
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
