<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Paciente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Central de Pacientes</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            table {
                border-collapse: collapse;
                width: 100%;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #007bff;
                color: white;
            }
            .formulario {
                background-color: #f2f2f2;
                padding: 20px;
                border-radius: 5px;
                width: 50%;
            }
            input, select, button {
                margin-bottom: 10px;
                padding: 5px;
                width: 95%;
            }
            button {
                background-color: #28a745;
                color: white;
                border: none;
                cursor: pointer;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Gestión - Central de Pacientes</h1>

        <div class="formulario">
            <h3>Registrar Nuevo Paciente</h3>
            <!-- El formulario envía los datos por POST a nuestro PacienteServlet (cuya ruta es /pacientes) -->
            <form action="pacientes" method="POST">
                <label>Código:</label>
                <input type="number" name="codigo" required>

                <label>Nombre:</label>
                <input type="text" name="nombre" required>

                <label>Clínica:</label>
                <input type="text" name="clinica" required>

                <label>Información Médica:</label>
                <input type="text" name="informacionMedica" required>

                <label>Sexo:</label>
                <select name="sexo">
                    <option value="1">Hombre</option>
                    <option value="2">Mujer</option>
                </select>

                <button type="submit">Agregar Paciente</button>
            </form>
        </div>

        <h3>Listado de Pacientes</h3>

        <!-- Aquí recuperamos el nodo 'primero' que nos envió el Servlet -->
        <% Paciente actual = (Paciente) request.getAttribute("primero"); %>

        <table>
            <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Clínica</th>
                <th>Información médica</th>
                <th>Sexo</th>
            </tr>

            <!-- Recorremos la lista enlazada mientras el nodo actual no sea nulo -->
            <% while (actual != null) {%>
            <tr>
                <td><%= actual.getCodigo()%></td>
                <td><%= actual.getNombre()%></td>
                <td><%= actual.getClinica()%></td>
                <td><%= actual.getInformacionMedica()%></td>
                <td><%= actual.getSexo() == 1 ? "Hombre" : "Mujer"%></td>
            </tr>
            <!-- Avanzamos al siguiente nodo de la lista -->
            <% actual = actual.getSiguiente();
              }%>
        </table>

    </body>
</html>