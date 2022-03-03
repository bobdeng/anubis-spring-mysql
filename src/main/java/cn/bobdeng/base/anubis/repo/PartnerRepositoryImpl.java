package cn.bobdeng.base.anubis.repo;

import cn.bobdeng.anubis.Partner;
import cn.bobdeng.anubis.PartnerCode;
import cn.bobdeng.anubis.PartnerRepository;
import cn.bobdeng.anubis.Partners;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerRepositoryImpl implements PartnerRepository {
    private final PartnerDAO partnerDAO;

    public PartnerRepositoryImpl(PartnerDAO partnerDAO) {
        this.partnerDAO = partnerDAO;
        Partners.partnerRepository = this;
    }

    @Override
    public void save(Partners partners, Partner partner) {

    }

    @Override
    public Optional<Partner> findByCode(Partners partners, PartnerCode code) {
        return Optional.empty();
    }
}
