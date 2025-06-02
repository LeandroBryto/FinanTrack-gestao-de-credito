package leandrodev.creditapplicationsystem.service;

import leandrodev.creditapplicationsystem.entity.CreditNotification;

public interface NotificationService {
    void send(CreditNotification notification);
}
