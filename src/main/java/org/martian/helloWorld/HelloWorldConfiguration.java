package org.martian.helloWorld;

import io.dropwizard.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldConfiguration extends Configuration {
    @NotEmpty
    private String template;

    public String getTemplate() {
        return template;
    }

    @NotEmpty
    private String defaultName;

    public String getDefaultName() {
        return defaultName;
    }
}
