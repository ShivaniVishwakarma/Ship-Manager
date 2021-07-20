package com.hpc.shipservice.service;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShipCodeGenerator implements IdentifierGenerator{

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-MM-mm-ss");
            return simpleDateFormat.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
