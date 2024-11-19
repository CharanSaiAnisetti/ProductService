package com.Charan.ProductServiceEcom.Controllers;

import com.Charan.ProductServiceEcom.Services.PaymentService;
import com.Charan.ProductServiceEcom.dtos.GeneratePaymentLinkRequestDto;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

   private PaymentService paymentService;

   public PaymentController(PaymentService paymentService) {
       this.paymentService = paymentService;
   }

    @PostMapping
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDto request) throws StripeException {
        return paymentService.generatePaymentLink();
    }

    @PostMapping("/webhook")
   public void handleWebhookEvent(){
        System.out.println("Webhook Event is triggered successfully");
   }
}
