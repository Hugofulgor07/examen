package mx.com.proyecto1.personas.controller;

import com.google.gson.Gson;
import mx.com.proyecto1.personas.entity.Persona;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/persona")
public class PersonaController {

    final AtomicLong secuencia = new AtomicLong();

    @GetMapping(value = "/get")
    public Persona persona(){
        Persona persona = leerArchivo();
        return persona;
    }

    @PostMapping(value = "/alta")
    public @ResponseBody Persona altaPersona(@RequestBody Persona persona) throws Exception{
        Persona persona1 = new Persona();
        persona1.setIdPersona(secuencia.incrementAndGet());
        persona1.setNombrePersona(persona.getNombrePersona());
        persona1.setCorreo(persona.getCorreo());
        crearArchivoSalida(persona1);
        return persona1;
    }

    @PutMapping("/actualiza")
    public Persona actualizarRegistro(@RequestBody Persona persona){
        Persona personaGuardada = new Persona();
        personaGuardada = leerArchivo();
        personaGuardada.setNombrePersona(persona.getNombrePersona());
        personaGuardada.setCorreo(persona.getCorreo());
        crearArchivoSalida(personaGuardada);
        return personaGuardada;
    }

    @DeleteMapping("/elimina")
    public void borrarRegistro(){
        eliminarRegistro();
    }

    public void crearArchivoSalida(Persona persona){
        try(FileWriter archivoSalida = new FileWriter("archivoPersonas.json")){

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idPersona", persona.getIdPersona());
            jsonObject.put("nombrePersona", persona.getNombrePersona());
            jsonObject.put("correo", persona.getCorreo());
            archivoSalida.write(jsonObject.toString());
            archivoSalida.flush();

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public Persona leerArchivo() {
        //GsonJsonParser
        //JSONParser
        Gson gson = new Gson();
        String cadena = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("archivoPersonas.json"))){

            String registro;
            registro = bufferedReader.readLine();
            cadena += registro;

        } catch (FileNotFoundException e) {
            //ResponseEntity.noContent();
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        Properties properties = gson.fromJson(cadena, Properties.class);
        Persona persona = gson.fromJson(cadena, Persona.class);
        return persona;
    }

    public void eliminarRegistro(){
        try(FileWriter fileWriter = new FileWriter("archivoPersonas.json")){

            JSONObject jsonObject = new JSONObject();
            jsonObject.remove("nombrePersona");
            jsonObject.remove("correo");
            jsonObject.remove("idPersona");
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
