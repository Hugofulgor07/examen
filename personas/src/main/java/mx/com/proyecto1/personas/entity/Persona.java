package mx.com.proyecto1.personas.entity;

public class Persona {

    private Long idPersona;
    private String nombrePersona;
    private String correo;

    public Persona() {
    }

    public Persona(Long idPersona, String nombrePersona, String correo) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.correo = correo;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombrePersona=" + nombrePersona +
                ", correo=" + correo +
                '}';
    }

}
