package com.example.aws.elasticsearch.demo.controller;

import com.example.aws.elasticsearch.demo.document.DocumentSearch;
import com.example.aws.elasticsearch.demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class SearchController {

    private SearchService service;

    @Autowired
    public SearchController(SearchService service) {

        this.service = service;
    }

    @GetMapping("/test")
    public String test() {

        return "Success";
    }

    @GetMapping(value = "/api/v1/planname/name-search")
    public List<DocumentSearch> searchByPlanName(@RequestParam(value = "name") String name) throws Exception {
        return service.searchByPlanName(name);
    }
}
