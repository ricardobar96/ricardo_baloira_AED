package es.iespuertodelacruz.ricardo.Supermercado.controller.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.ricardo.Supermercado.dto.PedidoDTO;
import es.iespuertodelacruz.ricardo.Supermercado.dto.ProductoDTO;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Pedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Producto;
import es.iespuertodelacruz.ricardo.Supermercado.services.PedidoService;
import es.iespuertodelacruz.ricardo.Supermercado.services.ProductoService;

@RestController
@RequestMapping("/api/v2/pedidos")
public class PedidoRESTv2 {
	private Logger logger = LoggerFactory.getLogger(PedidoRESTv2.class);
	@Autowired
	PedidoService pedidosService;
	@GetMapping("")
	public List<Pedido> getAll(){
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		pedidosService
		.findAll()
		.forEach(p -> pedidos.add((Pedido) p) );
		return pedidos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPedidoById(@PathVariable Integer id){
		Optional<Pedido> pedidoOPT = pedidosService.findById(id);
		if (pedidoOPT.isPresent()) {
			return ResponseEntity.ok(pedidoOPT);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El pedido no existe");
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Pedido> pedidoOPT = pedidosService.findById(id);
		
		if(pedidoOPT.isPresent()) {
			pedidosService.deleteById(id);
			return ResponseEntity.ok("Pedido eliminado");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El pedido no existe");
		}	
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PedidoDTO pedidoDto){
		Pedido p = new Pedido();
		p.setDireccionEntrega(pedidoDto.getDireccionEntrega());
		p.setEntregado(pedidoDto.getEntregado());
		p.setEnviado(pedidoDto.getEnviado());
		p.setPagado(pedidoDto.getPagado());
	    p.setFecha(pedidoDto.getFecha());
		p.setDetallepedidos(pedidoDto.getDetallepedidos());

		pedidosService.save(p);
		return ResponseEntity.ok().body(new PedidoDTO(p));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PedidoDTO pedidoDto){
		Optional<Pedido> pedidoOPT = pedidosService.findById(id);
		if(pedidoOPT.isPresent()) {
			Pedido p = pedidoOPT.get();

			if(pedidoDto.getDireccionEntrega()!=null) {
				p.setDireccionEntrega(pedidoDto.getDireccionEntrega());
			}
			if(pedidoDto.getEntregado()==0 || pedidoDto.getEntregado()==1) {
				p.setEntregado(pedidoDto.getEntregado());
			}
			if(pedidoDto.getEnviado()==0 || pedidoDto.getEnviado()==1) {
				p.setEnviado(pedidoDto.getEnviado());
			}
			if(pedidoDto.getPagado()==0 || pedidoDto.getPagado()==1) {
				p.setPagado(pedidoDto.getPagado());
			}
			if(pedidoDto.getDetallepedidos()!=null) {
				p.setDetallepedidos(pedidoDto.getDetallepedidos());
			}
			
			return ResponseEntity.ok(pedidosService.save(p));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El pedido no existe");
		}
	}
}
