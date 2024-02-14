package com.ongbl.springbatch.service;

import com.ongbl.springbatch.db.entity.Parent;
import com.ongbl.springbatch.db.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentChildService {
    private final ParentRepository parentRepository;

    public void saveParentWithChildren(Parent parent) {
        // Save the parent (and cascade save the children)
        parentRepository.save(parent);
    }

    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }
}
