package ggd.test.jsh.counter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Named
@Path("/counter")
public class CounterResource {

    private CounterRepository counterRepository;

    @Inject
    public CounterResource(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @POST
    @Transactional
    public Response create() {
        Counter counter = new Counter();
        counterRepository.persist(counter);
        return Response.created(UriBuilder.fromPath(counter.getId()).build()).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void increment(@PathParam("id") String id) {
        getSafely(id).increment();
    }

    @GET
    @Path("/{id}")
    public String get(@PathParam("id") String id) {
        return String.valueOf(getSafely(id).getValue());
    }

    private Counter getSafely(String id) {
        Counter counter = counterRepository.get(id);
        if (counter == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return counter;
    }
}
