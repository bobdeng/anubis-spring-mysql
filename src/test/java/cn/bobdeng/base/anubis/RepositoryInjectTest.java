package cn.bobdeng.base.anubis;

import cn.bobdeng.anubis.Partners;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RepositoryInjectTest extends IntegrationTest {
    @Test
    public void ensureAllRepositoryInjected() {
        assertThat(Partners.partnerRepository, notNullValue());
        assertThat(Partners.partnerKeyRepository, notNullValue());
    }
}
