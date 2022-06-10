package com.toko.kelontong.services;

import com.toko.kelontong.dtos.supplier.SupplierHeaderDto;
import com.toko.kelontong.dtos.supplier.SupplierUpdateInsertDto;
import com.toko.kelontong.models.Supplier;
import com.toko.kelontong.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SupplierService {

    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierHeaderDto> findAllSupplier() {
        return SupplierHeaderDto.toListStream(supplierRepository.findAll());
    }

    public SupplierHeaderDto insertSupplier(SupplierUpdateInsertDto newSupplier) {
        Supplier supplier = newSupplier.insertSupplier();
        supplierRepository.save(supplier);
        return SupplierHeaderDto.setSupplierHeader(supplier);
    }

    public SupplierHeaderDto updateSupplier(SupplierUpdateInsertDto updateSupplier, Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

        Stream.of(updateSupplier).forEach(field -> {
            if (field != null) {
                field.setValue(supplier);
                supplierRepository.save(supplier);
            }
        });
        return SupplierHeaderDto.setSupplierHeader(supplier);
    }

    public boolean deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
        return true;
    }

    public Long countSupplier() {
        return supplierRepository.count();
    }
}
