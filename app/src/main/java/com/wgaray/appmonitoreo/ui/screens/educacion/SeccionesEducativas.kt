package com.wgaray.appmonitoreo.ui.screens.educacion

import com.wgaray.appmonitoreo.R
import com.wgaray.appmonitoreo.domain.model.BloqueContenido

val contenidoPorPaso = mapOf(
    1 to listOf(
        BloqueContenido.Subtitulo("¿Qué es la preeclampsia?"),
        BloqueContenido.Parrafo("La preeclampsia es una complicación grave que ocurre durante el embarazo. Se caracteriza por presión arterial alta y daño a órganos como los riñones o el hígado. Puede aparecer después de la semana 20 y, sin tratamiento, poner en riesgo la vida de la madre y del bebé."),
        BloqueContenido.Imagen(R.drawable.preeclampsia_info),
        BloqueContenido.Cita("Detectarla a tiempo y seguir los controles médicos es clave para prevenir complicaciones."),
        BloqueContenido.Separador,
        BloqueContenido.Parrafo("El tratamiento definitivo es el nacimiento del bebé, pero la vigilancia médica puede evitar que la situación se agrave."),
        BloqueContenido.Video("https://www.youtube.com/watch?v=hMKCNhasDek")
    ),

    2 to listOf(
        BloqueContenido.Subtitulo("Factores de riesgo"),
        BloqueContenido.Parrafo("Conocer los factores de riesgo ayuda a tomar medidas preventivas. Algunas mujeres tienen mayor probabilidad de desarrollar preeclampsia."),
        BloqueContenido.Lista(listOf(
            "Primer embarazo",
            "Embarazo múltiple (gemelos, trillizos)",
            "Antecedentes familiares de preeclampsia",
            "Edad materna menor de 18 o mayor de 35 años",
            "Obesidad o sobrepeso",
            "Hipertensión arterial previa",
            "Diabetes, enfermedades renales o autoinmunes"
        )),
        BloqueContenido.Cita("Si tienes alguno de estos factores, infórmalo en tus controles prenatales."),
        BloqueContenido.Alerta("El control prenatal es tu mejor herramienta para prevenir complicaciones."),
        BloqueContenido.Separador
    ),

    3 to listOf(
        BloqueContenido.Subtitulo("¿Cómo prevenir la preeclampsia?"),
        BloqueContenido.Parrafo("No siempre se puede prevenir, pero hay acciones que reducen el riesgo y ayudan a detectarla a tiempo."),
        BloqueContenido.Lista(listOf(
            "Asiste a todos tus controles prenatales",
            "Sigue una dieta saludable y baja en sal",
            "Mantén un peso adecuado",
            "Evita el consumo de tabaco y alcohol",
            "Realiza actividad física moderada (según recomendación médica)",
            "Informa a tu médico sobre cualquier síntoma o malestar"
        )),
        BloqueContenido.Cita("La prevención es un trabajo conjunto entre tú y el personal de salud."),
        BloqueContenido.Parrafo("La detección temprana salva vidas. No faltes a tus controles y consulta ante cualquier duda."),
        BloqueContenido.Video("https://www.youtube.com/watch?v=6QKQbQwQK6w")
    ),

    4 to listOf(
        BloqueContenido.Subtitulo("¿Cuándo acudir al médico?"),
        BloqueContenido.Parrafo("Es vital reconocer los síntomas de alarma y buscar atención médica de inmediato."),
        BloqueContenido.Lista(listOf(
            "Dolor de cabeza intenso que no cede",
            "Visión borrosa o pérdida de visión",
            "Dolor abdominal fuerte",
            "Disminución o ausencia de movimientos del bebé",
            "Hinchazón repentina de rostro, manos o pies",
            "Dificultad para respirar"
        )),
        BloqueContenido.Alerta("No esperes a que los síntomas empeoren. Acude al centro de salud de inmediato."),
        BloqueContenido.Cita("Tu salud y la de tu bebé son lo más importante. Ante cualquier duda, consulta."),
        BloqueContenido.Parrafo("El equipo de salud está para ayudarte. No tengas miedo ni vergüenza de acudir a una consulta de emergencia."),
        BloqueContenido.Video("https://www.youtube.com/watch?v=QwQnQwQwQwQ")
    ),
    5 to listOf(
        BloqueContenido.Subtitulo("¿Cuándo ir al médico?"),
        BloqueContenido.Parrafo("Reconocer los síntomas de alarma y actuar rápido puede salvar vidas. No ignores las señales de tu cuerpo."),
        BloqueContenido.Lista(listOf(
            "Dolor de cabeza intenso y persistente",
            "Visión borrosa, manchas o pérdida de visión",
            "Dolor abdominal fuerte, especialmente en la parte superior",
            "Disminución o ausencia de movimientos del bebé",
            "Hinchazón repentina de rostro, manos o pies",
            "Dificultad para respirar o dolor en el pecho"
        )),
        BloqueContenido.Alerta("Si presentas alguno de estos síntomas, acude al centro de salud de inmediato. No esperes a que se agraven."),
        BloqueContenido.Cita("Ante la duda, es mejor consultar. Tu salud y la de tu bebé son lo más importante."),
        BloqueContenido.Parrafo("El personal de salud está para ayudarte. No tengas miedo ni vergüenza de acudir a una consulta de emergencia."),
        BloqueContenido.Video("https://www.youtube.com/watch?v=QwQnQwQwQwQ")
    )
)
