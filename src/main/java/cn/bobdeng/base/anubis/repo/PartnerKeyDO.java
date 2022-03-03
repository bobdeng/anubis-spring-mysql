package cn.bobdeng.base.anubis.repo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "anubis_partner_key")
public class PartnerKeyDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int partnerId;
    @Column(name = "pub_key")
    private String key;
    private Long expireAt;
}
