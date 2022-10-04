window.addEventListener("load", function () {
  (function () {
    const url = "/turnos";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);

        for (turno of data) {
          var table = document.getElementById("turnoTable");
          var turnoRow = table.insertRow();
          let tr_id = "tr_" + turno.idTurno;
          turnoRow.id = tr_id;

          let deleteButton =
            "<button" +
            " id=" +
            '"' +
            "btn_delete_" +
            turno.idTurno +
            '"' +
            ' type="button" onclick="deleteBy(' +
            turno.idTurno +
            ')" class="action-bt">' +
            "Eliminar" +
            "</button>";

          // let updateButton =
          //   "<button" +
          //   " id=" +
          //   '"' +
          //   "btn_id_" +
          //   turno.idTurno +
          //   '"' +
          //   ' type="button" onclick="findBy(' +
          //   turno.idTurno +
          //   ')" class="action-bt">' +
          //   "Modificar" +
          //   "</button>";


          turnoRow.innerHTML =
            "<td>" +
            turno.idTurno +
            "</td>" +
            '<td class="td_fecha">' +
            turno.fecha +
            "</td>" +
            '<td class="td_hora">' +
            turno.hora +
            "</td>" +
            '<td class="td_odontologo">' +
            turno.odontologo.apellido +
            ", " +
            turno.odontologo.nombre +
            "</td>" +
            '<td class="td_paciente">' +
            turno.paciente.apellido +
            ", " +
            turno.paciente.nombre +
            "</td>" +
            // "<td>" +
            // updateButton +
            // "</td>" +
            "<td>" +
            deleteButton +
            "</td>";
        }
      });
  })(function () {
    let pathname = window.location.pathname;
    if (pathname == "/turnos.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  });
});
