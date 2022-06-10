package com.toko.kelontong.repositories;

import com.toko.kelontong.models.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<TransactionDetail, Long> {


}
