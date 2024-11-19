package com.Charan.ProductServiceEcom.Services;

import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;


public interface PaymentService {

   public String generatePaymentLink() throws StripeException;
}
