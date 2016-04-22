package org.martian.helloWorld.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.martian.helloWorld.model.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/helloWorld")
@Api(value = "Hello World APIs", description = "Hello World")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(final String template,
                              final String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @ApiOperation(
            value = "Say Hello",
            notes = "Endpoint to say hello"
    )
    public Saying sayHello(@QueryParam("name") String nameParam) {
        String name = defaultName;
        if (!StringUtils.isEmpty(nameParam)) {
            name = nameParam;
        }
        final String message = String.format(template, name);
        return new Saying(counter.incrementAndGet(), message);
    }
}
