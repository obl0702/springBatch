package com.ongbl.springbatch.job2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockService {

    public void send(){
        log.info("send...");
    }

}
