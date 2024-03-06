package com.bachelor.vju_vm_apla2;

import com.bachelor.vju_vm_apla2.Controller.JournalpostController;
import com.bachelor.vju_vm_apla2.Service.SimpleService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.Body;
import com.sun.tools.javac.Main;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import lombok.SneakyThrows;
import org.json.HTTP;
import org.junit.Test;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;
import wiremock.org.checkerframework.checker.index.qual.IndexFor;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames.map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class JournalPostControllerTest {

    @Mock
    SimpleService serviceMock;
    @InjectMocks
    JournalpostController jpController;


    @InjectMocks
    JournalpostController jpContrllerInject = new JournalpostController(serviceMock);


    @Test
    public void hentJournalpostTest() {
        String brukerid = "asdsd12123312";
        HttpHeaders headers = new HttpHeaders();

        Mockito.when(serviceMock.hentJournalpostListe(anyString(), any(HttpHeaders.class))).thenReturn("en banan, to banan");


        headers.add("Authorization", "Bearer ");
        ResponseEntity<String> res = jpController.hentJournalpostListe(brukerid, headers);
        ResponseEntity<String> cmp = ResponseEntity.status(org.springframework.http.HttpStatus.OK).header("Content-Type", "application/json").header("Content-Disposition", "inline").body("en banan, to banan");
        assertEquals(cmp, res);
    }

    @SneakyThrows
    @Test
    public void hentDokumenterTest() {
        Path resources = Paths.get("/src", "test", "resources", "example.pdf");
        String pathToRes = resources.toFile().getPath();
        String dokumentId = "000001";
        HttpHeaders headers = new HttpHeaders();
        byte[]fakePDF = "Cool pdfs".getBytes(StandardCharsets.UTF_8);
        ByteArrayResource fakePDFresource = new ByteArrayResource(fakePDF);

        Mockito.when(serviceMock.hentDokument(dokumentId, headers)).thenReturn(Mono.just(fakePDFresource));
        String cmp = String.valueOf(Mono.just(ResponseEntity.status(HttpStatus.OK).headers(headers).contentType(MediaType.APPLICATION_PDF).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"document.pdf\"").body(fakePDFresource)).block());
      String res = String.valueOf(jpController.hentDokument(dokumentId, headers).block());

      assertEquals(cmp, res);
    }

 /*   @Test
    public void hentDokumenterTest(){

    }*/
}
