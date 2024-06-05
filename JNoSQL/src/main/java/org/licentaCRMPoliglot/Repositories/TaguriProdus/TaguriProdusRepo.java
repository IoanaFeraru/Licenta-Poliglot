package org.licentaCRMPoliglot.Repositories.TaguriProdus;

import jakarta.nosql.mapping.document.DocumentTemplate;
import lombok.NoArgsConstructor;
import org.licentaCRMPoliglot.Entities.ProdusReferences.TaguriProdus;
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
public class TaguriProdusRepo {

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

    public void save(TaguriProdus taguriProdus) {
        Set<ConstraintViolation<TaguriProdus>> violations = validator.validate(taguriProdus);
        if (violations.isEmpty()) {
            template.insert(taguriProdus);
        } else {
            throw new IllegalArgumentException("TaguriProdus validation failed: " + violations);
        }
    }
}
