package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.service.DocumentService;
import com.oleszeksylwester.dmssb.DMSSB.utils.DataOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class DocumentController {

    private static final Logger LOGGER = Logger.getLogger(DocumentController.class.getName());

    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents")
    private ModelAndView displayAllDocuments(){
        ModelAndView mov = new ModelAndView();

        List<Document> documents = documentService.findAll();
        List<Document> approvedDocuments = documentService.findAllApproved();

        mov.addObject("document", new Document());
        mov.addObject("approvedDocuments", approvedDocuments);
        mov.addObject("documents", documents);
        mov.setViewName("documents");

        return mov;
    }

    @PostMapping("/new/document")
    private ModelAndView createDocument(@ModelAttribute("document") Document document, @RequestParam("file") MultipartFile file, @RequestParam("doctype") String type, ModelMap modelMap) {

        String path = Paths.get(file.getOriginalFilename()).getFileName().toString();
        modelMap.addAttribute("file", file);
        ModelAndView mov = new ModelAndView();

        InputStream fileContent = null;
        try {
            fileContent = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Problem with acquring stream from MultipartFile");
        }
        documentService.SaveOrUpdate(document, fileContent, type, path);

        String fileName = DataOperations.drawingsPath + String.valueOf(document.getId());
        String pdf = documentService.readPdfDocument(fileName);

        mov.addObject("pdf", pdf);
        mov.addObject("document", document);
        mov.setViewName("document");

        return mov;
    }

    @GetMapping("/document")
    private ModelAndView displayDocument(@ModelAttribute("document") Document document){
        ModelAndView mov = new ModelAndView();
        String fileName = DataOperations.drawingsPath + String.valueOf(document.getId());
        String pdf = documentService.readPdfDocument(fileName);

        mov.addObject("pdf", pdf);
        mov.addObject("document", document);
        mov.setViewName("document");

        return mov;
    }

    @GetMapping("/document/{documentId}")
    private ModelAndView displayDocument(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        String fileName = DataOperations.drawingsPath + String.valueOf(documentId);
        String pdf = documentService.readPdfDocument(fileName);

        mov.addObject("pdf", pdf);
        mov.addObject("document", document);
        mov.setViewName("document");

        return mov;
    }

    @PostMapping("/update/document/{documentId}")
    private ModelAndView updateDocument(@PathVariable("documentId") Long documentId, @RequestParam("title") String title, @RequestParam("description") String description){
        ModelAndView mov = new ModelAndView();

        Document document = documentService.update(documentId, title, description);

        String fileName = DataOperations.drawingsPath + String.valueOf(documentId);
        String pdf = documentService.readPdfDocument(fileName);

        mov.addObject("pdf", pdf);
        mov.addObject(document);
        mov.setViewName("document");

        return mov;
    }

    @GetMapping("/document/{documentId}/revisions")
    private ModelAndView displayDocumentRevisions(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);
        String name = document.getName();
        List<Document> revisions = documentService.findAllRevisions(name);

        mov.addObject(document);
        mov.addObject("revisions", revisions);
        mov.setViewName("revisions");

        return mov;
    }

    @GetMapping("/document/{documentId}/lifecycle")
    private ModelAndView displayDocumentLifecycle(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        mov.addObject("document", document);
        mov.setViewName("lifecycle");

        return mov;
    }

    @GetMapping("/document/{documentId}/viewer")
    private ModelAndView displayDocumentViewer(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        String fileName = DataOperations.drawingsPath + String.valueOf(documentId);

        String pdf = documentService.readPdfDocument(fileName);

        mov.addObject("pdf", pdf);
        mov.addObject("document", document);
        mov.setViewName("viewer");

        return mov;
    }

    @GetMapping("/new/revision/{documentId}")
    private ModelAndView createNewRevision(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();

        Document document = documentService.createNewRevision(documentId);

        String name = document.getName();
        List<Document> revisions = documentService.findAllRevisions(name);

        mov.addObject(document);
        mov.addObject("revisions", revisions);
        mov.setViewName("revisions");

        return mov;
    }

    @GetMapping("/delete/document/{documentId}")
    private ModelAndView deleteDocument(@PathVariable("documentId") Long documentId) {
        ModelAndView mov = new ModelAndView();

        documentService.deleteById(documentId);

        List<Document> documents = documentService.findAll();
        List<Document> approvedDocuments = documentService.findAllApproved();

        mov.addObject("document", new Document());
        mov.addObject("approvedDocuments", approvedDocuments);
        mov.addObject("documents", documents);
        mov.setViewName("documents");

        return mov;
    }

}
