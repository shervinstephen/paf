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

import model.Project;
@Path("/Project")
public class ProjectService {

	Project ProjectObj = new Project();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProject() {
		return ProjectObj.readProject();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("PID") String PID, @FormParam("type") String type, @FormParam("name") String name,
			@FormParam("details") String details) {
		String output = ProjectObj.insertProject(PID, type,name, details);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(String ProjectData) {
		
		JsonObject ProjectObject = new JsonParser().parse(ProjectData).getAsJsonObject();
		
		String PID = ProjectObject.get("PID").getAsString();
		String type = ProjectObject.get("type").getAsString();
		String name = ProjectObject.get("name").getAsString();
		String details = ProjectObject.get("details").getAsString();
		String output = ProjectObj.updateProject(PID, type,name, details);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String ProjectData) {
		
		Document doc = Jsoup.parse(ProjectData, "", Parser.xmlParser());
		
		String PID = doc.select("PID").text();
		String output = ProjectObj.deleteProject(PID);
		return output;
	}
}
