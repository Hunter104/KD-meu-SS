package Controle;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import Modelo.Grade;
import Modelo.Horario;
import Modelo.Materia;
import Modelo.Memoria;
public class Controle {
    private final Memoria memoria;
    public Controle(Memoria memoria){
        this.memoria = memoria;
    }
    //TODAS AS POSSIBILIDADES DE MATERIA
    public ArrayList<ArrayList<Materia>> possibilidades(ArrayList<Materia> escolhidas){
        ArrayList<ArrayList<Materia>> possibilidades = new ArrayList<>();

        for (Materia index : escolhidas) {
            ArrayList<Materia> blocosDiciplinas = new ArrayList<>();
            for (Materia indexA : memoria.getMaterias()) {
                if (index.getNome().equals(indexA.getNome()) &&
                        (index.getProfessor().equals("nenhum") || index.getProfessor().equals(indexA.getProfessor())) &&
                        (index.getCodigo().equals("nenhum") || index.getCodigo().equals(indexA.getCodigo()))) {

                    if(!blocosDiciplinas.isEmpty() && blocosDiciplinas.get(0).getCodigo().equals(indexA.getCodigo())){
                        String novoNome = indexA.getProfessor() + "|" + blocosDiciplinas.get(0).getProfessor();
                        Materia materia = new Materia(indexA.getNome(), novoNome, indexA.getCodigo());
                        blocosDiciplinas.remove(blocosDiciplinas.get(0));
                        blocosDiciplinas.add(materia);
                    }
                    else blocosDiciplinas.add(indexA);
                }
            }
            possibilidades.add(blocosDiciplinas);
        }
        return possibilidades;
    }

    /**
     * Esse método é responsável por receber o array de arrays do método possibilidades e transformar e grades através da regra do tracinho (MD1).
     *
     * @param indiceAtual
     * @param combinacaoAtual
     * @param dados Conjuntos de materias a serem combinadas (dados a serem combinados).
     * @param grades Resultado final com a lista de grades obtidas.
     */
    private void gerarCombinacoes(int indiceAtual, ArrayList<Materia> combinacaoAtual, ArrayList<ArrayList<Materia>> dados, ArrayList<Grade> grades){
        if (indiceAtual == dados.size()) {
            ArrayList<Materia> gradeAtual = new ArrayList<>(combinacaoAtual);
            Set<String> codigosAtuais = gradeAtual.stream().map(Materia::getCodigo).collect(Collectors.toSet());
            if(gradeAtual.size()==codigosAtuais.size()) grades.add(new Grade(new ArrayList<>(combinacaoAtual)));

            return;
        }
        for (Materia index : dados.get(indiceAtual)) {
            combinacaoAtual.add(index);
            gerarCombinacoes(indiceAtual + 1, combinacaoAtual, dados, grades);
            combinacaoAtual.remove(combinacaoAtual.size() - 1);
        }
    }
    //TODAS AS GRADES POSSIVEIS
    public ArrayList<Grade> organizarGrades(ArrayList<Materia> escolhidas) {
        ArrayList<Grade> grades = new ArrayList<>();
        gerarCombinacoes(0, new ArrayList<>(), possibilidades(escolhidas), grades);
        return grades;
    }
    public boolean verificadorConflitos(Materia primeira, Materia segunda){
        Horario horarioPrimeira = primeira.getHorario();
        Horario horarioSegunda = segunda.getHorario();

        if (!horarioPrimeira.turno.equals(horarioSegunda.turno)) return false;

        List<Integer> horasConflitantes = new ArrayList<>(horarioPrimeira.hora);
        horasConflitantes.retainAll(horarioSegunda.hora);
            if((horasConflitantes.size() < 2)) return false;

        List<Integer> diasConflitantes = new ArrayList<>(horarioPrimeira.dia);
        diasConflitantes.retainAll(horarioSegunda.dia);
            //Se chegou até aqui significa que existe colisão de horários retornará true se tiver colisão de dias
             return diasConflitantes.size() >= 1;
    }
    public boolean verificadorConflitosArrays(ArrayList<Materia> materias) {
        int numMaterias = materias.size();
        return IntStream.range(0, numMaterias - 1)
                .anyMatch(i -> IntStream.range(i + 1, numMaterias)
                        .anyMatch(j -> verificadorConflitos(materias.get(j), materias.get(i))));
    }
    public ArrayList<Grade> verificadorConflitosGrades(ArrayList<Grade> grades){
        return grades.stream()
                .filter(grade -> !verificadorConflitosArrays(grade.materias()))
                    .collect(Collectors.toCollection(ArrayList::new));
    }
}
