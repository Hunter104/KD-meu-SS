package Modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Essa classe é o molde para transformar o codigo de uma materia da unb (12M12) em um formato tratavel.
 */
public class Horario {
    public static final Pattern pattern = Pattern.compile("^(\\d+)([MTN])(\\d+)$");
    public Set<Integer> dia = new HashSet<>();      //Lista de dias da materia (2 = segunda, 3 = terça...)
    public Set<Integer> hora = new HashSet<>();     //Lista de horario da materia (1 = primeiro horario...)
    public String turno = "";                       //Lista de turnos da materia (M = manha...)
    public Horario(String codigo){
        String[] partes = codigo.split("\\s+");
        for (String parte : partes) {
            formatador(parte);
        }
    }
    public void formatador(String codigo){
        Matcher matcher = pattern.matcher(codigo);
        if (matcher.matches()) {
            String dias = matcher.group(1);
                for(char index : dias.toCharArray()) dia.add(Character.getNumericValue(index));
            String horas = matcher.group(3);
                for(char index : horas.toCharArray()) hora.add(Character.getNumericValue(index));
            this.turno = turno + matcher.group(2);
        }
    }
    public String toString() { return "DIA: " + dia + " | HORA: " + hora + " | TURNO: " + turno; }
}