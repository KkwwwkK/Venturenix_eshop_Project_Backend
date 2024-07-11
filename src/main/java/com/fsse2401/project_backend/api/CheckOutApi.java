package com.fsse2401.project_backend.api;

import com.fsse2401.project_backend.config.EnvConfig;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import com.fsse2401.project_backend.repository.ProductRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin({
        EnvConfig.DEV_BASE_URL,
        EnvConfig.PROD_BASE_URL,
        EnvConfig.PROD_S3_BASE_URL
})
public class CheckOutApi {
    private final ProductRepository productRepository;

    @Autowired
    public CheckOutApi (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @PostMapping("/v1/products")
    public void createProduct() throws StripeException{


        Stripe.apiKey = "sk_test_51PBpckBLB2CmbGe0fNrazNx1RXeIuzzr2PuW1mMYYfyBZ8Ea5VRgqSDDFifOJxskqxR7pKDE7aQirmJ12O4vU3Ly0093jnmOZS";

        for(ProductEntity productEntity: productRepository.getAllBy()){
            // Convert BigDecimal to cents (long)

            ProductCreateParams params =
                    ProductCreateParams.builder()
                            .setId(String.valueOf(productEntity.getPid()))
                            .setDescription(productEntity.getDescription())
                            .setName(productEntity.getName())
                            .addImage(productEntity.getImageUrl())
                            .build();

            Product product = Product.create(params);
        }

    }

    @PostMapping("/v1/prices")
    public void updateProductPrices() throws StripeException {
        Stripe.apiKey = "sk_test_51PBpckBLB2CmbGe0fNrazNx1RXeIuzzr2PuW1mMYYfyBZ8Ea5VRgqSDDFifOJxskqxR7pKDE7aQirmJ12O4vU3Ly0093jnmOZS";

        // Iterate through the product entities and update their corresponding product prices
        for (ProductEntity productEntity : productRepository.getAllBy()) {
            long unitAmount = productEntity.getPrice().multiply(new BigDecimal("100")).longValue();

            PriceCreateParams params = PriceCreateParams.builder()
                    .setCurrency("hkd")
                    .setUnitAmount(unitAmount) // Set the unit amount based on the productEntity.getPrice()
                    .setRecurring(
                            PriceCreateParams.Recurring.builder()
                                    .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                    .build()
                    )
                    .setProduct(productEntity.getPid().toString()) // Associate the price with the product ID
                    .build();

            Price price = Price.create(params);

            productEntity.setStripe_price_id(price.getId());
            productRepository.save(productEntity);

        }

    }

    @PostMapping("/public/checkout")
    public String checkout() throws StripeException {
        Stripe.apiKey = "sk_test_51PBpckBLB2CmbGe0fNrazNx1RXeIuzzr2PuW1mMYYfyBZ8Ea5VRgqSDDFifOJxskqxR7pKDE7aQirmJ12O4vU3Ly0093jnmOZS";

        String YOUR_DOMAIN = "http://localhost:5173";
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(YOUR_DOMAIN + "/thankyou")
                        .setCancelUrl(YOUR_DOMAIN + "/error")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                                        .setPrice("price_1PBqEOBLB2CmbGe0VeEYpNNV")
                                        .build())
                        .build();
        Session session = Session.create(params);
        // for secure guarantee of payment
//        session.setSuccessUrl(YOUR_DOMAIN + "/thankyou?session_id=" + session.getId());
//        System.out.println(session.getId());
//        System.out.println(session.getUrl());
        return session.getUrl();
    }
}



//    @PostMapping("/public/checkout")
//    public void checkout() throws StripeException {
//        Stripe.apiKey = "sk_test_51PBpckBLB2CmbGe0fNrazNx1RXeIuzzr2PuW1mMYYfyBZ8Ea5VRgqSDDFifOJxskqxR7pKDE7aQirmJ12O4vU3Ly0093jnmOZS";
//
//        String YOUR_DOMAIN = "http://localhost:5173";
//        SessionCreateParams params =
//        SessionCreateParams.builder()
//                .setMode(SessionCreateParams.Mode.PAYMENT)
//                .setSuccessUrl(YOUR_DOMAIN + "/thankyou")
//                .setCancelUrl(YOUR_DOMAIN + "/error")
//                .addLineItem(
//                        SessionCreateParams.LineItem.builder()
//                                .setQuantity(1L)
//                                // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
//                                .setPrice("price_1PBqEOBLB2CmbGe0VeEYpNNV")
//                                .build())
//                .build();
//        Session session = Session.create(params);
//        System.out.println(session.getId());
//        System.out.println(session.getUrl());
//    }