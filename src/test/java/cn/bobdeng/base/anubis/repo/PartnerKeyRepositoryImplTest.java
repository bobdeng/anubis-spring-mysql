package cn.bobdeng.base.anubis.repo;

import cn.bobdeng.anubis.*;
import cn.bobdeng.base.anubis.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static cn.bobdeng.anubis.Partners.partnerKeyRepository;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class PartnerKeyRepositoryImplTest extends IntegrationTest {
    @Autowired
    PartnerKeyDAO partnerKeyDAO;
    private String key = """
            -----BEGIN PUBLIC KEY-----
            MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEA2DILCOJwTTM45rAw81bn
            wIfzYHNXCgc5C0RFBu4IoWa13Y9JzBis8gtWbbOp/rIUwFmDrmUTPRmORM+1+zjO
            ZGnBlxn28OIh+DuloExe91rLORhON4FNcCIosq7Oa2ntbpXQyRshmvwHGPND6IXP
            A0eHMP+Zs/uY7UsRudP9u2kaLR5Y/eH0hQTbM9lI5P1t2PVSdXvKKhzOyf5aeIqH
            zI8DMQLMx0TZc6r4GLv5p1TMxwnyYoVob68zC/uQ6s2moOKUwfErIcTZdobE/jYS
            0fyNrsPJv3k75qvamt8P4Q/coYEo8UmViRnsMJtAQyz7+1eK+k1W8NFPJHSBegzC
            l66AYOEPlH4RJB8ie0kqziaen6AXJNKI75mvD6InaJ1XLzQlFKYV4KziV+GuWhZL
            jBwfEog+wdQ5smbkL66g0GYq3hwoMNl6PW9h/ohijLOn+Wj2Q8RfroslcUETzpt7
            DEgqrKHfe4zwcsTh/hOMvkQBh4bmfuODiWRZ2C5b5KwRAgMBAAE=
            -----END PUBLIC KEY-----
            """;

    @BeforeEach
    public void setup() {
        partnerKeyDAO.deleteAll();
    }

    @Test
    public void save() {
        Partner partner = new Partner(PartnerId.of(100), null, null);
        AccessKey key = new AccessKey(this.key);
        PartnerKey partnerKey = new PartnerKey(key, ExpireDate.empty());
        partnerKeyRepository.save(partner, partnerKey);

        assertThat(partnerKeyDAO.count(), is(1L));
        PartnerKeyDO partnerKeyDO = partnerKeyDAO.findAll().iterator().next();
        assertThat(partnerKeyDO.getKey(), is(key.getKey()));
        assertThat(partnerKeyDO.getPartnerId(), is(partner.id()));
    }

    @Test
    public void find_partner_keys() {
        Partner partner = new Partner(PartnerId.of(100), null, null);
        partnerKeyDAO.save(PartnerKeyDO.builder()
                .key(key)
                .partnerId(partner.id())
                .build());
        partnerKeyDAO.save(PartnerKeyDO.builder()
                .key(key)
                .partnerId(partner.id() + 1)
                .build());

        List<PartnerKey> keys = partnerKeyRepository.findKeys(partner).collect(Collectors.toList());

        assertThat(keys.size(), is(1));

    }

    @Test
    public void set_expire_at() {
        Partner partner = new Partner(PartnerId.of(100), null, null);
        PartnerKeyDO partnerKeyDO = PartnerKeyDO.builder()
                .key(key)
                .partnerId(partner.id())
                .build();
        partnerKeyDAO.save(partnerKeyDO);

        long expireAt = System.currentTimeMillis();
        PartnerKey partnerKey = partnerKeyDO.toEntity();
        partnerKey.setExpireAt(expireAt);
        partnerKeyRepository.save(partnerKey);

        PartnerKeyDO afterSetExpireAt = partnerKeyDAO.findAll().iterator().next();
        assertThat(afterSetExpireAt.getExpireAt(), is(expireAt));
    }
}