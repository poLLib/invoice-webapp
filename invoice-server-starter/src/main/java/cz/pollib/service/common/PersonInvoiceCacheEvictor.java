package cz.pollib.service.common;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

@Component
public class PersonInvoiceCacheEvictor {

    @Caching(
            evict = {
                    @CacheEvict(
                            value = "get-seller-invoices",
                            key = "'seller=' + #identificationNumber"
                    ),
                    @CacheEvict(
                            value = "get-buyer-invoices",
                            key = "'buyer=' + #identificationNumber"
                    )
            }
    )
    public void evictPersonInvoiceCache(String identificationNumber) {}
}
