package com.javaee.acoes.controllers.v1;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.acoes.domain.Acao;
import com.javaee.acoes.domain.Pedido;
import com.javaee.acoes.services.AcaoService;
import com.javaee.acoes.services.BalcaoService;
import com.javaee.acoes.services.PedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("This id Balcao API")
@RestController
@RequestMapping(BalcaoController.BASE_URL)
public class BalcaoController {

	public static final String BASE_URL = "/api/v1/balcao";
	
    private final BalcaoService balcaoService;
    
    private final PedidoService pedidoService;

    public BalcaoController(BalcaoService balcaoService, PedidoService pedidoService) {
        this.balcaoService = balcaoService;
        this.pedidoService = pedidoService;
    }
	
	
	@ApiOperation(value = "View List de Ações Disponiveis", notes="Todas as ações disponiveis para compra")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Acao> getAll(){
        return balcaoService.getAll();
    }
	
	@ApiOperation(value = "Comprar Ação Disponiveis")
	@PostMapping({"/compra"})
    @ResponseStatus(HttpStatus.CREATED)
    public String compraPedido(@RequestBody Pedido pedido){
		pedido.setVenda(false);
		pedidoService.sendPedido(pedido);
        return "Pedido Enviado";
    }
	
	@ApiOperation(value = "Disponibizar Ação para venda")
	@PostMapping({"/venda"})
    @ResponseStatus(HttpStatus.CREATED)
    public String vendaPedido(@RequestBody Pedido pedido){
		pedido.setVenda(true);
		pedidoService.sendPedido(pedido);
        return "Pedido Enviado";
    }
}
