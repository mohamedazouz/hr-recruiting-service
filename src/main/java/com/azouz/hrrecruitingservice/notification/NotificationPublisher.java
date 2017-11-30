package com.azouz.hrrecruitingservice.notification;

import java.util.List;

/**
 * @author mazouz
 */
public class NotificationPublisher {

  final List<Notification> notifications;

  public NotificationPublisher(final List<Notification> notifications) {
    this.notifications = notifications;
  }

  public void publishCandidateStatusChange(final NotificationMessage msg) {
    notifications.forEach(notification -> notification.send(msg));
  }
}
