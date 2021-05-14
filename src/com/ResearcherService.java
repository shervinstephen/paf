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

import model.Researcher;

@Path("/Researcher")
public class ResearcherService {

	Researcher ResearcherObj = new Researcher();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearchers() {
		return ResearcherObj.readResearchers();
	} 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearcher(@FormParam("RID") String RID, @FormParam("name") String name,
			@FormParam("address") String address, @FormParam("phone") String phone,@FormParam("email") String email) {
		String output = ResearcherObj.insertResearcher(RID, name, address, phone,email);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearcher(String ResearcherData) {
		JsonObject ResearcherObject = new JsonParser().parse(ResearcherData).getAsJsonObject();
		String RID = ResearcherObject.get("RID").getAsString();
		String name = ResearcherObject.get("name").getAsString();
		String address = ResearcherObject.get("address").getAsString();
		String phone = ResearcherObject.get("phone").getAsString();
		String email = ResearcherObject.get("email").getAsString();
		String output = ResearcherObj.updateResearcher(RID, name, address, phone,email);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearcher(String ResearcherData) {
		Document doc = Jsoup.parse(ResearcherData, "", Parser.xmlParser());
		String RID = doc.select("RID").text();
		String output = ResearcherObj.deleteResearcher(RID);
		return output;
	}
}
