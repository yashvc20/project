package com.example.Controller;

import com.example.Service.ResourceService;
import com.example.dto.ResourceDTO;
import com.example.Entity.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public List<Resource> getAllResources() {
        logger.info("GET /resources called");
        List<Resource> resources = resourceService.getAllResources();
        logger.debug("Fetched {} resources", resources.size());
        return resources;
    }

    @GetMapping("/{id}")
    public Resource getResourceById(@PathVariable Long id) {
        logger.info("GET /resources/{} called", id);
        return resourceService.getResourceById(id)
                .orElseThrow(() -> {
                    logger.error("Resource not found with ID: {}", id);
                    return new RuntimeException("Resource not found");
                });
    }

    @PostMapping
    public Resource createResource(@Valid @RequestBody ResourceDTO resourceDTO) {
        logger.info("POST /resources called with name: {}", resourceDTO.getName());
        Resource resource = new Resource();
        resource.setName(resourceDTO.getName());
        resource.setType(resourceDTO.getType());
        resource.setDescription(resourceDTO.getDescription());

        Resource saved = resourceService.saveResource(resource);
        logger.info("Resource created with ID: {}", saved.getId());
        return saved;
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Long id) {
        logger.warn("DELETE /resources called for ID: {}", id);
        resourceService.deleteResource(id);
        logger.info("Resource deleted with ID: {}", id);
    }
}
