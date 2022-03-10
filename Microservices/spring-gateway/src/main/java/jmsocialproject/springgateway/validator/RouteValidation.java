package jmsocialproject.springgateway.validator;

public interface RouteValidation {
    boolean isOpenApi(String url);

    boolean checkMapping(String roleName, String url);
}
