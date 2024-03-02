package com.ongbl.springbatch;

import com.ongbl.springbatch.service.ParentService;
import com.ongbl.springbatch.service.ParentServiceV2;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class DefaultBatchConfig {

    @MockBean
    private ParentService parentService;

    @MockBean
    private ParentServiceV2 parentServiceV2;

}
