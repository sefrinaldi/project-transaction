package com.sefrinaldi.transactionservice.service;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

@Service
public class CodeGeneratorService {

    public static final int MAX_LENGHT = 30;

    public String generateTransactionNumber(String prefix) {
        String invoice = "INV/" + prefix;
        return prefix + this.getRandomAlphaNumeric(invoice);
    }

    public String getRandomAlphaNumeric(String prefix) {
        return this.getRandomAlphaNumeric(prefix, MAX_LENGHT);
    }

    public String getRandomAlphaNumeric(String prefix, int length) {
        int randomLength = length - prefix.length();
        return (new RandomString(randomLength)).nextString();
    }
}
