package cn.bobdeng.base.anubis.repo;

import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface PartnerKeyDAO extends CrudRepository<PartnerKeyDO, Integer> {
    Stream<PartnerKeyDO> findByPartnerId(int partnerId);
}
