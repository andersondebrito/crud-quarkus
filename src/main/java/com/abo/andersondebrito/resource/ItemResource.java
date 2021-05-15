package com.abo.andersondebrito.resource;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.abo.andersondebrito.dto.ItemDTO;
import com.abo.andersondebrito.entity.Item;

@Path("items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {
	
	@GET
	public List<Item> getAllProducts() {
		return Item.listAll();
	}
	
	@POST
	@Transactional
	public void createItem(ItemDTO dto) {
		Item item = new Item(dto.getName(), dto.getValue());
		item.persist();
	}
	
	@PUT
	@Path(value = "{id}")
	@Transactional
	public void updateItem(@PathParam("id")Long id, ItemDTO dto) {
		Optional<Item> itemOp = Item.findByIdOptional(id);
		
		if(itemOp.isPresent()) {
			Item item = itemOp.get();
			item.setName(dto.getName());
			item.setValue(dto.getValue());
			item.persist();
		} else {
			throw new NotFoundException();
		}
	}
	
	@DELETE
	@Path(value = "{id}")
	@Transactional
	public void deleteItem(@PathParam("id")Long id) {
		Optional<Item> itemOp = Item.findByIdOptional(id);
		
		itemOp.ifPresentOrElse(Item::delete, () -> {
			throw new NotFoundException();
			}
		);
	}

}
