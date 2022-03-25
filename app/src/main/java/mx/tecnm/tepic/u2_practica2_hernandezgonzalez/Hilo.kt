package mx.tecnm.tepic.u2_practica2_hernandezgonzalez

import android.media.MediaPlayer

class Hilo(este:MainActivity) : Thread() {
    val este = este
    var ejecutar = false
    var pausar = false
    var cRestantes=0
    var finJuego = false

    override fun run() {
        super.run()
        var indice = 0
        while (ejecutar){
            if(!pausar){
                este.runOnUiThread {
                    if(indice < 54) {
                        cRestantes = 53 - indice
                        este.binding.imagen.setImageResource(este.cartas.get(este.index.get(indice)))
                        var audio = MediaPlayer.create(este,este.audios.get(este.index.get(indice)))
                        audio.start()
                        if (!finJuego) {
                            este.binding.texto.text = "Cartas restantes:${cRestantes}"
                        }
                        indice++
                    }
                    if(indice == 54){
                        sleep(5000)
                        este.binding.texto.text = "MUCHAS GRACIAS POR USAR MI APLICACION"
                        pausarHilo()
                    }

                }
                sleep(5000)
            }
        }
    }

    fun terminarHilo() {
        ejecutar = false
    }

    fun pausarHilo() {
        pausar = true
    }

    fun despausarHilo(){
        pausar = false
    }

    fun estaPausado() : Boolean {
        return pausar
    }
}