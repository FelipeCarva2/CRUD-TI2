package app;

import static spark.Spark.*;

import service.CachorroService;

public class Aplicacao {
    public static void main(String[] args) {
        port(6789);
        CachorroService service = new CachorroService();

        // POST: Cadastrar
        post("/animal", (request, response) -> service.add(request, response));

        // GET: Buscar por ID
        get("/cachorro/:id", (request, response) -> service.get(request, response));

        // GET: Remover
        get("/cachorro/delete/:id", (request, response) -> service.remove(request, response));

        // POST: Atualizar
        post("/cachorro/update/:id", (request, response) -> service.update(request, response));

        //GET: Listar todos
        get("/cachorro", (request, response) -> service.getAll(request, response));
    }
}