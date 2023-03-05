package es.mariaanasanz.proyecto6.simulador;

import es.mariaanasanz.proyecto6.ejercicios.Estadisticas;
import javafx.scene.input.KeyCode;

public class Simulador {

    /**
     * TODO: Debereis simular los datos de una partida con el objetivo de validar vuestros metodos implementados (14 puntos)
     * Es decir, tendreis que llamar desde aqui a todos vuestros metodos:
     *      - capturarEventoTeclado
     *      - mostrarEventosTeclado
     *      - teclaMasPulsada
     *      - objetoRecogido
     *      - mostrarObjetosRecogidos
     *      - mostrarQuienHaRecogidoMasObjetos
     *      - capturarDisparo
     *      - borrarDisparo
     *      - mostrarRatioPrecision
     * IMPORTANTE: tendreis que validar que llegan los parametros correctos
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * Para ello, se emplearan los argumentos de entrada de la siguiente manera:
     * @param args numFlechasIzquierda numFlechasDerecha numShift numEscape numOtrasTeclas numComidaJugador
     *             numJoyaJugador numComidaZarigueya numJoyaZarigueya numDisparosCerteros numDisparosFallidos numDisparosCerterosBorrar numDisparosFallidosBorrar
     */
    public static void main(String[] args) {
        if(args.length!=13){
            System.out.println("Lo siento, no has introducido la cantidad de parametros necesarios para la simulacion.");
            return;
        }
        for(String arg : args){
            try {
                Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                System.out.println("Lo siento, todos los parametros deben ser numericos.");
                return;
            }
        }
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.LEFT);
        }
        for (int i = 0; i < Integer.parseInt(args[1]); i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.RIGHT);
        }
        for (int i = 0; i < Integer.parseInt(args[2]); i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.SHIFT);
        }
        for (int i = 0; i < Integer.parseInt(args[3]); i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.ESCAPE);
        }
        for (int i = 0; i < Integer.parseInt(args[4]); i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.ASTERISK);
        }
        for (int i = 0; i < Integer.parseInt(args[5]); i++) {
            Estadisticas.objetoRecogido("jugador", "comida");
        }
        for (int i = 0; i < Integer.parseInt(args[6]); i++) {
            Estadisticas.objetoRecogido("jugador", "gema");
        }
        for (int i = 0; i < Integer.parseInt(args[7]); i++) {
            Estadisticas.objetoRecogido("zarigueya", "comida");
        }
        for (int i = 0; i < Integer.parseInt(args[8]); i++) {
            Estadisticas.objetoRecogido("zarigueya", "gema");
        }
        for (int i = 0; i < Integer.parseInt(args[9]); i++) {
            Estadisticas.capturarDisparo(true);
        }
        for (int i = 0; i < Integer.parseInt(args[10]); i++) {
            Estadisticas.capturarDisparo(false);
        }
        for (int i = 0; i < Integer.parseInt(args[11]); i++) {
            Estadisticas.borrarDisparo(true);
        }
        for (int i = 0; i < Integer.parseInt(args[12]); i++) {
            Estadisticas.borrarDisparo(false);
        }
        System.out.println("****************************************");
        Estadisticas.mostrarEventosTeclado();
        System.out.println("****************************************");
        KeyCode code = Estadisticas.teclaMasPulsada();
        if(code!=null) {
            System.out.println("La tecla que mas veces se ha pulsado ha sido: " + code.toString());
            System.out.println("****************************************");
        }
        Estadisticas.mostrarObjetosRecogidos();
        System.out.println("****************************************");
        Estadisticas.mostrarQuienHaRecogidoMasObjetos();
        System.out.println("****************************************");
        Estadisticas.mostrarRatioPrecision();
        System.out.println("****************************************");
    }

}
