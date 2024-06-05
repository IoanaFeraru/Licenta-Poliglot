package org.licentaCRMPoliglot.Repositories.Achizitie;

import jakarta.nosql.mapping.document.DocumentTemplate;
import lombok.NoArgsConstructor;
import org.licentaCRMPoliglot.Entities.Achizitie.Achizitie;
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
public class AchizitieRepo {

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

    public void save(Achizitie achizitie) {
        Set<ConstraintViolation<Achizitie>> violations = validator.validate(achizitie);
        if (violations.isEmpty()) {
            template.insert(achizitie);
        } else {
            throw new IllegalArgumentException("Achizitie validation failed: " + violations);
        }
    }
}
