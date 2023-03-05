package es.mariaanasanz.proyecto6.ejercicios;

import javafx.scene.input.KeyCode;

import java.util.*;
import java.util.Map.Entry;

public class Estadisticas {

    private static LinkedHashMap<KeyCode, Integer> contadorEventosTeclado = new LinkedHashMap<>();
    private static HashMap<String, HashMap<String, Integer>> contadorObjetosRecogidos = new HashMap<>();
    private static ArrayList<Boolean> historicoDisparos = new ArrayList<>();

    public static void mostrarEstadisticasSeguro(){
        try {
            System.out.println("****************************************");
            mostrarEventosTeclado();
            System.out.println("****************************************");
            KeyCode code = teclaMasPulsada();
            if(code!=null) {
                System.out.println("La tecla que mas veces se ha pulsado ha sido: " + code.toString());
                System.out.println("****************************************");
            }
            mostrarObjetosRecogidos();
            System.out.println("****************************************");
            mostrarQuienHaRecogidoMasObjetos();
            System.out.println("****************************************");
            mostrarRatioPrecision();
            System.out.println("****************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void capturarEventoTecladoSeguro(KeyCode code) {
        try {
            capturarEventoTeclado(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void objetoRecogidoSeguro(String actor, String objeto) {
        try {
            objetoRecogido(actor, objeto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO: Captura todos los eventos que se produzcan durante la partida en el LinkedHashMap contadorEventosTeclado (10 puntos)
     * Acabaremos poblando un HashMap que contenga la cantidad de veces que ha ocurrido cada evento de teclado
     * Los eventos posibles que pueden llegar son:
     *      - KeyCode.RIGHT  --> para desplazarse a la derecha
     *      - KeyCode.LEFT   --> para desplazarse a la izquierda
     *      - KeyCode.SHIFT  --> para correr
     *      - KeyCode.ESCAPE --> para cerrar la ventana
     *      - KeyCode.ASTERISK --> Este evento no llegara, sino que cualquier otro evento lo almacenaremos como si fuese KeyCode.ASTERISK
     * Ejemplo de validacion:
     *      code == KeyCode.RIGHT   o bien   code == RIGHT   dependiendo de como hagais la validacion (IntelliJ os lo dira)
     * IMPORTANTE: Se debera recoger el tipo de evento mediante la clausula switch
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *
     * @param code el cogido de evento capturado
     */
    public static void capturarEventoTeclado(KeyCode code) {
        if(code==null){
            return;
        }
        Integer contador;
        switch (code){
            case RIGHT:
                code = KeyCode.RIGHT;
                break;
            case LEFT:
                code = KeyCode.LEFT;
                break;
            case SHIFT:
                code = KeyCode.SHIFT;
                break;
            case ESCAPE:
                code = KeyCode.ESCAPE;
                break;
            default:
                code = KeyCode.ASTERISK;
                break;
        }
        contador = contadorEventosTeclado.get(code);
        if(contador!=null){
            contador = Integer.valueOf(contador.intValue()+1);
        } else {
            contador = Integer.valueOf(1);
        }
        contadorEventosTeclado.put(code, contador);
    }

    /**
     * TODO: Se debera mostrar por consola toda la informacion del LinkedHashMap contadorEventosTeclado con el siguiente formato (el orden SI importa) (8 puntos)
     * Teclas pulsadas durante la partida:
     *      - RIGHT: 55 veces
     *      - LEFT: 43 veces
     *      - SHIFT: 2 veces
     *      - ESCAPE: 1 vez
     *      - OTROS: 243 veces
     * IMPORTANTE: Se debera emplear el metodo entrySet() para recorrer las entradas
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarEventosTeclado(){
        StringBuilder sb = new StringBuilder("Teclas pulsadas durante la partida:");
        Set<Entry<KeyCode, Integer>> entries = contadorEventosTeclado.entrySet();
        for (Entry<KeyCode, Integer> entry : entries) {
            Integer contador = entry.getValue();
            sb.append("\n\t- "+entry.getKey().toString()+": "+contador.toString());
            if(contador.intValue()==1){
                sb.append(" vez");
            } else {
                sb.append(" veces");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * TODO: debera devolver el evento con mas ocurrencias del LinkedHashMap contadorEventosTeclado (6 puntos)
     * IMPORTANTE: Se debera emplear el metodo keySet() para recorrer las entradas
     * IMPORTANTE: Si el juego se cierra sin pulsar ninguna tecla, devera devolver KeyCode.ESCAPE
     * @return KeyCode mas frecuente
     */
    public static KeyCode teclaMasPulsada(){
        Set<KeyCode> keys = contadorEventosTeclado.keySet();
        KeyCode teclaMasPulsada = KeyCode.ESCAPE;
        int maxOcurrencias = 0;
        for (KeyCode key : keys) {
            if(maxOcurrencias<contadorEventosTeclado.get(key)){
                maxOcurrencias = contadorEventosTeclado.get(key);
                teclaMasPulsada = key;
            }
        }
        return teclaMasPulsada;
    }

    /**
     * TODO: se debera almacenar la relacion de objetos que recoge cada actor en el HashMap contadorObjetosRecogidos (12 puntos)
     * IMPORTANTE: la primera clave del HashMap contadorObjetosRecogidos sera el actor
     * IMPORTANTE: para cada actor habra otro hashmap asociado con la relacion de objetos y las veces que estos se han recogido
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param actor sera o el jugador o la zarigueya
     * @param objeto sera o la comida o la gema
     */
    public static void objetoRecogido(String actor, String objeto){
        HashMap<String, Integer> objetosRecogidos = contadorObjetosRecogidos.get(actor);
        if(objetosRecogidos==null){
            objetosRecogidos = new HashMap<>();
        }
        Integer cantidadRecogida = objetosRecogidos.get(objeto);
        if(cantidadRecogida==null){
            cantidadRecogida = Integer.valueOf(0);
        }
        cantidadRecogida = Integer.valueOf(cantidadRecogida.intValue()+1);
        objetosRecogidos.put(objeto, cantidadRecogida);
        contadorObjetosRecogidos.put(actor, objetosRecogidos);
    }

    /**
     * TODO: Se debera mostrar por consola toda la informacion del HashMap contadorObjetosRecogidos con el siguiente formato (el orden no importa) (16 puntos)
     * Objetos recogidos durante la partida:
     *      - JUGADOR:
     *          - comida: 7 veces
     *          - gemas: 3 veces
     *      - ZARIGUEYA:
     *          - comida: 2 veces
     *          - gemas: 1 vez
     * IMPORTANTE: Se podra emplear el metodo deseado para recorrer las entradas
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarObjetosRecogidos(){
        StringBuilder sb = new StringBuilder("Objetos recogidos durante la partida:");
        Set<String> keys = contadorObjetosRecogidos.keySet();
        for(String key : keys){
            Set<Entry<String, Integer>> entries = contadorObjetosRecogidos.get(key).entrySet();
            sb.append("\n\t- "+key.toUpperCase()+":");
            for (Entry<String, Integer> entry : entries) {
                Integer contador = entry.getValue();
                sb.append("\n\t\t- "+entry.getKey()+": "+contador.toString());
                if(contador.intValue()==1){
                    sb.append(" vez");
                } else {
                    sb.append(" veces");
                }
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * TODO: Se debera mostrar por consola quien ha recogido mas objetos en base al HashMap contadorObjetosRecogidos con el siguiente formato (14 puntos)
     * Quien ha recogido mas objetos ha sido... ¡[JUGADOR/ZARIGUEYA] con un total de [XX] objetos!
     * IMPORTANTE: Se debera emplear el metodo values() para sumar la cantidad de objetos
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarQuienHaRecogidoMasObjetos(){
        StringBuilder sb = new StringBuilder("Quien ha recogido mas objetos ha sido... ");
        Set<String> keys = contadorObjetosRecogidos.keySet();
        int cantidadRecogida = 0;
        String actor = null;
        for(String key : keys){
            Collection<Integer> coleccionContador = contadorObjetosRecogidos.get(key).values();
            int auxCantidadRecogida = 0;
            for (Integer cantidad : coleccionContador) {
                auxCantidadRecogida = auxCantidadRecogida + cantidad.intValue();
            }
            if(cantidadRecogida<auxCantidadRecogida){
                cantidadRecogida = auxCantidadRecogida;
                actor = key;
            }
        }
        if(actor!=null){
            sb.append("¡"+actor.toUpperCase()+" con un total de "+cantidadRecogida+" ");
            if(cantidadRecogida==1){
                sb.append("objeto!");
            } else {
                sb.append("objetos!");
            }
        } else {
            sb.append("¡NADIE! No se han recogido objetos...");
        }
        System.out.println(sb.toString());
    }

    /**
     * TODO: Se debera incluir al ArrayList historicoDisparos los disparos que se efectuen durante el juego (el orden SI importa) (6 puntos)
     * AVISO: Cuando es certero, se invoca dos veces este metodo, no os preocupeis. Para eso luego teneis que implementar borrarDisparo
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param exito representa si el disparo es certero (true) o fallido (false)
     */
    public static void capturarDisparo(boolean exito){
        historicoDisparos.add(Boolean.valueOf(exito));
    }

    /**
     * TODO: Se debera eliminar el ULTIMO disparo QUE SE INDIQUE del ArrayList historicoDisparos (no vale eliminar cualquiera) (6 puntos)
     * AVISO: Es muy importante implementar correctamente este metodo para que el metodo mostrarRatioPrecision funcione correctamente
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param exito representa si el disparo es certero (true) o fallido (false)
     */
    public static void borrarDisparo(boolean exito){
        int index = historicoDisparos.lastIndexOf(Boolean.valueOf(exito));
        if(index>=0){
            historicoDisparos.remove(index);
        }
    }

    /**
     * TODO: Se debera mostrar por consola el porcentaje de precision en base al contenido del ArrayList historicoDisparos con el siguiente formato: (8 puntos)
     * Tienes una precision del [XX]%.
     * Adicionalmente, en base a la precision, se mostrara un mensaje adicional:
     *      Si la precision es entre un 67 y un 100% --> ¡Eres insuperable!
     *      Si la precision es entre un 34 y un 66% --> No esta nada mal
     *      Si la precision es entre un 0 y un 33% --> Deberias entrenar un poco mas...
     * IMPORTANTE: La precision se mide base a los disparos acertados entre el total de disparos
     * IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarRatioPrecision(){
        int aciertos = 0;
        for(Boolean exito : historicoDisparos){
            if(exito.booleanValue()){
                aciertos++;
            }
        }
        int precision = 0;
        if(aciertos>0){
            precision = (aciertos*100)/historicoDisparos.size();
        }
        StringBuilder sb = new StringBuilder("Tienes una precision del "+precision+"%. ");
        if(precision<34){
            sb.append("Deberias entrenar un poco mas...");
        } else if (precision<67) {
            sb.append("No esta nada mal");
        } else {
            sb.append("¡Eres insuperable!");
        }
        System.out.println(sb.toString());
    }
}
