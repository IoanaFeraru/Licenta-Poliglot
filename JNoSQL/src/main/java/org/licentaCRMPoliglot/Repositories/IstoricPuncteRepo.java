package org.licentaCRMPoliglot.Repositories;

import jakarta.nosql.mapping.document.DocumentTemplate;
import lombok.NoArgsConstructor;
import org.licentaCRMPoliglot.Entities.ClientReferences.IstoricPuncte;
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
public class IstoricPuncteRepo {

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

    public void save(IstoricPuncte istoricPuncte) {
        Set<ConstraintViolation<IstoricPuncte>> violations = validator.validate(istoricPuncte);
        if (violations.isEmpty()) {
            IstoricPuncte existingIstoric = template.find(IstoricPuncte.class, istoricPuncte.getCodClient()).orElse(null);
            if (existingIstoric != null) {
                existingIstoric.getPuncteEntries().addAll(istoricPuncte.getPuncteEntries());
                template.update(existingIstoric);
            } else {
                template.insert(istoricPuncte);
            }
        } else {
            throw new IllegalArgumentException("IstoricPuncte validation failed: " + violations);
        }
    }
}
