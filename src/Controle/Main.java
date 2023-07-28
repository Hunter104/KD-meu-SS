package Controle;
import java.util.ArrayList;

import Modelo.Horario;
import Modelo.Materia;
import Modelo.Memoria;
public class Main {
    public static void main(String[] args) {
        Memoria memoria = new Memoria();
        memoria.addMateria("EDA", "NILTON", "35M34");
        memoria.addMateria("EDA", "ROSE", "35M34");
        memoria.addMateria("EDA", "ROSE", "35T23");

        memoria.addMateria("FAC", "TIAGO", "24M34");
        memoria.addMateria("FAC", "JOHN", "46T45");

        memoria.addMateria("MDS", "HILMER", "35T45");
        memoria.addMateria("MDS", "GEORGE", "35T23");
        memoria.addMateria("MDS", "CARLA", "46M34");
        memoria.addMateria("MDS", "RICARDO", "24T23");

        memoria.addMateria("GPQ", "REJANE", "35T23");
        memoria.addMateria("GPQ", "MARIO", "35T45");

        memoria.addMateria("MD2", "MATHEUS", "26T23");

        memoria.addMateria("PJ1", "tanto faz 1", "24T45");
        memoria.addMateria("PJ1", "tanto faz 2", "24T45");
        memoria.addMateria("PJ1", "tanto faz 3", "24T45");
        memoria.addMateria("PJ1", "tanto faz 4", "24T45");
        memoria.addMateria("PJ1", "tanto faz 5", "24T45");

        memoria.addMateria("teste", "teste", "1T45");

        ArrayList<Materia> teste = new ArrayList<>();
        Materia lau = new Materia("EDA", "nenhum", "nenhum");
        Materia lau1 = new Materia("FAC", "nenhum", "nenhum");
        Materia lau2 = new Materia("MDS", "nenhum", "nenhum");
        Materia lau3 = new Materia("GPQ", "nenhum", "nenhum");
        Materia lau4 = new Materia("MD2", "nenhum", "nenhum");
        Materia lau5 = new Materia("PJ1", "tanto faz 2", "nenhum");
        Materia lau6 = new Materia("teste", "nenhum", "nenhum");
        teste.add(lau);
        teste.add(lau1);
        teste.add(lau2);
        teste.add(lau3);
        teste.add(lau4);
        teste.add(lau5);
       // teste.add(lau6);

        Controle controle = new Controle(memoria);
        controle.organizarGrades(teste);

        System.out.println(controle.verificadorConflitosGrades(controle.organizarGrades(teste)));

        System.out.println("TODAS AS COMBINAÇÕES POSSIVEIS: " + controle.organizarGrades(teste).size());

        System.out.println("GRADES COM PREFERENCIAS SETADAS: " + controle.verificadorConflitosGrades(controle.organizarGrades(teste)).size());

        Horario horario = new Horario("24M25");
        Horario horario2 = new Horario("245M25");
        Horario horario3 = new Horario("34M5 34T1");
        System.out.println(horario.toString());
        System.out.println(horario2.toString());
        System.out.println(horario3.toString());
    }
}
