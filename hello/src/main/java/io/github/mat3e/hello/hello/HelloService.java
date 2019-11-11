package io.github.mat3e.hello.hello;

import io.github.mat3e.hello.lang.Lang;
import io.github.mat3e.hello.lang.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HelloService {
    static final String FALLBACK_NAME = "world";
    static final Lang FALLBACK_LANG = new Lang(1, "Helo", "en");
    private final Logger logger= LoggerFactory.getLogger(HelloService.class);
    private LangRepository repository;

    HelloService(LangRepository repository) {
        this.repository = repository;
    }

    String prepareGreting(String name, Integer langId) {
        langId = Optional.ofNullable(langId).orElse(FALLBACK_LANG.getId());

        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome + "!";
    }
}
