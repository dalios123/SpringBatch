package com.ooredoo.projetfinetude.Entities.Rapport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "reported_data_vol")
public class ReportedDataVol  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date time;
    private String imsi;
    private String msisdn;
    private Float reported_data_vol_dl;
    private Float reported_data_vol_ul;
    private Float granted_data_vol_total;

}
