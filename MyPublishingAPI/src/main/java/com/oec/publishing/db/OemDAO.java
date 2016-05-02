package com.oec.publishing.db;

import com.oec.publishing.core.Oem;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import java.util.List;
import com.google.common.base.Optional;

public class OemDAO extends AbstractDAO<Oem> {
    public OemDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Oem> findById(long id) {
        return Optional.fromNullable(get(id));
    }

    public Oem create(Oem oem) {
        return persist(oem);
    }

    public List<Oem> findAll() {
        return list(namedQuery("com.oec.publishing.core.Oem.findAll"));
		//return list(namedQuery("Nick"));
    }
	
	public List<Oem> findByNumber(String oemId) {
        StringBuilder builder = new StringBuilder("");
        builder.append(oemId);
        return list(namedQuery("com.oec.publishing.core.Oem.findByNumber").setParameter("oemId", Integer.parseInt(builder.toString())));
    }
	public List<Oem> findByName(String oemName) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(oemName.toLowerCase()).append("%");
        return list(namedQuery("com.oec.publishing.core.Oem.findByName").setParameter("oemName", builder.toString()));
    }
}