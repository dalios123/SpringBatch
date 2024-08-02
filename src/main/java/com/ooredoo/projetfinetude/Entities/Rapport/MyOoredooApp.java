package com.ooredoo.projetfinetude.Entities.Rapport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "my_ooredoo_app")
public class MyOoredooApp  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date time;
    private String application_name;
    private String msisdn;
    private String imsi ;
    private String cell ;
    private String radio_acess;
    private Float data_vol_total;
    private Double tcp_conn_establ_ul_sr;
    private Float tcp_initial_radio_rtt_avg;
    private Float data_throughput ;

}
