
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
    var date;
    $.ajax({
        type: "GET",
        url: "/toDo",
        success: function (data) {
            console.log(data);
            $.each(data, function (indexInArray, value) {
                date = new Date(value.created).toLocaleDateString();
                if (value.done) {
                    $('#table tr:last').after(
                        '<tr><td>' + value.id
                        + '</td><td>' + value.desc
                        + '</td><td>' + date
                        + '</td><td>' + value.done
                        + '</td><td><form><input type="checkbox" checked></form></td></tr>');
                } else {
                    $('#table tr:last').after(
                        '<tr><td>' + value.id
                        + '</td><td>' + value.desc
                        + '</td><td>' + date
                        + '</td><td>' + value.done
                        + '</td><td><form><input type="checkbox"></form></td></tr>');
                }

            })

        }
    })
}
function toDoList() {
    var chekBox = document.getElementById("check");
    var date;
    if (chekBox.checked == true) {
        clearTable();
        $.ajax({
            type: "GET",
            url: "/toDo",
            success: function (data) {
                console.log(data);
                $.each(data, function (indexInArray, value) {
                    if (value.done === false) {
                        date = new Date(value.created).toLocaleDateString();
                        $('#table tr:last').after(
                            '<tr><td>' + value.id
                            + '</td><td>' + value.desc
                            + '</td><td>' + date
                            + '</td><td>' + value.done
                            + '</td><td><form><input  type="checkbox" ></form></td></tr>');
                    }

                })

            }
        })
    }
    else allItems();
}

function clearTable() {
    $("#table tr").not(":first").remove();
}

function changeStatus(id) {
    var change = document.ge
    if (change.checked) {
        console.log("ckecked");
    }
}

