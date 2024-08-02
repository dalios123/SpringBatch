package com.ooredoo.projetfinetude.Entities.Rapport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "vol_data")
public class VolDataByUsers  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;

    private Date time;

    private String imsi ;

    private String msisdn;

    private Float data_vol_total;


}
