package com.azouz.hrrecruitingservice.notification;

import java.text.MessageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mazouz
 */
public class LoggerNotification implements Notification {

  private static final Logger LOG = LoggerFactory.getLogger(LoggerNotification.class);

  public LoggerNotification() {
  }

  @Override
  public void send(final NotificationMessage msg) {
    LOG.info(MessageFormat
        .format("User: {0} changed status to: {1} for offer: {2}", msg.getUserEmail(),
            msg.getStatus(), msg.getOfferName()));
  }
}
