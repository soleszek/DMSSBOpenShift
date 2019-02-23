package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.enums.DocumentStates;
import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.DocumentRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;
import com.oleszeksylwester.dmssb.DMSSB.utils.DataOperations;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private static final Logger LOGGER = Logger.getLogger(DocumentService.class.getName());

    private final DocumentRepository documentRepository;
    private NameFactory nameFactory;
    private final UserRepository userRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, NameFactory nameFactory, UserRepository userRepository){
        this.documentRepository = documentRepository;
        this.nameFactory = nameFactory;
        this.userRepository = userRepository;
    }

    @Transactional
    public void SaveOrUpdate(Document document, InputStream fileContent, String type, String path) {
        document.setRevision(1);
        document.setState(DocumentStates.INWORK.getState());
        document.setType(type);
        document.setCreationDate(LocalDate.now());
        document.setLastModification(LocalDate.now());
        document.setLink(path);

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username);
        document.setOwner(user);

        documentRepository.save(document);

        String fileName = document.getId().toString();
        DataOperations.saveData(document.getType(), fileContent, fileName);

        Long documentId = document.getId();
        String name = nameFactory.createName(documentId, document.getType());
        document.setName(name);

        documentRepository.save(document);
    }

    @Transactional(readOnly = true)
    public Document findById(Long id){
        return documentRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no document with this id"));
    }

    @Transactional(readOnly = true)
    public List<Document> findAll(){
        return documentRepository.findAll();
    }

    //TODO
    //Jak napisać zapytanie, które umożliwi pobranie z bazy tylko zatwierdzonych wersji
    @Transactional(readOnly = true)
    public List<Document> findAllApproved(){
        List<Document> allDocuments = documentRepository.findAll();

        return allDocuments.stream()
                .filter(u -> u.getState().equals(DocumentStates.RELEASED.getState()))
                .collect(Collectors.toList());
    }

    //TODO
    //Jak napisać zapytanie, które umożliwi pobranie wszystkich rewizji o zadanym NAME
    @Transactional(readOnly = true)
    public List<Document> findAllRevisions(String name){
        List<Document> allDocuments = documentRepository.findAll();

        return allDocuments.stream()
                .filter(u -> u.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Transactional
    public Document createNewRevision(Long id){
        Document document = findById(id);

        List<Document> documents = findAll();
        String name = document.getName();

        List<Document> documentRevisions = documents.stream()
                .filter(r -> r.getName().equals(name))
                .collect(Collectors.toList());

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username);

        Long lastRevisionId = documentRevisions.get(documentRevisions.size() - 1).getId();
        int lastRevisionNumber = findById(lastRevisionId).getRevision();

        Document newRevision = new Document.Builder()
                .name(document.getName())
                .revision(lastRevisionNumber + 1)
                .type(document.getType())
                .title(document.getTitle())
                .description("")
                .state(DocumentStates.INWORK.getState())
                .owner(user)
                .creationDate(LocalDate.now())
                .lastModification(LocalDate.now())
                .link("")
                .build();

        documentRepository.save(newRevision);

        return newRevision;

    }

    @Transactional
    public void deleteById(Long id){

        Document document = findById(id);
        String type = document.getType();
        String fileName = id.toString();
        DataOperations.deleteData(type, fileName);

        documentRepository.deleteById(id);
    }

    @Transactional
    public void delete(Document document){
        documentRepository.delete(document);
    }

    public String readPdfDocument(String filename){
        InputStream is = null;
        try {
            is = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Can't read file from the source " + filename);
        }
        byte[] pdfBytes = new byte[0];
        try {
            pdfBytes = IOUtils.toByteArray(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(Base64.encodeBase64(pdfBytes));
    }

    @Transactional
    public Document update(Long documentId, String title, String description){
        Document document = findById(documentId);
        document.setTitle(title);
        document.setDescription(description);
        document.setLastModification(LocalDate.now());
        documentRepository.save(document);

        return document;
    }
}
