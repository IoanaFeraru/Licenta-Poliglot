package org.licentaCRMPoliglot.Repositories.TaguriProdus;

import jakarta.nosql.mapping.document.DocumentTemplate;
import lombok.NoArgsConstructor;
import org.licentaCRMPoliglot.Entities.ProdusReferences.TaguriProdus;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@NoArgsConstructor
@ApplicationScoped
public class TaguriProdusRepositoryImp {

    @Inject
    private DocumentTemplate template;

    private Validator validator;

    public void TaguriProdusRepositoryImp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public TaguriProdusRepositoryImp(Validator validator) {
        this.validator = validator;
    }

    public void save(TaguriProdus taguriProdus) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<TaguriProdus>> violations = validator.validate(taguriProdus);
            if (violations.isEmpty()) {
                template.insert(taguriProdus);
            } else {
                throw new IllegalArgumentException("TaguriProdus validation failed: " + violations);
            }
        }
    }

}


