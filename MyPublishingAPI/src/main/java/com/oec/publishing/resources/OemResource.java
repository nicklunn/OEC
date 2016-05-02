package com.oec.publishing.resources;



import com.oec.publishing.core.Oem;
import com.oec.publishing.db.OemDAO;
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

@Path("/publishing/oem")
@Produces(MediaType.APPLICATION_JSON)
public class OemResource {
    private final AtomicLong counter;
	private OemDAO oemDAO;

    
	public OemResource(OemDAO oemDAO) {
        this.oemDAO = oemDAO;
        this.counter = new AtomicLong();
    }

    @GET
    @UnitOfWork
    //public List<Oem> findByNumber(@QueryParam("oemId") Optional<String> oemId) {
	public List<Oem> findOEMs(@QueryParam("oemId") Optional<String> oemId, @QueryParam("oemName") Optional<String> oemName) {
        if (oemId.isPresent()) {
            return oemDAO.findByNumber(oemId.get());
        } else if (oemName.isPresent()){
			return oemDAO.findByName(oemName.get());
		} else {
            return oemDAO.findAll();
        }
    }
	
	@GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Oem> findById(@PathParam("id") LongParam id) {
        return oemDAO.findById(id.get());
    }
	
}