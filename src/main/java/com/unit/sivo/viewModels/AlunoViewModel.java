package com.unit.sivo.viewModels;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoViewModel {
    private int id;
    
    private String nome;

    private String email;

    private String senha;

    private String matricula;

    private int curso_id;

    private List<Integer> disciplinas;
}
