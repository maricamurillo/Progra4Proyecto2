/*
    Formularios.css

    EIF209 - Programación 4 – Proyecto #1 
    Abril 2019

    Autores:
            - 113030275 Mariela Cambronero
            - 111320128 Rodrigo Rodriguez

*/
package beans;

import java.io.Serializable;
public class Formularios implements Serializable{
    public static String campoEtiquetado(String etiqueta, String id, String clave) {
        StringBuilder contenidos = new StringBuilder();
        contenidos.append("<tr>");
        contenidos.append(String.format("<td class=\"etiqueta\">%s</td>", etiqueta));
        contenidos.append("<td class=\"campo\">");
        if("1".equals(clave)){contenidos.append(String.format("<input type=\"password\" id=\"%1$s\" name=\"%1$s\" size=\"30\" />", id));}
        else contenidos.append(String.format("<input type=\"text\" id=\"%1$s\" name=\"%1$s\" size=\"30\" />", id));
        contenidos.append("</td>");
        contenidos.append("</tr>");
        return contenidos.toString();
    }
    
    public static String campoBoton(String boton, String id, String tr){
        StringBuilder contenidos = new StringBuilder();
        if("1".equals(tr)){
            contenidos.append("<tr>");
            contenidos.append("<td>");
        }
        contenidos.append(String.format( "<button id=\"%1$s\" name=\"%1$s\" type=\"submit\">%2$s</button>",id,boton));
        if("1".equals(tr)){
            contenidos.append("</td>");
            contenidos.append("</tr>");
        }
        return contenidos.toString();
    }
    
   
}

