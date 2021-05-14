package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Customer;

@Path("/Customer")
public class CustomerService {

	Customer customerObj = new Customer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomers() {
		return customerObj.readCustomer();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(@FormParam("CID") String CID, @FormParam("name") String name,
			@FormParam("details") String details) {
		String output = customerObj.insertCustomer(CID, name, details);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData) {
		JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
		String CID = customerObject.get("CID").getAsString();
		String name = customerObject.get("name").getAsString();
		String details = customerObject.get("details").getAsString();
		String output = customerObj.updateCustomer(CID, name, details);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData) {
		Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());
		String CID = doc.select("CID").text();
		String output = customerObj.deleteCustomer(CID);
		return output;
	}

}
