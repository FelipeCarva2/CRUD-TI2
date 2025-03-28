package service;

import dao.CachorroDAO;
import model.Cachorro;
import spark.Request;
import spark.Response;

public class CachorroService {
    private CachorroDAO cachorroDAO;

    public CachorroService() {
        cachorroDAO = new CachorroDAO();
        cachorroDAO.conectar(); // Conecta ao PostgreSQL
    }

    // CADASTRAR CACHORRO (POST)
    public Object add(Request request, Response response) {
        try {
            int codigo = Integer.parseInt(request.queryParams("id"));
            String nome = request.queryParams("nome");
            String raca = request.queryParams("raca");
            char sexo = request.queryParams("sexo").charAt(0);

            Cachorro cachorro = new Cachorro(codigo, nome, raca, sexo);

            if (cachorroDAO.inserirCachorro(cachorro)) {
                response.status(201); // 201 Created
                return "<mensagem>Cachorro cadastrado com ID: " + codigo + "</mensagem>";
            } else {
                response.status(500);
                return "<erro>Falha ao cadastrar cachorro</erro>";
            }
        } catch (Exception e) {
            response.status(400); // Bad Request
            return "<erro>Dados inválidos</erro>";
        }
    }

    //BUSCAR CACHORRO POR ID (GET)
    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Cachorro[] cachorros = cachorroDAO.getCachorros();

        for (Cachorro c : cachorros) {
            if (c.getCodigo() == id) {
                response.type("application/xml");
                return "<cachorro>\n" +
                       "   <id>" + c.getCodigo() + "</id>\n" +
                       "   <nome>" + c.getNome() + "</nome>\n" +
                       "   <raca>" + c.getRaca() + "</raca>\n" +
                       "   <sexo>" + c.getSexo() + "</sexo>\n" +
                       "</cachorro>";
            }
        }

        response.status(404);
        return "<erro>Cachorro não encontrado</erro>";
    }

    //ATUALIZAR CACHORRO (POST)
    public Object update(Request request, Response response) {
        try {
            // Obtém os parâmetros do formulário
            int codigo = Integer.parseInt(request.queryParams("codigo"));
            String nome = request.queryParams("nome");
            String raca = request.queryParams("raca");
            String sexoParam = request.queryParams("sexo");
            
            // Validação básica dos parâmetros
            if (nome == null || nome.isEmpty() || 
                raca == null || raca.isEmpty() || 
                sexoParam == null || sexoParam.isEmpty()) {
                response.status(400);
                return "Todos os campos são obrigatórios!";
            }
            
            char sexo = sexoParam.charAt(0);
            if (sexo != 'M' && sexo != 'F') {
                response.status(400);
                return "Sexo deve ser 'M' ou 'F'!";
            }

            // Cria o objeto Cachorro
            Cachorro cachorro = new Cachorro(codigo, nome, raca, sexo);
            
            // Chama o DAO para atualizar
            if (cachorroDAO.atualizarCachorro(cachorro)) {
                response.status(200);
                return "Cachorro atualizado com sucesso! ID: " + codigo;
            } else {
                response.status(404);
                return "Cachorro com ID " + codigo + " não encontrado!";
            }
            
        } catch (NumberFormatException e) {
            response.status(400);
            return "ID inválido! Deve ser um número.";
        } catch (Exception e) {
            response.status(500);
            return "Erro ao atualizar cachorro: " + e.getMessage();
        }
    }
    //REMOVER CACHORRO (GET)
    public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        if (cachorroDAO.excluirCachorro(id)) {
            return "<mensagem>Cachorro removido com ID: " + id + "</mensagem>";
        } else {
            response.status(404);
            return "<erro>Cachorro não encontrado</erro>";
        }
    }

    //LISTAR TODOS (GET)
    public Object getAll(Request request, Response response) {
        Cachorro[] cachorros = cachorroDAO.getCachorros();
        StringBuilder xml = new StringBuilder("<cachorros>\n");

        for (Cachorro c : cachorros) {
            xml.append("   <cachorro>\n")
               .append("       <id>").append(c.getCodigo()).append("</id>\n")
               .append("       <nome>").append(c.getNome()).append("</nome>\n")
               .append("       <raca>").append(c.getRaca()).append("</raca>\n")
               .append("       <sexo>").append(c.getSexo()).append("</sexo>\n")
               .append("   </cachorro>\n");
        }

        xml.append("</cachorros>");
        response.type("application/xml");
        return xml.toString();
    }
}