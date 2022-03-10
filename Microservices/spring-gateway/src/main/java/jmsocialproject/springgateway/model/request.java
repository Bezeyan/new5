package jmsocialproject.springgateway.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class request {
    @NotBlank
    private String refreshToken;
}
