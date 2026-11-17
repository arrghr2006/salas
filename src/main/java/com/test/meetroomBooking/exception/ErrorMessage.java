package com.test.meetroomBooking.exception;

public class ErrorMessage {
    public static final String DUPLICATED_MEETROOM = "El nombre de la sala especificado ya esta registrado en el sistema.";
    public static final String INTEGRITY_VIOLATION = "El id especificado no existe en nuestro sistema";
    public static final String MEETROOM_NOT_FOUND = "Sala no encontrada";
    public static final String MEETROOMBOOKING_NOT_FOUND = "Reservacion no encontrada";
    public static final String MEETROOM_RESERVED_IN_PERIOD = "Sala reservada en el periodo especificado";
    public static final String VALIDATION_PARAMS = "Parámetros no válidos";
    public static final String INTERNAL_ERROR = "Error interno en la operación";
    public static final String NO_VALID_DATA = "Datos inconsistentes en base de datos.";

    private ErrorMessage(){
        throw new IllegalStateException("Utility class");
    }
}
