package Modelo;

public class Materia {
    private String nome;
    private String professor;
    private String codigo;
    private Horario horario;
    public Materia(String nome, String professor, String codigo){
        if(nome!=null) this.nome = nome;
        if(professor!=null)this.professor = professor;
        if(codigo!=null)this.codigo = codigo;
        this.horario = new Horario(codigo);
    }
    public String getNome(){
        return nome;
    }
    public String getProfessor(){
        return professor;
    }
    public String getCodigo(){
        return codigo;
    }
    public Horario getHorario(){
        return horario;
    }
    public void setProfessor(String novoProfessor){
        this.professor = novoProfessor;
    }
    public String toString(){
        return "\nMateria: " + nome + " | Professor: " + professor +
                " | Codigo: " + codigo;
    }
}
