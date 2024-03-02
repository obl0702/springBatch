package com.ongbl.springbatch.job;

import com.ongbl.springbatch.db.entity.Parent;
import com.ongbl.springbatch.db.repository.ParentRepository;
import com.ongbl.springbatch.service.ParentService;
import com.ongbl.springbatch.service.ParentServiceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParentProcessor implements ItemProcessor<Parent, Parent> {

    private final ParentRepository parentRepository;
    private final ParentService parentService;
    private final ParentServiceV2 parentServiceV2;

    @Override
    public Parent process(Parent item) {
        log.info("item: " + item.toString());
        item.setStatus("COMPLETED");

        int result1 = parentService.doSomething();
        int result2 = parentServiceV2.doSomething();

        log.info("result1: {}, result2: {}", result1, result2);

        parentRepository.save(item);
        return item;
    }
}
