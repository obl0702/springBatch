package com.ongbl.springbatch.job;

import com.ongbl.springbatch.db.entity.Parent;
import com.ongbl.springbatch.db.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParentProcessor implements ItemProcessor<Parent, Parent> {

    private final ParentRepository parentRepository;

    @Override
    public Parent process(Parent item) {
        log.info("item: " + item.toString());

        item.setStatus("COMPLETED");
        parentRepository.save(item);
        return item;
    }
}
