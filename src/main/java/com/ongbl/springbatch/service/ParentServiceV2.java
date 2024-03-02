package com.ongbl.springbatch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ParentServiceV2 {

    public int doSomething(){
        log.info("Do Something 2");
        return 2;
    }
}
