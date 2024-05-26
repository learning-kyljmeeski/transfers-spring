package com.dreamers.transfers.controller;

import com.dreamers.transfers.model.response.TransferResponse;
import com.dreamers.transfers.model.response.TransfersResponse;
import com.dreamers.transfers.service.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {
    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @GetMapping
    public TransfersResponse getAllTransfers() {
        return service.getAllTransfers();
    }

    @GetMapping("/{id}")
    public TransferResponse getTransferById(@PathVariable String id) {
        return service.getTransferById(id);
    }

    @GetMapping("/filter")
    public TransfersResponse getTransfersBySearchTerm(@RequestParam("search-term") String searchTerm) {
        return service.getTransfersBySearchTerm(searchTerm);
    }
}
