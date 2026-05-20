package ride_service.websocker;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.*;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class WebSocketAuthInterceptor
        implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) {

        String token =
                ((ServletServerHttpRequest) request)
                        .getServletRequest()
                        .getHeader("Authorization");

        return validateToken(token);
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception) {
    }

    private boolean validateToken(String token) {

        return token != null &&
                token.startsWith("Bearer ");
    }
}
