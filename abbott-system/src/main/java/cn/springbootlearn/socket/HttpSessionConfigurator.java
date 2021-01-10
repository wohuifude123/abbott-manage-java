package cn.springbootlearn.socket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author abbottLiu
 * @Description
 * @date 2020/04/28
 */
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator{

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpSession session = (HttpSession) request.getHttpSession();
        config.getUserProperties().put(HttpSession.class.getName(), session);
    }
}