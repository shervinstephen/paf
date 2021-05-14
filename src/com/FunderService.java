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

import model.Funder;

@Path("/Funder")
public class FunderService {

	Funder funderObj = new Funder();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunders() {
		return funderObj.readFunders();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFunder(@FormParam("FID") String FID, @FormParam("name") String name,
			@FormParam("amount") String amount, @FormParam("phone") String phone, @FormParam("email") String email) {
		String output = funderObj.insertFunder(FID, name, amount, phone, email);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFunder(String funderData) {
		JsonObject funderObject = new JsonParser().parse(funderData).getAsJsonObject();
		String FID = funderObject.get("FID").getAsString();
		String name = funderObject.get("name").getAsString();
		String amount = funderObject.get("amount").getAsString();
		String phone = funderObject.get("phone").getAsString();
		String email = funderObject.get("email").getAsString();
		String output = funderObj.updateFunder(FID, name, amount, phone, email);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFunder(String funderData) {
		Document doc = Jsoup.parse(funderData, "", Parser.xmlParser());

		String FID = doc.select("FID").text();
		String output = funderObj.deleteFunder(FID);
		return output;
	}
}
