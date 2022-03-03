package cn.bobdeng.base.anubis.repo;

import cn.bobdeng.anubis.Partner;
import cn.bobdeng.anubis.PartnerKey;
import cn.bobdeng.anubis.PartnerKeyRepository;
import cn.bobdeng.anubis.Partners;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PartnerKeyRepositoryImpl implements PartnerKeyRepository {
    private final PartnerKeyDAO partnerKeyDAO;

    public PartnerKeyRepositoryImpl(PartnerKeyDAO partnerKeyDAO) {
        this.partnerKeyDAO = partnerKeyDAO;
        Partners.partnerKeyRepository = this;
    }

    @Override
    public void save(Partner partner, PartnerKey partnerKey) {
        partnerKeyDAO.save(PartnerKeyDO.builder()
                .expireAt(partnerKey.expireAt())
                .partnerId(partner.id())
                .key(partnerKey.key())
                .build());
    }

    @Override
    public Stream<PartnerKey> findKeys(Partner partner) {
        return partnerKeyDAO.findByPartnerId(partner.id())
                .map(PartnerKeyDO::toEntity);
    }

    @Override
    public void save(PartnerKey partnerKey) {
        partnerKeyDAO.findById(partnerKey.id())
                .ifPresent(partnerKeyDO -> {
                    partnerKeyDO.setExpireAt(partnerKey.expireAt());
                    partnerKeyDAO.save(partnerKeyDO);
                });
    }
}
