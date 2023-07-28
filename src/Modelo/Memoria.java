package Modelo;
import java.util.ArrayList;

public class Memoria {
    private ArrayList<Materia> materias = new ArrayList<>();
    //GETTERS
    public ArrayList<Materia> getMaterias(){
        return materias;
    }
    public void setMaterias(ArrayList<Materia> novaMaterias){
        this.materias = novaMaterias;
    }
    public void addMateria(String nome, String professor, String codigo){
        materias.add(new Materia(nome, professor, codigo));
    }
}
