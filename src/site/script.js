//script.js

document.addEventListener("DOMContentLoaded", function () {
    const imageContainer = document.getElementById("image-container");
    const resultContainer = document.getElementById("result-container");
    const backButton = document.getElementById("backButton");
    const resultOverlay = document.getElementById("resultOverlay");
    const buttons = document.querySelectorAll('.buttons button');
    const itemsPerPage = 10;
    let currentPage = 1;

    function calculateMedian() {
            const courseName = document.getElementById("courseNameInput").value;
            fetch(`http://localhost:8080/statistics/medianGradesBySubject/${courseName}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Error al obtener la mediana del curso: ${response.status} ${response.statusText}`);
                    }
                    return response.text();
                })
                .then(data => {
                    const medianGradeBySubject = document.getElementById("medianGradeBySubject");
                    if (medianGradeBySubject) {
                        medianGradeBySubject.textContent = `Mediana del curso ${courseName}: ${data}`;
                        showOverlay();
                    } else {
                        console.error('Elemento con ID "medianGradeBySubject" no encontrado.');
                    }
                })
                .catch(error => {
                    console.error(error);
                });
        }

    function loadContentFromFile(file, callback) {
        fetch(file)
                .then(response => response.text())
                .then(content => {
                    resultContainer.innerHTML = content;
                    if (callback) {
                        callback();
                    }
                    showOverlay();
                })
                .catch(error => console.error('Error cargando el contenido:', error));
    }

    function loadStudentDataAndShowTable() {
        fetch('http://localhost:8080/students/all')
            .then(response => response.json())
            .then(data => {
                console.log('Datos obtenidos:', data);
                showStudentTable(data);
                showOverlay();
            })
            .catch(error => {
                console.error('Error al obtener datos de estudiantes:', error);
            });
    }

    function showStudentTable(data) {
        const tableBody = document.getElementById("studentTableBody");
        if (!tableBody) {
            console.error('Elemento con ID "studentTableBody" no encontrado.');
            return;
        }

        tableBody.innerHTML = '';

        if (!data || data.length === 0) {
            console.warn('No hay datos para mostrar en la tabla.');
            return;
        }

        data.forEach(student => {
            const newRow = tableBody.insertRow(-1);
            newRow.insertCell().textContent = student.identificationNumber;
            newRow.insertCell().textContent = student.name;
            newRow.insertCell().textContent = student.calculusGrade;
            newRow.insertCell().textContent = student.spanishGrade;
            newRow.insertCell().textContent = student.socialGrade;
            newRow.insertCell().textContent = student.physicsGrade;
            newRow.insertCell().textContent = student.chemistryGrade;
            newRow.insertCell().textContent = student.citizenEducationGrade;
            newRow.insertCell().textContent = student.philosophyGrade;
            newRow.insertCell().textContent = student.sportsGrade;
        });

        showPagination();
    }

    function showStudentStatisticsTable(data) {
        const tableBody = document.getElementById("statisticsTableBody");

        if (!tableBody) {
            console.error('Elemento con ID "statisticsTableBody" no encontrado.');
            return;
        }

        tableBody.innerHTML = '';

        if (!data || data.length === 0) {
            console.warn('No hay datos para mostrar en la tabla.');
            return;
        }

        data.forEach(studentInfo => {
                const newRow = tableBody.insertRow(-1);
                const cell = newRow.insertCell();

                if (studentInfo) {
                    cell.textContent = studentInfo;
                } else {
                    cell.textContent = "Información no disponible";
                }
            });

            showPagination();
    }

    function loadPassedStudents() {
        fetch('http://localhost:8080/courses/passedStudents')
            .then(response => response.json())
            .then(data => {
                showStudentStatisticsTable(data);
                showOverlay();
            })
            .catch(error => {
                console.error('Error al obtener estudiantes aprobados:', error);
            });
    }

    function loadFailedStudents() {
        fetch('http://localhost:8080/courses/failedStudents')
            .then(response => response.json())
            .then(data => {
                showStudentStatisticsTable(data);
                showOverlay();
            })
            .catch(error => {
                console.error('Error al obtener estudiantes reprobados:', error);
            });
    }

    function showResultsAndMoveImage(buttonText) {
        if (buttonText === 'Listar estudiantes') {
            loadContentFromFile('pages/list.html', showStudentTable);
            loadStudentDataAndShowTable();
        } else if (buttonText === 'Nuevo estudiante') {
            loadContentFromFile('pages/new.html', setupFormSubmit);
        } else if (buttonText === 'Promedio curso') {
              fetch('pages/courseAverageResult.html')
                  .then(response => response.text())
                  .then(content => {
                      resultContainer.innerHTML = content;
                      fetch('http://localhost:8080/courses/average')
                          .then(response => response.json())
                          .then(data => {
                              const courseAverageResultValue = document.getElementById("courseAverageResultValue");
                              if (courseAverageResultValue) {
                                  courseAverageResultValue.textContent = `El promedio del curso es: ${data}`;
                                  showOverlay();
                              } else {
                                  console.error('Elemento con ID "courseAverageResultValue" no encontrado.');
                              }
                          })
                          .catch(error => {
                              console.error('Error al obtener el promedio del curso:', error);
                          });
                  })
                  .catch(error => console.error('Error al cargar "courseAverageResult.html":', error));
        } else if (buttonText === 'Estudiantes aprobados') {
              loadContentFromFile('pages/listStatistics.html', loadPassedStudents);
        }else if (buttonText === 'Estudiantes reprobados') {
              loadContentFromFile('pages/listStatistics.html', loadFailedStudents);
        }else if (buttonText === 'Mejor promedio') {
              fetch('pages/highestAverageStudent.html')
                  .then(response => response.text())
                  .then(content => {
                      resultContainer.innerHTML = content;
                      fetch('http://localhost:8080/students/highestAverageStudent')
                           .then(response => response.text())
                           .then(data => {
                               const highestAverageStudent = document.getElementById("highestAverageStudent");
                               if (highestAverageStudent) {
                                   highestAverageStudent.textContent = `El mejor promedio es ${data}`;
                                   showOverlay();
                                   } else {
                                        console.error('Elemento con ID "highestAverageStudent" no encontrado.');
                                   }
                                   })
                                   .catch(error => {
                                       console.error('Error al obtener el mejor promedio:', error);
                                   });
                  })
                  .catch(error => console.error('Error al cargar "highestAverageStudent.html":', error));
        }else if (buttonText === 'Peor promedio') {
              fetch('pages/lowestAverageStudent.html')
                  .then(response => response.text())
                  .then(content => {
                      resultContainer.innerHTML = content;
                      fetch('http://localhost:8080/students/lowestAverageStudent')
                           .then(response => response.text())
                           .then(data => {
                               const lowestAverageStudent = document.getElementById("lowestAverageStudent");
                               if (lowestAverageStudent) {
                                   lowestAverageStudent.textContent = `El promedio más bajo es ${data}`;
                                   showOverlay();
                                   } else {
                                        console.error('Elemento con ID "lowestAverageStudent" no encontrado.');
                                   }
                                   })
                                   .catch(error => {
                                       console.error('Error al obtener el peor promedio:', error);
                                   });
                  })
                  .catch(error => console.error('Error al cargar "lowestAverageStudent.html":', error));
        } else if (buttonText === 'Mediana de los cursos') {
              fetch('pages/medianGradesForAllSubjects.html')
                  .then(response => response.text())
                  .then(content => {
                      resultContainer.innerHTML = content;
                      fetch('http://localhost:8080/statistics/medianGradesForAllSubjects')
                           .then(response => response.text())
                           .then(data => {
                               const medianGradesForAllSubjects = document.getElementById("medianGradesForAllSubjects");
                               if (medianGradesForAllSubjects) {
                                   medianGradesForAllSubjects.textContent = `Mediana de los cursos ${data}`;
                                   showOverlay();
                                   } else {
                                        console.error('Elemento con ID "medianGradesForAllSubjects" no encontrado.');
                                   }
                                   })
                                   .catch(error => {
                                       console.error('Error al obtener la mediana de los cursos:', error);
                                   });
                  })
                  .catch(error => console.error('Error al cargar "medianGradesForAllSubjects.html":', error));
        } else if (buttonText === 'Mediana por curso') {
            fetch('pages/medianGradeBySubject.html')
                .then(response => response.text())
                .then(content => {
                    resultContainer.innerHTML = content;
                    document.getElementById("calculateMedianBtn").addEventListener("click", calculateMedian);
                })
                .catch(error => console.error('Error al cargar "medianGradeBySubject.html":', error));
        }else {
            const resultado = showResult(buttonText);
            if (resultContainer) {
                resultContainer.textContent = resultado;
                showOverlay();
            }
        }
    }

    function goBack() {
        backButton.style.opacity = '0';
        resultContainer.style.display = 'none';

        setTimeout(() => {
            resultOverlay.style.display = 'none';
            imageContainer.style.transition = 'opacity 1s';
            imageContainer.style.opacity = '1';
        }, 100);

        resultContainer.textContent = '';
    }

    function showOverlay() {
        imageContainer.style.transition = 'opacity 1s';
        imageContainer.style.opacity = '0';

        setTimeout(() => {
            resultOverlay.style.display = 'flex';
        }, 800);

        setTimeout(() => {
            backButton.style.opacity = '1';
            resultContainer.style.display = 'block';
        }, 2000);
    }

    buttons.forEach(button => {
        button.addEventListener('click', function () {
            showResultsAndMoveImage(this.innerText);
        });
    });
    backButton.addEventListener('click', goBack);

    function showPagination() {
        const table = document.querySelector('table');
        const rows = table.querySelectorAll('tr');

        const totalPages = Math.ceil((rows.length - 1) / itemsPerPage);

        document.getElementById('currentPage').innerText = currentPage;

        rows.forEach(row => {
            row.style.display = 'none';
        });

        const start = (currentPage - 1) * itemsPerPage + 1;
        const end = start + itemsPerPage - 1;

        for (let i = start; i <= end && i < rows.length; i++) {
            rows[i].style.display = '';
        }
    }

    function prevPage() {
        if (currentPage > 1) {
            currentPage--;
            showPagination();
        }
    }

    function nextPage() {
        const table = document.querySelector('table');
        const rows = table.querySelectorAll('tr');

        if (currentPage < Math.ceil((rows.length - 1) / itemsPerPage)) {
            currentPage++;
            showPagination();
        }
    }

    const studentForm = document.getElementById("studentForm");
    if (studentForm) {
        studentForm.addEventListener("submit", setupFormSubmit);
    }

    window.setupFormSubmit = setupFormSubmit;
    window.nextPage = nextPage;
    window.prevPage = prevPage;

    function setupFormSubmit() {
        const studentForm = document.getElementById("studentForm");
        console.log(studentForm);
        studentForm.addEventListener("submit", function (event) {
            event.preventDefault();

            const formData = new FormData(studentForm);

            console.log(formData);
            console.log("Datos del formulario:", formData);
            const studentData = {
                identificationNumber: formData.get("id"),
                name: formData.get("name"),
                calculusGrade: parseFloat(formData.get("calculusGrade")),
                spanishGrade: parseFloat(formData.get("spanishGrade")),
                socialGrade: parseFloat(formData.get("socialGrade")),
                physicsGrade: parseFloat(formData.get("physicsGrade")),
                chemistryGrade: parseFloat(formData.get("chemistryGrade")),
                citizenEducationGrade: parseFloat(formData.get("citizenGrade")),
                philosophyGrade: parseFloat(formData.get("philosophyGrade")),
                sportsGrade: parseFloat(formData.get("sportsGrade")),

            };

            console.log("Datos del estudiante:", studentData);

            fetch('http://localhost:8080/students/createStudent', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(studentData),
            })
            .then(response => response.text())
            .then(result => {
                console.log(result);
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
    }
});

function showResult(buttonText) {
    return `Resultado para ${buttonText}`;
}