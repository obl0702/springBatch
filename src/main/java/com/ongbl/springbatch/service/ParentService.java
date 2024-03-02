package com.ongbl.springbatch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ParentService {

    public int doSomething(){
        log.info("Do Something");
        return 1;
    }
}
