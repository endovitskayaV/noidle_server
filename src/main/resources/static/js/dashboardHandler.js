var tableType = {
    keys: 1,
    langs: 2
};

$(document).ready(function () {
    $('.sidenav').sidenav();
});

document.addEventListener('DOMContentLoaded', function () {
    var elems = document.querySelectorAll('.collapsible');
    var instances = M.Collapsible.init(elems);
});

document.addEventListener('DOMContentLoaded', function () {
    var elem = document.querySelector('.datepicker');
    var instance = M.Datepicker.init(elem, {
        onOpen: function (e, data) {
            $('#slide-out').width("500px");
        },
        onClose: function (e, data) {
            $('#slide-out').width("300px");
        },
        setDefaultDate: true,
        firstDay: 1,
        format: 'd.m.yyyy'
    });

    var today = new Date();
    var d = today.getDate();
    var m = today.getMonth() + 1;
    var y = today.getFullYear();
    $('#date-input')[0].value = d + "." + m + "." + y;
});

function show(link, table, data, flag, type) {
    var text = "";
    if (flag.value) {
        link[0].text = "Show less";
        data = data.slice(6, data.length);
    } else {
        link[0].text = "Show more";
        data = data.slice(1, 6);
        table[0].innerHTML = "";
    }

    $.each(data, function (index, value) {
        switch (type) {
            case tableType.keys:
                text += "<tr>\n" +
                    "           <td class=\"center-align\"><b>" + value.name + "</b></td>\n" +
                    "           <td><i>" + value.value + "</i></td>\n" +
                    "    </tr>";
                break;
            case tableType.langs:
                text += "<tr>\n" +
                    "         <td><b>" + value.languageName + "</b></td>\n" +
                    "         <td><i>" + value.time + "</i></td>\n" +
                    "         <td><i>" + value.symbols + "</i></td>\n" +
                    "    </tr>";
                break;
        }

    });
    flag.value = !flag.value;
    table.append(text);
}

function applyStatisticsFilters() {
    //  $('.sidenav').sidenav('close');
}

function enableTeams() {
    radioHandler(true);
}

function disableTeams() {
    radioHandler(false);
}

function radioHandler(checked) {
    var myArray = $('input[name="groupTeam"]');
    $.each(myArray, function (index, value) {
        var text = "<input name=\"groupTeam\" type=\"radio\" class=\"with-gap\"";
        if (!checked) {
            text += "disabled=\"disabled\"/>";
        } else {
            text += "/>";
        }
        value.outerHTML = text;
    });
}