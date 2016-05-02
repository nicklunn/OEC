package com.oec.publishing.db;

import com.oec.publishing.core.Part;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import java.util.List;
import com.google.common.base.Optional;

public class PartDAO extends AbstractDAO<Part> {
    public PartDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Part> findById(long id) {
        return Optional.fromNullable(get(id));
    }

    public Part create(Part part) {
        return persist(part);
    }

    public List<Part> findAll() {
        return list(namedQuery("com.oec.publishing.core.Part.findAll"));
    }
	
	public List<Part> findByNumber(String partNumber) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(partNumber).append("%");
        return list(namedQuery("com.oec.publishing.core.Part.findByNumber").setParameter("partNumber", builder.toString()));
    }
}