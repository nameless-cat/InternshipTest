package com.nameless.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wwwtm on 10.03.2017.
 */
@Entity
@Table(name="filter")
public class Filter
{
    @Id
    private int id;
}
