package com.wgaray.appmonitoreo.ui.screens.educacion

import com.wgaray.appmonitoreo.R
import com.wgaray.appmonitoreo.domain.model.BloqueContenido

val contenidoPorPaso = mapOf(
    1 to listOf(
        BloqueContenido.Parrafo("La preeclampsia es una complicación que ocurre durante el embarazo, generalmente después de la semana 20 de gestación, aunque puede presentarse incluso después del parto. Se caracteriza principalmente por un aumento significativo de la presión arterial y la presencia de proteínas en la orina (proteinuria), lo que indica un posible daño en los riñones. Además, puede afectar otros órganos como el hígado, provocando síntomas como dolores de cabeza intensos, visión borrosa, dolor abdominal, náuseas o alteraciones en los valores de laboratorio. La preeclampsia puede progresar rápidamente y representa un riesgo tanto para la madre como para el bebé si no se detecta y trata a tiempo."),
        BloqueContenido.Subtitulo("¿Qué la causa?"),
        BloqueContenido.Parrafo("El único tratamiento definitivo para la preeclampsia es el nacimiento del bebé, ya que una vez finalizado el embarazo, los síntomas suelen mejorar. Sin embargo, dependiendo de la gravedad y del momento en que aparezca, el médico puede optar por monitorear muy de cerca a la paciente, recetar medicamentos para controlar la presión arterial o incluso inducir el parto antes de tiempo si la salud de la madre o el feto corre peligro. Las mujeres con antecedentes de hipertensión crónica, diabetes, lupus o quienes tengan una historia familiar de preeclampsia tienen mayor riesgo de desarrollarla. Por eso, es fundamental realizar controles prenatales regulares para detectar cualquier cambio temprano y manejar adecuadamente esta condición."),
        BloqueContenido.Imagen(R.drawable.preeclampsia_info),
        BloqueContenido.Lista(listOf("Presión alta", "Proteína en la orina", "Hinchazón de rostro"))
    ),
    2 to listOf(
        BloqueContenido.Subtitulo("Síntomas comunes"),
        BloqueContenido.Lista(listOf("Dolor de cabeza", "Visión borrosa", "Dolor abdominal"))
    )
    // Agrega más contenido para los pasos restantes
)
