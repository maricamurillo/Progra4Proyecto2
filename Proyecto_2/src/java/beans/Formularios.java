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

public class Formularios implements Serializable {

    public static String campoEtiquetado(String etiqueta, String id, String clave) {
        StringBuilder contenidos = new StringBuilder();
        contenidos.append("<tr>");
        contenidos.append(String.format("<td class=\"etiqueta\">%s</td>", etiqueta));
        contenidos.append("<td class=\"campo\">");
        if ("1".equals(clave)) {
            contenidos.append(String.format("<input type=\"password\" required id=\"%1$s\" name=\"%1$s\" size=\"30\" />", id));
        } else if ("2".equals(clave)) {
            contenidos.append(String.format("<p>%s</p>", id));
        } else {
            contenidos.append(String.format("<input type=\"text\" id=\"%1$s\" name=\"%1$s\" size=\"30\" />", id));
        }
        contenidos.append("</td>");
        contenidos.append("</tr>");
        return contenidos.toString();
    }

    public static String campoBoton(String boton, String id, String tr) {
        StringBuilder contenidos = new StringBuilder();
        if ("1".equals(tr)) {
            contenidos.append("<tr>");
            contenidos.append("<td>");
        }
        contenidos.append(String.format("<button id=\"%1$s\" name=\"%1$s\" type=\"submit\">%2$s</button>", id, boton));
        if ("1".equals(tr)) {
            contenidos.append("</td>");
            contenidos.append("</tr>");
        }
        return contenidos.toString();
    }

    public static String campoArchivo(String etiqueta, String id) {
        StringBuilder contenidos = new StringBuilder();
        contenidos.append("<tr>");
        contenidos.append(String.format("<td class=\"etiqueta\">%s</td>", etiqueta));
        contenidos.append("<td class=\"campo\">");
        contenidos.append(String.format("<input type=\"file\" id=\"%1$s\" name=\"%1$s\" />", id));
        contenidos.append("</td>");
        contenidos.append("</tr>");
        return contenidos.toString();
    }

    public static String campoTextarea(String etiqueta, String id, String rows, String cols) {
        StringBuilder contenidos = new StringBuilder();
        contenidos.append("<tr>");
        contenidos.append(String.format("<td class=\"etiqueta\">%s</td>", etiqueta));
        contenidos.append("<td class=\"campo\">");
        contenidos.append(String.format("<textarea id=\"%1$s\" name=\"%1$s\" rows=\"%2$s\" cols=\"%3$s\"></textarea>", id, rows, cols));
        contenidos.append("</td>");
        contenidos.append("</tr>");
        return contenidos.toString();
    }

    public static String campoFecha(String etiqueta, String id) {
        StringBuilder contenidos = new StringBuilder();
        contenidos.append("<tr>");
        contenidos.append(String.format("<td class=\"etiqueta\">%s</td>", etiqueta));
        contenidos.append("<td class=\"campo\">");
        contenidos.append(String.format("<input type=\"date\" required id=\"%1$s\" name=\"%1$s\" />", id));
        contenidos.append("</td>");
        contenidos.append("</tr>");
        return contenidos.toString();
    }
}
