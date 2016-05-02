package com.oec.publishing.resources;



import com.oec.publishing.core.Part;
import com.oec.publishing.db.PartDAO;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/publishing")
@Produces(MediaType.APPLICATION_JSON)
public class PublishingResource {
    private final AtomicLong counter;
	private PartDAO partDAO;

    
	public PublishingResource(PartDAO partDAO) {
        this.partDAO = partDAO;
        this.counter = new AtomicLong();
    }

    @GET
    @UnitOfWork
    public List<Part> findByNumber(
            @QueryParam("partNumber") Optional<String> partNumber
    ) {
        if (partNumber.isPresent()) {
            return partDAO.findByNumber(partNumber.get());
        } else {
            return partDAO.findAll();
        }
    }
	
	@GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Part> findById(@PathParam("id") LongParam id) {
        return partDAO.findById(id.get());
    }
}