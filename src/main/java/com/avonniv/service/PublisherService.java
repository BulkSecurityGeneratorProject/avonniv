package com.avonniv.service;

import com.avonniv.domain.Publisher;
import com.avonniv.repository.PublisherRepository;
import com.avonniv.service.dto.PublisherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PublisherService {

    private final Logger log = LoggerFactory.getLogger(PublisherService.class);

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher createPublisher(PublisherDTO publisherDTO) {
        Publisher newPublisher = new Publisher();
        newPublisher.setName(publisherDTO.getName());
        newPublisher.setDescriptionSWE(publisherDTO.getDescriptionSWE());
        newPublisher.setDescriptionEN(publisherDTO.getDescriptionEN());
        newPublisher.setAddress(publisherDTO.getAddress());
        newPublisher.setEmail(publisherDTO.getEmail());
        newPublisher.setPhone(publisherDTO.getPhone());
        newPublisher.setUrl(publisherDTO.getUrl());
        newPublisher.setCrawled(publisherDTO.isCrawled());

        publisherRepository.save(newPublisher);
        log.debug("Created Information for Publisher: {}", newPublisher);
        return newPublisher;
    }

    @Transactional(readOnly = true)
    public Page<PublisherDTO> getAllPublisher(Pageable pageable) {
        return publisherRepository.findAll(pageable).map(PublisherDTO::new);
    }

    @Transactional(readOnly = true)
    public List<PublisherDTO> getAllPublisher(Boolean crawled) {
        return publisherRepository.findAllByCrawledIs(crawled).stream()
            .map(PublisherDTO::new)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Publisher> getPublisherByName(String name) {
        return publisherRepository.findOneByName(name);
    }

    public List<PublisherDTO> getAll() {
        return publisherRepository.findAll().stream().map(PublisherDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Publisher> getById(Long id) {
        Publisher publisher = publisherRepository.findOne(id);
        if (publisher == null) {
            return Optional.empty();
        }
        return Optional.of(publisherRepository.findOne(id));
    }

    public Optional<PublisherDTO> updatePublisher(PublisherDTO publisherDTO) {
        return Optional.of(publisherRepository
            .findOne(publisherDTO.getId()))
            .map(publisher -> {
                publisher.setName(publisherDTO.getName());
                publisher.setDescriptionSWE(publisherDTO.getDescriptionSWE());
                publisher.setDescriptionEN(publisherDTO.getDescriptionEN());
                publisher.setAddress(publisherDTO.getAddress());
                publisher.setEmail(publisherDTO.getEmail());
                publisher.setPhone(publisherDTO.getPhone());
                publisher.setUrl(publisherDTO.getUrl());
                publisher.setCrawled(publisherDTO.isCrawled());
                log.debug("Changed Information for Publisher: {}", publisher);
                return publisher;
            })
            .map(PublisherDTO::new);
    }

    public void deletePublisher(PublisherDTO publisherDTO) {
        publisherRepository.delete(publisherDTO.getId());
    }

    public void deletePublisher(String name) {
        publisherRepository.findOneByName(name).ifPresent(publisher -> {
            publisherRepository.delete(publisher);
            log.debug("Deleted Publisher: {}", publisher);
        });
    }
}
