package cn.bobdeng.base.anubis.repo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PartnerDAO extends CrudRepository<PartnerDO, Integer> {
    Optional<PartnerDO> findByCode(String code);
}
