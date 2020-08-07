package ru.testfield.ansible.inventory.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.testfield.ansible.inventory.model.LoginUserGroupFormatter;

@Configuration
class MvcConfig implements WebMvcConfigurer {

    final LoginUserGroupFormatter loginUserGroupFormatter;

    public MvcConfig(LoginUserGroupFormatter loginUserGroupFormatter) {
        this.loginUserGroupFormatter = loginUserGroupFormatter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(loginUserGroupFormatter);
    }
}
