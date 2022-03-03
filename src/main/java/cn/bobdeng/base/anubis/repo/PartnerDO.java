package cn.bobdeng.base.anubis.repo;

import cn.bobdeng.anubis.Partner;
import cn.bobdeng.anubis.PartnerCode;
import cn.bobdeng.anubis.PartnerId;
import cn.bobdeng.anubis.PartnerName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "anubis_partner")
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String name;

    public Partner toEntity() {
        return new Partner(PartnerId.of(id), PartnerCode.of(code), new PartnerName(name));
    }
}
