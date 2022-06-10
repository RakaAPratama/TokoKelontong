package com.toko.kelontong.services;

import com.toko.kelontong.dtos.history.HistoryHeaderDto;
import com.toko.kelontong.dtos.history.HistoryUpdateInsertDto;
import com.toko.kelontong.models.Customer;
import com.toko.kelontong.models.History;
import com.toko.kelontong.repositories.CustomerRepository;
import com.toko.kelontong.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class HistoryService {

    private HistoryRepository historyRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository, CustomerRepository customerRepository) {
        this.historyRepository = historyRepository;
        this.customerRepository = customerRepository;
    }

    public List<HistoryHeaderDto> findAllHistory() {
        return HistoryHeaderDto.toListStream(historyRepository.findAll());
    }

    public List<HistoryHeaderDto> insertHistory(HistoryUpdateInsertDto newHistory) {
        Customer customer = customerRepository.findById(newHistory.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        Stream.of(newHistory).forEach(field -> {
            if (field != null) {
                historyRepository.save(newHistory.insertHistory(customer));
            }
        });
        return findAllHistory();
    }

    public HistoryHeaderDto updateHistory(HistoryUpdateInsertDto updateHistory, Long id) {
        History history = historyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("History not found"));
        Customer customer = customerRepository.findById(updateHistory.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Stream.of(updateHistory).forEach(field -> {
            if (field != null) {
                field.setValue(history, customer);
                historyRepository.save(history);
            }
        });
        return HistoryHeaderDto.setHistoryHeader(history);
    }

    public HistoryHeaderDto deleteHistory(Long id) {
        History history = historyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("History not found"));
        historyRepository.delete(history);
        return HistoryHeaderDto.setHistoryHeader(history);
    }
}
