package com.sefrinaldi.transactionservice.client.product;

import com.sefrinaldi.transactionservice.client.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ProductRestClient {

    @Value("${product-service.get-product}")
    private String getProductEndPoint;

    @Autowired
    @Qualifier("serviceRestTemplate")
    private RestTemplate restTemplate;

    public ProductDto getProductByCode(String code) {
        log.info("Before call product service {} ", code);
        String url = getProductEndPoint + code;
        ResponseEntity<ProductDto> response =
                restTemplate
                        .getForEntity(
                                url,
                                ProductDto.class
                        );
        log.info("After call product service {} ", response);
        return response.getBody();
    }
}
