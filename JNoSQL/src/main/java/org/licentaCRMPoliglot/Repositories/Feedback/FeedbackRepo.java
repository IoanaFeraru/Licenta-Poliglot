package org.licentaCRMPoliglot.Repositories.Feedback;

import jakarta.nosql.mapping.document.DocumentTemplate;
import lombok.NoArgsConstructor;
import org.licentaCRMPoliglot.Entities.ClientReferences.Feedback;
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
public class FeedbackRepo {

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

    public void save(Feedback feedback) {
        Set<ConstraintViolation<Feedback>> violations = validator.validate(feedback);
        if (violations.isEmpty()) {
            template.insert(feedback);
        } else {
            throw new IllegalArgumentException("Feedback validation failed: " + violations);
        }
    }
}
