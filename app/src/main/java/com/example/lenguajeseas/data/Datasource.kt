package com.example.lenguajeseas.data

import com.example.lenguajeseas.R
import com.example.lenguajeseas.model.Comida
import com.example.lenguajeseas.model.Mes
import com.example.lenguajeseas.model.Semana

class Datasource {
    fun cargaComida(): List<Comida>{
        return listOf<Comida>(
            Comida(R.string.comida1, R.drawable.cenar),
            Comida(R.string.comida2, R.drawable.comer),
            Comida(R.string.comida3, R.drawable.desayunar),
            Comida(R.string.comida4, R.drawable.merendar),
        )
    }

    fun cargaSemana(): List<Semana>{
        return listOf<Semana>(
            Semana(R.string.semana1, R.drawable.lunes),
            Semana(R.string.semana2, R.drawable.martes),
            Semana(R.string.semana3, R.drawable.miercoles),
            Semana(R.string.semana4, R.drawable.jueves),
            Semana(R.string.semana5, R.drawable.viernes),
            Semana(R.string.semana6, R.drawable.sabado),
            Semana(R.string.semana7, R.drawable.domingo),
        )
    }

    fun cargaMeses(): List<Mes>{
        return listOf<Mes>(
            Mes(R.string.mes1, R.drawable.meses),
            Mes(R.string.mes2, R.drawable.enero),
            Mes(R.string.mes3, R.drawable.febrero),
            Mes(R.string.mes4, R.drawable.marzo),
            Mes(R.string.mes5, R.drawable.abril),
            Mes(R.string.mes6, R.drawable.mayo),
            Mes(R.string.mes7, R.drawable.junio),
            Mes(R.string.mes8, R.drawable.julio),
            Mes(R.string.mes9, R.drawable.agosto),
            Mes(R.string.mes10, R.drawable.septiembre),
            Mes(R.string.mes11, R.drawable.octubre),
            Mes(R.string.mes12, R.drawable.noviembre),
            Mes(R.string.mes13, R.drawable.diciembre),
        )
    }



}