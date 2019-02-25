
$(document).ready(function () {
    allItems();
});
function descValidate() {
    var description = document.getElementById("description").value;
    var result = true;
    if (description === "") {
        alert("Enter description");
        result = false;
    }

    return result;
}

function allItems() {
    clearTable();
    var chekBox = document.getElementById("check");
    var id;
    var date;
    $.ajax({
        type: "GET",
        url: "/toDo",
        success: function (data) {
            console.log(data);
            $.each(data, function (indexInArray, value) {
                date = new Date(value.created).toLocaleDateString();
                id = value.id;
                if (value.done && chekBox.checked == false) {
                    $('#table tr:last').after(
                        '<tr><td>' + value.id
                        + '</td><td>' + value.desc
                        + '</td><td>' + date
                        + '</td><td><input type="checkbox" name="checkDone" id="' + value.id +'" checked></td></tr>');
                } else if (value.done === false) {
                    $('#table tr:last').after(
                        '<tr><td>' + value.id
                        + '</td><td>' + value.desc
                        + '</td><td>' + date
                        + '</td><td><input type="checkbox" name="checkDone" id="' + value.id +'"></td></tr>');
                }

            })

        }
    })
}


function clearTable() {
    $("#table tr").not(":first").remove();
}

$(document).on('click', 'input[name=checkDone]', function () {
    var taskId = $(this).attr('id');
    console.log(taskId);
    console.log($(this).is(':checked'));
    $.ajax({
        url: 'status.do',
        type: 'get',
        contentType: 'text/html',
        data: ({taskId: taskId, taskDone: $(this).is(':checked')})
    });
});



