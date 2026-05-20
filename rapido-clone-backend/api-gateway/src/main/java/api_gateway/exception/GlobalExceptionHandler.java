package exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class GlobalExceptionHandler
        implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange,
                             Throwable ex) {

        log.error("Gateway Error: {}", ex.getMessage());

        exchange.getResponse()
                .setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

        return exchange.getResponse().setComplete();
    }
}
