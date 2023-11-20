package com.unit.sivo.viewModels;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorViewModel {
    private String email;

    private String senha;

    private String matricula;

    private List<Integer> disciplinas;
}
