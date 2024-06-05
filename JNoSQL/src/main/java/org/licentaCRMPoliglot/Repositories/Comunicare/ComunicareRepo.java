package org.licentaCRMPoliglot.Repositories.Comunicare;

import jakarta.nosql.mapping.document.DocumentTemplate;
import lombok.NoArgsConstructor;
import org.licentaCRMPoliglot.Entities.Comunicare.Comunicare;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@NoArgsConstructor
@ApplicationScoped
public class ComunicareRepo {

    @Inject
    private DocumentTemplate template;

    private Validator validator;

    @Inject
    public void init() {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void save(Comunicare comunicare) {
        Set<ConstraintViolation<Comunicare>> violations = validator.validate(comunicare);
        if (violations.isEmpty()) {
            template.insert(comunicare);
        } else {
            throw new IllegalArgumentException("Comunicare validation failed: " + violations);
        }
    }
}
