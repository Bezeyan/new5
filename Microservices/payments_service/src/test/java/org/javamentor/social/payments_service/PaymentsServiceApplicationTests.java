package org.javamentor.social.payments_service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.javamentor.social.payments_service.Controller.PaymentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentsServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PaymentController paymentController;

    @Test
    void contextLoads() {
        assertThat(paymentController).isNotNull();
    }

    @Test
    public void incorrectCurrency() throws Exception {
        this.mockMvc.perform(
                        post("/payment_service/pay")
                                .param("subtotal", "10.0")
                                .param("shipping", "10.0")
                                .param("tax", "10.0")
                                .param("total", "50.0")
                )
                .andDo(print());
    }

    @Test
    public void testOfPay() throws Exception {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
            Page page = browser.newPage();
            page.navigate("http://localhost:8061/payment_service/get");
            page.fill("#subtotal", "1");
            page.fill("#shipping", "1");
            page.fill("#tax", "1");
            page.fill("#total", "3");
            page.click("#submit");
            page.waitForTimeout(5000);
            page.fill("#password", "123456789");
            while (!page.content().contains("btnLogin")){
                page.waitForTimeout(100);
            }
            page.click("#btnLogin");
            while (!page.content().contains("Остаток на счете PayPal")){
                page.waitForTimeout(100);
            }
            page.click("#payment-submit-btn");
            page.waitForLoadState();
            page.click("#execute");
            page.waitForLoadState();

            if (!page.content().contains("Perfect")){
                throw new Exception();
            }
        }
    }
}
