package com.javaee.acoes.listeners;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.javaee.acoes.config.RabbitMQConfig;
import com.javaee.acoes.domain.Acao;
import com.javaee.acoes.domain.Cliente;
import com.javaee.acoes.domain.Empresa;
import com.javaee.acoes.domain.Pedido;
import com.javaee.acoes.email.EmailConfig;
import com.javaee.acoes.repositories.ClienteRepository;
import com.javaee.acoes.services.AcaoService;
import com.javaee.acoes.services.ClienteService;
import com.javaee.acoes.services.EmpresaService;



@Component
public class PedidoListener {
	
	static final Logger logger = LoggerFactory.getLogger(PedidoListener.class);

	private AcaoService acaoService;
	
	private ClienteService clienteService;
	
	private  EmpresaService empresaService;
	
	public PedidoListener(AcaoService acaoService,
			ClienteService clienteService,
			EmpresaService empresaService) {
		this.acaoService = acaoService;
		this.clienteService = clienteService;
		this.empresaService = empresaService;
	}
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_MESSAGES)
	public void ProcessPedido(Pedido pedido) {
		
		logger.info("Message Pedido recebido");
        logger.info("ID Acao: " + pedido.getId());
        logger.info("Id cliente: " + pedido.getClienteId());
        logger.info("Venda true: " + pedido.getVenda());
        
        try {
        	Acao acao = this.acaoService.getAcaoById(pedido.getId());
            Cliente cliPedido = this.clienteService.getClienteById(acao.getClienteId());
            Empresa empPedido = this.empresaService.getEmpresaById(acao.getEmpresaId());
            
            if (pedido.getVenda()) {
            	acao.setEmpresaId(pedido.getClienteId());
            	acao.setClienteId("");
            	acao.setDtAtualizacao(new Date());
            } else {
            	acao.setClienteId(pedido.getClienteId());
            	acao.setDtAtualizacao(new Date());
            }
            logger.info("Acao atualizada");
            logger.info("Id cliente: " + acao.getClienteId());
            logger.info("Id Empresa: " + acao.getEmpresaId());
            logger.info("Id DateUp: " + acao.getDtAtualizacao());
            
            acaoService.saveAcao(pedido.getId(), acao);
            
            logger.info("###############################");
            logger.info("Cliente Localizado");
            logger.info("Id cliente: " + cliPedido.getId());
            logger.info("Email cliente: " + cliPedido.getEmail());
            
            logger.info("###############################");
            logger.info("Empresa Localizado");
            logger.info("Id Empresa: " + empPedido.getId());
            logger.info("Email Empresa: " + empPedido.getEmail());
    		
            
            final String fromEmail = "alexandrecunha.eti@gmail.com";
    		final String password = "sumppujrnpgiprnf";
    		final String toEmail = "alexandrecunha.eti@gmail.com";
    		
    		System.out.println("Initializing email send");
    		
    		EmailConfig config = new EmailConfig();
    		
    		config.sendEmail(fromEmail, password,toEmail, "Acoes Vendida.trabalho final email" + cliPedido.getEmail(), "Sua ação foi comprada");
    		
    		config.sendEmail(fromEmail, password, toEmail, "Acoes Compra trabalho final email" + empPedido.getEmail(), "Sua ação foi vendida");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("## Error.....");
		}
        
        
        
	}
	
}
