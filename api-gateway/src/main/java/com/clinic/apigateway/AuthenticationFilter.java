package com.clinic.apigateway;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GatewayFilter {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Получаем заголовок Authorization
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // Если заголовок отсутствует или не начинается с "Bearer"
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return onError(exchange, "Пользователь не авторизован", HttpStatus.UNAUTHORIZED);
        }

        // Извлекаем токен
        String token = authHeader.substring(7);

        // Проверяем валидность токена
        if (!jwtUtil.isTokenValid(token)) {
            return onError(exchange, "Невалидный токен", HttpStatus.UNAUTHORIZED);
        }

        // Извлекаем данные из токена
        Claims claims = jwtUtil.getClaims(token);
        String username = claims.getSubject();
        String role = claims.get("role", String.class);

        // Добавляем username и role в заголовки запроса для дальнейшего использования
        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(r -> r.headers(h -> {
                    h.set("X-Username", username);
                    h.set("X-Role", role);
                }))
                .build();

        // Переход к следующему фильтру в цепочке
        return chain.filter(modifiedExchange);
    }

    // Метод для возврата ошибки с сообщением и статусом
    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);

        // Пишем сообщение в тело ответа
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);

        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        return response.writeWith(Mono.just(buffer));
    }
}

