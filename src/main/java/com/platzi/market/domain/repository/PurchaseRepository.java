package com.platzi.market.domain.repository;

import com.platzi.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();

    /*El Optional nos permite manipular esa lista en el caso de que no exista compra por cliente*/
    Optional<List<Purchase>> getByClient(String clientId);

    Purchase save(Purchase purchase);
}
