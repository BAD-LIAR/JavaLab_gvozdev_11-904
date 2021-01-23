package HTMLProcessor;

@HtmlForm(method = "post", action = "/users")
public class Car {
    @HtmlInput(name = "carname", placeholder = "Car ник")
    private String carname;
    @HtmlInput(name = "email",type = "email", placeholder = "Ваша почта")
    private String email;
    @HtmlInput(name = "password", type = "password", placeholder = "Пароль")
    private String password;
}
