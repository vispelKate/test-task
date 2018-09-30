package com.ks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Company implements Serializable {

    @Column( name = "company_name")
    private String name;
    private String catchPhrase;
    private String bs;

}
