package com.z.annotation.bizcheck;

import com.z.annotation.dovalid.NewsBizValid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
@Constraint(validatedBy = NewsBizValid.NewsTitleConflictValidator.class)
public @interface NewsTitleConflict {

    String message() default "消息标题重复";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
