package com.demo.service;

import com.demo.utils.response.ExpiredResponse;
import org.springframework.stereotype.Service;

public interface Expired_Service {
    public ExpiredResponse checkExpiredC(String invoice_id);

    public ExpiredResponse checkExpiredR(String invoice_id);
}
