package com.ecommerce.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notification")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;

	// ============================= GET mapping =============================	
	@GetMapping("/getAll")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        System.out.println("Retrieving all notifications");
        return new ResponseEntity<>( notificationService.getAllNotifications(), HttpStatus.OK );
    }
	
	@GetMapping("getById/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable("id") Long id) {
        System.out.println("Retrieving notification with id {}" + id);
        return new ResponseEntity<>(notificationService.getNotification(id), HttpStatus.OK);
    }
	
	@GetMapping("getByClientId/{clientId}")
    public ResponseEntity<List<Notification>> getNotificationByClientId(@PathVariable("clientId") Long clientId) {
        System.out.println("Retrieving notification with id {}" + clientId);
        return new ResponseEntity<>(notificationService.getNotificationsByClientId(clientId), HttpStatus.OK);
    }

	// ============================= POST mapping =============================	
    @PostMapping("/send")
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        System.out.println("Sending new notification {}" + notificationRequest);
        notificationService.send(notificationRequest);
    }
}
