package com.example.projecttracker.Controller;

import com.example.projecttracker.ApiResponse.ApiResponse;
import com.example.projecttracker.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project-tracker")
public class ProjectTrackerController {

    ArrayList<Project> projects = new ArrayList<Project>();

    @PostMapping("/create-project")
    public ApiResponse createProject(@RequestBody Project project) {
        projects.add(project);

        return new ApiResponse("Project has been created successfully");
    }

    @GetMapping("/display-projects")
    public ArrayList<Project> displayProjects() {
        return projects;
    }

    @PutMapping("/update-project/{index}")
    public ApiResponse updateProject(@PathVariable int index, @RequestBody Project project) {
        projects.set(index, project);

        return new ApiResponse("Project at index " + index + " has been updated successfully");
    }

    @DeleteMapping("/delete-project/{index}")
    public ApiResponse deleteProject(@PathVariable int index) {
        projects.remove(index);

        return new ApiResponse("Project at index " + index + " has been deleted successfully");
    }

    @PutMapping("/change-status/{index}")
    public ApiResponse changeStatus(@PathVariable int index) {
        if (projects.get(index).getStatus().equalsIgnoreCase("Done"))
            projects.get(index).setStatus("Not Done");
        else projects.get(index).setStatus("Done");

        return new ApiResponse("Project status at index " + index + " has been changed successfully");
    }

    @GetMapping("/get-project-by-title/{title}")
    public Project getProjectByTitle(@PathVariable String title) {
        for (Project project : projects)
            if (project.getTitle().toLowerCase().contains(title))
                return project;

        return null;
    }

    @GetMapping("/get-company-projects")
    public ArrayList<Project> getCompanyProjects(@RequestBody String companyName) {
        ArrayList<Project> companyProjects = new ArrayList<>();
        for (Project project : projects)
            if (project.getCompanyName().equalsIgnoreCase(companyName))
                companyProjects.add(project);

        return companyProjects;
    }
}
