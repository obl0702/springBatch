package com.ongbl.springbatch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ParentService {

    public int doSomething(){
        log.info("Do Something");
        return 1;
    }
}
