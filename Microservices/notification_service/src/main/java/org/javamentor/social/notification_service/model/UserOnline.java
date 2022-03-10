package org.javamentor.social.notification_service.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Calendar;


@Data
@NoArgsConstructor
public class UserOnline {
    private String login;
    private String connectTime;

    public UserOnline(String login) {
        this.login = login;
        connectTime = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(Calendar.getInstance().getTime());
    }
}
