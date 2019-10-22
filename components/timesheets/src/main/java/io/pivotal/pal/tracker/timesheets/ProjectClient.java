package io.pivotal.pal.tracker.timesheets;

import org.springframework.web.client.RestOperations;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public class ProjectClient {

    private final RestOperations restOperations;
    private final String endpoint;
    private final Map<Long, ProjectInfo> projects = new ConcurrentHashMap<>();

    public ProjectClient(RestOperations restOperations, String registrationServerEndpoint) {
        this.restOperations = restOperations;
        this.endpoint = registrationServerEndpoint;
    }

    @HystrixCommand(fallbackMethod = "getProjectFromCache")
    public ProjectInfo getProject(long projectId) {
        ProjectInfo project = restOperations.getForObject(endpoint + "/projects/" + projectId, ProjectInfo.class);
        projects.put(projectId, project);
        return project;
    }

    public ProjectInfo getProjectFromCache(long projectId){
        return projects.get(projectId);
    }
}
