window.addEventListener("load", function () {
  (function () {
    //con fetch invocamos a la API de odontologos con el método GET
    //nos devolverá un JSON con una colección de odontologos
    const url = "/pacientes";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        //recorremos la colección de odontologos del JSON
        for (paciente of data) {
          //por cada odontologo armaremos una fila de la tabla
          //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el odontologo
          var table = document.getElementById("pacienteTable");
          var pacienteRow = table.insertRow();
          let tr_id = "tr_" + paciente.id;
          pacienteRow.id = tr_id;

          //por cada odontologo creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
          //dicho boton invocara a la funcion de java script deleteByKey que se encargará
          //de llamar a la API para eliminar un odontologo
          let deleteButton =
            "<button" +
            " id=" +
            '"' +
            "btn_delete_" +
            paciente.id +
            '"' +
            ' type="button" onclick="deleteBy(' +
            paciente.id +
            ')" class="action-bt">' +
            "Eliminar" +
            "</button>";

          //por cada odontologo creamos un boton que muestra el id y que al hacerle clic invocará
          //a la función de java script findBy que se encargará de buscar al odontologo que queremos
          //modificar y mostrar los datos del mismo en un formulario.
          let updateButton =
            "<button" +
            " id=" +
            '"' +
            "btn_id_" +
            paciente.id +
            '"' +
            ' type="button" onclick="findBy(' +
            paciente.id +
            ')" class="action-bt">' +
            "Modificar" +
            "</button>";

          //armamos cada columna de la fila
          //como primer columna pondremos el boton modificar
          //luego los datos del odontologo
          //como ultima columna el boton eliminar
          pacienteRow.innerHTML =
            "<td>" +
            paciente.id +
            "</td>" +
            '<td class="td_nombre">' +
            paciente.nombre.toUpperCase() +
            "</td>" +
            '<td class="td_apellido">' +
            paciente.apellido.toUpperCase() +
            "</td>" +
            '<td class="td_dni">' +
            paciente.dni +
            "</td>" +
            '<td class="td_fechaIngreso">' +
            paciente.fechaIngreso +
            "</td>" +
            '<td class="td_domicilio">' +
            paciente.domicilio.calle +
            " " +
            paciente.domicilio.numero +
            ", " +
            paciente.domicilio.localidad +
            ", " +
            paciente.domicilio.provincia +
            "</td>" +
            '<td class="td_email">' +
            paciente.email +
            "</td>" +
            "<td>" +
            updateButton +
            "</td>" +
            "<td>" +
            deleteButton +
            "</td>";
        }
      });
  })(function () {
    let pathname = window.location.pathname;
    if (pathname == "/pacientes.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  });
});
