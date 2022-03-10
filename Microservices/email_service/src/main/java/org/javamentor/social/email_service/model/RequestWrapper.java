package org.javamentor.social.email_service.model;

import lombok.Data;
import org.javamentor.social.email_service.model.dto.AccountDto;
import org.javamentor.social.email_service.model.entity.Profile;

@Data
public class RequestWrapper {

    private AccountDto accountDto;

    private Profile profile;

    private int lastDays;
}
