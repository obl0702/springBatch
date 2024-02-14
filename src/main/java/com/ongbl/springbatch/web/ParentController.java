package com.ongbl.springbatch.web;

import com.ongbl.springbatch.db.entity.Parent;
import com.ongbl.springbatch.service.ParentChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentChildService parentChildService;

    @GetMapping
    public List<Parent> getAllParents() {
        return parentChildService.getAllParents();
    }
}
