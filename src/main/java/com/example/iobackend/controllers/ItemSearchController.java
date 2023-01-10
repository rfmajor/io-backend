package com.example.iobackend.controllers;

import com.example.iobackend.dto.ItemInquiryDto;
import com.example.iobackend.dto.ItemScrapingResult;
import com.example.iobackend.service.ItemSearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/search")
public class ItemSearchController {
    private final ItemSearchService itemSearchService;

    @PostMapping
    public ResponseEntity<List<ItemScrapingResult>> getItemInquiryResults(@RequestBody ItemInquiryDto inquiry) {
        return ResponseEntity.ok(itemSearchService.findItems(inquiry));
    }
}
