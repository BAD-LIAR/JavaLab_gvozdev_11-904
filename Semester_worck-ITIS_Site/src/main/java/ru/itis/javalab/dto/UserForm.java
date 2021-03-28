package ru.itis.javalab.dto;

import lombok.Data;
import ru.itis.javalab.validation.ValidNames;
import ru.itis.javalab.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Data
@ValidNames(
        message = "{errors.invalid.names}",
        name = "firstName",
        surname = "lastName"
)
public class UserForm {

    @NotEmpty(message = "{errors.incorrect.email}")
    @Email(message = "{errors.incorrect.email}")
    private String email;


    @ValidPassword(message = "{errors.invalid.password}")
    private String password;


    @NotEmpty(message = "{errors.invalid.empty}")
    private String firstName;

    @NotEmpty(message = "{errors.invalid.empty}")
    private String lastName;
}
