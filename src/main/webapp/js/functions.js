var ajax_groups = "/visitator/ajax/groups";
var ajax_subjects = "/visitator/ajax/subjects";

$(document).ready(function() {

    $("#fclty").val(0);
    $("#subjects").val("");


    $("#types").change(function() {
        if($("#types").val() == 1)
        {
            $("#groups").attr('multiple','multiple');
        }
        else
        {
            $("#groups").attr('multiple', false);
        }
    });

    $("#fclty").change(function() {
        var id = $("#fclty").val();
        if (id != "0") {
            getGroups(id);
            getSubjects(id);
        }
    });

    $("#checkCollege").change(function() {
        if(this.checked) {
            document.getElementById('alert').innerHTML = '/ / / / / / THIS COLLEGE WILL BE DELETED! / / / / / /';
        }
        else
            document.getElementById('alert').innerHTML = '';
    });

    $("#checkLesson").change(function() {
        if(this.checked) {
            document.getElementById('alert').innerHTML = '/ / / / / / THIS LESSON WILL BE DELETED! / / / / / /';
        }
        else
            document.getElementById('alert').innerHTML = '';
    });

    $("#checkFaculty").change(function() {
        if(this.checked) {
            document.getElementById('alert').innerHTML = '/ / / / / / THIS FACULTY WILL BE DELETED! / / / / / /';
        }
        else
            document.getElementById('alert').innerHTML = '';
    });

    $("#checkTeacher").change(function() {
        if(this.checked) {
            document.getElementById('alert').innerHTML = '/ / / / / / THIS TEACHER WILL BE DELETED! / / / / / /';
        }
        else
            document.getElementById('alert').innerHTML = '';
    });

    $("#checkGroup").change(function() {
        if(this.checked) {
            document.getElementById('alert').innerHTML = '/ / / / / / THIS GROUP WILL BE DELETED! / / / / / /';
        }
        else
            document.getElementById('alert').innerHTML = '';
    });

    $("#checkStudent").change(function() {
        if(this.checked) {
            document.getElementById('alert').innerHTML = '/ / / / / / THIS STUDENT WILL BE DELETED! / / / / / /';
        }
        else
            document.getElementById('alert').innerHTML = '';
    });

    $("#checkSubject").change(function() {
        if(this.checked) {
            document.getElementById('alert').innerHTML = '/ / / / / / THIS SUBJECT WILL BE DELETED! / / / / / /';
        }
        else
            document.getElementById('alert').innerHTML = '';
    });

});

function getGroups(data) {

    $.getJSON(ajax_groups, {
        facultyId : data,
        ajax : 'true'
    }, function(response) {

        var select = $('#groups');
        select.find('option').remove();

        if($.isEmptyObject(response)) {
            $('<option>').val('').text('No groups').appendTo(select);
        }

        else {
            $.each(response, function (index, value) {
                $('<option>').val(value).text(value).appendTo(select);
            })
        }
    })
}

function getSubjects(data) {

    $.getJSON(ajax_subjects, {
        facultyId : data,
        ajax : 'true'
    }, function(response) {

        var select = $('#subjects');
        select.find('option').remove();

        if($.isEmptyObject(response)) {
            $('<option>').val("").text('No subjects').appendTo(select);
        }

        $.each(response, function(index, value) {
            $('<option>').val(value).text(value).appendTo(select);
        });

    });
}

function setLessonEnd(duration) {

    var x = document.getElementById("lStart").value;
    var y = document.getElementById("lEnd");
    var d = document.getElementById("date").value;
    var j = new Date(d + " " + x );
    var h, m;

    j.setMinutes(j.getMinutes() + duration);

    if(j.getHours() < 10)
        h = "" + 0 + j.getHours();
    else
        h = j.getHours();

    if(j.getMinutes() < 10)
        m = "" + 0 + j.getMinutes();
    else
        m = j.getMinutes();

    y.value = h + ":" + m;
}

