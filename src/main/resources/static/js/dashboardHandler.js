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
    var dateElems = document.querySelectorAll('.datepicker');
    $.each(dateElems, function (index, value) {
        M.Datepicker.init(value, {
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
    });

    $('#date-input')[0].value = selectedDate;
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
    var dateQueryString = "";
    if ($("#date-picker-radio")[0].checked) {
        dateQueryString = '?date=' + $('#date-input')[0].value;
    }
    var teamQueryString = "";
    if ($("#teamRadio")[0].checked) {
        var teamRadios = $('input[name="groupTeam"]');
        $.each(teamRadios, function (index, value) {
            if (value.checked) {
                if (dateQueryString === "") {
                    teamQueryString = "?"
                } else {
                    teamQueryString = "&"
                }
                teamQueryString += "teamId=" + value.id;
            }
        });
    }

    window.location.href = '/dashboard' + dateQueryString + teamQueryString;
}

function enableTeams() {
    radioHandler(true);
}

function disableTeams() {
    radioHandler(false);
}

function radioHandler(checked) {
    var teamRadios = $('input[name="groupTeam"]');
    $.each(teamRadios, function (index, value) {
        var text = "<input name=\"groupTeam\" type=\"radio\" class=\"with-gap\" id=\"" + value.id + "\"";
        if (!checked) {
            text += " disabled=\"disabled\"/>";
        } else {
            if (index === 0) {
                text += " checked";
            }
            text += "/>";
        }
        value.outerHTML = text;
    });
}

function enableDatePicker() {
    $("#date-input").removeAttr("disabled");
}

function disableDatePicker() {
    $("#date-input").attr("disabled", "disabled");
}