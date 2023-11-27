package com.unit.sivo.viewModels;

import java.util.List;

import com.unit.sivo.enums.SituacaoProjeto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjetoViewModel {
    private String nome;

    private String descricao;

    private String cronograma;

    private SituacaoProjeto situacao;

    private int professor_id;

    private int vagas;

    private int duracao;

    private List<Integer> disciplinas;
}
