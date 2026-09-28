package com.example.Service;

import com.example.Entity.Resource;
import com.example.Repository.ResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {
    private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);
    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getAllResources() {
        logger.info("Fetching all resources");
        return resourceRepository.findAll();
    }

    public Optional<Resource> getResourceById(Long id) {
        logger.info("Fetching resource by ID: {}", id);
        return resourceRepository.findById(id);
    }

    public Resource saveResource(Resource resource) {
        logger.info("Saving resource: {}", resource.getName());
        try {
            return resourceRepository.save(resource);
        } catch (Exception e) {
            logger.error("Failed to save resource: {}", resource.getName(), e);
            throw e;
        }
    }

    public void deleteResource(Long id) {
        logger.warn("Deleting resource with ID: {}", id);
        resourceRepository.deleteById(id);
        logger.info("Resource deleted successfully with ID: {}", id);
    }
}
