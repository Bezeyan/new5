package org.javamentor.social.payments_service.Controller;

import com.paypal.base.rest.PayPalRESTException;
import org.javamentor.social.payments_service.model.OrderDetail;
import org.javamentor.social.payments_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("payment_service")
public class PaymentController {


    PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/get")
    private void testGetMethod(HttpServletResponse response) throws IOException {


        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"pay\" method=\"post\">\n" +
                "<label for=\"subtotal\">Subtotal:</label>\n" +
                " <input id=\"subtotal\" name=\"subtotal\" type=\"text\" >" +
                "<label for=\"shipping\">Shipping:</label>\n" +
                " <input id=\"shipping\" name=\"shipping\" type=\"text\" >" +
                "<label for=\"tax\">Tax:</label>\n" +
                " <input id=\"tax\" name=\"tax\" type=\"text\" >" +
                "<label for=\"total\">Total:</label>\n" +
                " <input id=\"total\" name=\"total\" type=\"text\" >" +
                "    <button id=\"submit\" type=\"submit\">submit</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    @PostMapping("/pay")
    private String payTestProductMethod1(@RequestParam("subtotal") String subtotal,
                                         @RequestParam("shipping") String shipping,
                                         @RequestParam("tax") String tax,
                                         @RequestParam("total") String total) {

        OrderDetail testOrder = new OrderDetail("testProduct", subtotal, shipping, tax, total);

        try {
            String approvalLink = paymentService.authorizePayment(testOrder);
            return "redirect:" + approvalLink;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:" + paymentService.getErrorUrl();

    }

    @GetMapping("/cancel")
    private void cancelPay(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Cancel to pay</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "You don't want to pay" +
                "</body>\n" +
                "</html>");

    }

    @GetMapping("/execute")
    private void testGetExecute(HttpServletResponse response, @RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) throws IOException {


        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"execute\" method=\"post\">\n" +
                " <p>Execute payment?</p>" +
                " <input type=\"hidden\" name=\"paymentId\" type=\"text\" value=\"" + paymentId + "\">" +
                " <input type=\"hidden\" name=\"PayerID\" type=\"text\" value=\"" + payerId + "\">" +
                "    <button id =\"execute\" type=\"submit\">Execute</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    @PostMapping("/execute")
    private String executePayment(@RequestParam("paymentId") String paymentId,
                                  @RequestParam("PayerID") String payerId) {


        try {
            paymentService.executePayment(paymentId, payerId);
            return "redirect:" + paymentService.getEndedUrl();
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:" + paymentService.getErrorUrl();

    }

    @GetMapping("/ended")
    private void endedPayment(HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>payment done</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "Perfect" +
                "</body>\n" +
                "</html>");

    }
}
