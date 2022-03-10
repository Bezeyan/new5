package org.javamentor.social.notification_service.postForUser;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
public class Post {

    Map<Integer, String> updateList;

    public void addList() {
        updateList.put(1, "One");
        updateList.put(2, "Two!");
        updateList.put(3, "Three");
    }
}
