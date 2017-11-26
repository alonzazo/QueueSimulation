package Model;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Estadisticas {
    // SE REGISTRAN LOS TOTALES PARA CALCULAR LOS DATOS AL FINALIZAR
    // TODO (a) the expected waiting time for a randomly selected job
    private Duration tiempoEsperandoEnColaTotal;
    private LinkedList<Duration> duracionesEnCola;

    // TODO (b) the expected response time;
    private Duration tiempoDeRespuestaTotal;

    // TODO (c) the expected length of a queue (excluding the jobs receiving service), when a new job arrives
    private double tamañoDeColaTotalPorLlegada;
    private LinkedList<Integer> longitudesEnCola;

    // TODO (d) the expected maximum waiting time during a 10-hour day
    private Duration tiempoDeEsperaMaximoTotal;
    private LinkedList<Integer> longitudesEnColaMax;

    // TODO (e) the expected maximum length of a queue during a 10-hour day
    private Duration tiempoDeEsperaMinimoTotal;

    // TODO (f) the probability that at least one server is available, when a job arrives
    private double vecesUnServidorDisponibleTotales;

    // TODO (g) the probability that at least two servers are available, when a job arrives
    private double vecesDosServidoresDisponiblesTotales;

    // (h) the expected number of jobs processed by each server
    private HashMap<String, Integer> trabajosProcesadosTotalesPorServidor = new HashMap<>();

    // TODO (i) the expected time each server is idle during the day
    private HashMap<String, Duration> tiempoOciosoTotalesPorServidor = new HashMap<>();

    // (j) the expected number of jobs still remaining in the system at 6:03 pm
    private double trabajosRestantesAlFinalizarTotales;

    public Estadisticas() {
        tiempoEsperandoEnColaTotal = Duration.ZERO;
        tiempoDeRespuestaTotal = Duration.ZERO;
        tamañoDeColaTotalPorLlegada = 0;
        tiempoDeEsperaMaximoTotal = Duration.ZERO;
        tiempoDeEsperaMinimoTotal = Duration.ZERO;
        vecesUnServidorDisponibleTotales = 0;
        vecesDosServidoresDisponiblesTotales = 0;
        trabajosProcesadosTotalesPorServidor = new HashMap<>();
        tiempoOciosoTotalesPorServidor = new HashMap<>();
        trabajosRestantesAlFinalizarTotales = 0;
        trabajosAbandonaronColaTotales = 0;
        simulacionesEjecutadas = 0;
        //llegadasTotales = 0;
        trabajosFinalizadosTotales = 0;
        longitudesEnCola = new LinkedList<Integer>();
        duracionesEnCola = new LinkedList<Duration>();
    }

    // TODO (k) the expected percentage of jobs that left the queue prematurely
    private double trabajosAbandonaronColaTotales;

    // para calcular (d), (e), (h), (i), (j)
    private int simulacionesEjecutadas;

    // para calcular (c), (f), (g), (k)
    //private int llegadasTotales;

    // para calcular (b)
    private int trabajosFinalizadosTotales;


    //Registra una llegada nueva
    public void añadirLlegadaNueva(Integer longitudDeCola) {
        longitudesEnCola.add(longitudDeCola);
    }

    //Registra un tiempo de espera en la cola. Nota: Si no pasó por cola el tiempo es 0.
    public void añadirTiempoDeEsperaEnCola(Duration esperaEnCola) {
        duracionesEnCola.add(esperaEnCola);
    }

    // TODO enviar tiempo de respuesta en SistemaColas.java
    public void añadirTrabajoFinalizado(String nombreServidor /*, Duration tiempoRespuesta*/) {
        if (trabajosProcesadosTotalesPorServidor.containsKey(nombreServidor)) {
            trabajosProcesadosTotalesPorServidor.put(nombreServidor, 1 + trabajosProcesadosTotalesPorServidor.get(nombreServidor));
        } else {
            trabajosProcesadosTotalesPorServidor.put(nombreServidor, 1);
        }

        trabajosFinalizadosTotales++;
        //tiempoDeRespuestaTotal.plus(tiempoRespuesta);
    }

    public void añadirTrabajosRestantesAlFinalizar(int trabajos) {
        trabajosRestantesAlFinalizarTotales += trabajos;
    }

    public void añadirSimulacion() {
        simulacionesEjecutadas++;
    }

   /*public void añadirLlegada(){

        llegadasTotales++;
    }*/

    public String procesarDatos() {
        String resultado = "Simulaciones ejecutadas: " + simulacionesEjecutadas + "\n";

        //Cálculo del tiempo esperado de espera de un trabajo
        double waitingTime = 0;
        for (Duration duration : duracionesEnCola) waitingTime += duration.toMinutes();
        resultado += "(a) Tiempo esperado de espera de un trabajo cualquiera (Wq): " + waitingTime / duracionesEnCola.size() + " min\n";

        double longitud = 0;
        for (Integer i : longitudesEnCola) longitud += i;
        resultado += "(c) Longitud esperada de Cola (Lq):" + longitud / longitudesEnCola.size() + " trabajos\n";

        double max = 0;

        for (Integer i : longitudesEnCola) {
            if (i > max) {
                max = i;
            }
        }

            resultado += "(e) Longitud máxima esperada en la cola es: " + max +" trabajos \n";


            resultado += "(h) Trabajos procesados esperados: " + ((double) trabajosFinalizadosTotales / simulacionesEjecutadas);
            for (String nombre : trabajosProcesadosTotalesPorServidor.keySet()) {
                resultado += "\n  procesados por " + nombre + ": " + ((double) trabajosProcesadosTotalesPorServidor.get(nombre) / simulacionesEjecutadas);
            }
            resultado += "\n(j) Trabajos restantes esperados a las 6:03pm: " + ((double) trabajosRestantesAlFinalizarTotales / simulacionesEjecutadas) + "\n";


            return resultado;
        }
    }

