package com.javaee.acoes.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.javaee.acoes.domain.Pedido;
import com.javaee.acoes.config.RabbitMQConfig;

@Service
public class PedidoServiceImpl implements PedidoService {

	private final RabbitTemplate rabbitTemplate;
	
	public PedidoServiceImpl( RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Override
    public void sendPedido(Pedido pedido) {
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_MESSAGES, pedido);
    }
}
