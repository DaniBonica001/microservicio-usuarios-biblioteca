package co.analisys.biblioteca.controller;

import co.analisys.biblioteca.model.Email;
import co.analisys.biblioteca.model.Usuario;
import co.analisys.biblioteca.model.UsuarioId;
import co.analisys.biblioteca.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Operation(
            summary = "Obtener información de un usuario",
            description = "Este endpoint permite obtener la información de un usuario específico proporcionando su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado y devuelto exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable String id) {
        return usuarioService.obtenerUsuario(new UsuarioId(id));
    }

    @Operation(
            summary = "Cambiar el email de un usuario",
            description = "Este endpoint permite cambiar el email de un usuario específico proporcionando su ID y el nuevo email."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}/email")
    public void cambiarEmail(@PathVariable String id, @RequestBody String nuevoEmail) {
        usuarioService.cambiarEmailUsuario(new UsuarioId(id), new Email(nuevoEmail));
    }
}
