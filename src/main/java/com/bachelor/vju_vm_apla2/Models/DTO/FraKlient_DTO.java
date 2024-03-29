package com.bachelor.vju_vm_apla2.Models.DTO;

import com.bachelor.vju_vm_apla2.Models.POJO.graphql.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FraKlient_DTO {

    private BrukerIdInput brukerId;
    private String fraDato;
    private String tilDato;
    private List<Journalposttype> journalposttyper;
    private List<Journalstatus> journalstatuser;
    private List<Tema> tema;
    //private Integer foerste;

    //private String etter;
}
