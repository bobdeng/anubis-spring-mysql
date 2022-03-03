package cn.bobdeng.base.anubis.repo;

import cn.bobdeng.anubis.Partner;
import cn.bobdeng.anubis.PartnerCode;
import cn.bobdeng.anubis.PartnerName;
import cn.bobdeng.anubis.Partners;
import cn.bobdeng.base.anubis.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static cn.bobdeng.anubis.Partners.partnerRepository;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class PartnerRepositoryImplTest extends IntegrationTest {
    @Autowired
    PartnerDAO partnerDAO;

    @Test
    public void should_save() {
        Partners partners = new Partners();
        PartnerCode code = new PartnerCode("1000");
        PartnerName name = new PartnerName("合作伙伴1");
        Partner partner = new Partner(code, name);
        partnerRepository.save(partners, partner);

        List<PartnerDO> collection = StreamSupport.stream(partnerDAO.findAll().spliterator(), false).collect(Collectors.toList());
        assertThat(collection.size(), is(1));
        PartnerDO partnerDO = collection.get(0);
        assertThat(partnerDO.getCode(), is(code.getCode()));
        assertThat(partnerDO.getName(), is(name.getName()));
    }

    @Test
    public void find_by_code() {
        PartnerDO partnerDO = partnerDAO.save(PartnerDO.builder()
                .name("合作伙伴1")
                .code("1000")
                .build());
        Partner partner = partnerRepository.findByCode(new Partners(), new PartnerCode("1000")).orElseThrow();
        assertThat(partner.code(), is("1000"));
        assertThat(partner.name(), is("合作伙伴1"));
        assertThat(partner.id(), is(partnerDO.getId()));
    }
}