package com.ongbl.springbatch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ParentServiceV2 {

    public int doSomething(){
        log.info("Do Something 2");
        return 2;
    }
}
