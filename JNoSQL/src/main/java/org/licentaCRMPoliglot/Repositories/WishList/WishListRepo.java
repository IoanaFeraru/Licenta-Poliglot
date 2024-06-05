package org.licentaCRMPoliglot.Repositories.WishList;

import jakarta.nosql.mapping.document.DocumentTemplate;
import lombok.NoArgsConstructor;
import org.licentaCRMPoliglot.Entities.ClientReferences.WishList;
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
public class WishListRepo {

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

    public void save(WishList wishList) {
        Set<ConstraintViolation<WishList>> violations = validator.validate(wishList);
        if (violations.isEmpty()) {
            template.insert(wishList);
        } else {
            throw new IllegalArgumentException("WishList validation failed: " + violations);
        }
    }
}
