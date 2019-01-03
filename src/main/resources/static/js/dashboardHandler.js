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
    if (selectedStartDate !== 'undefined' && selectedStartDate!=="") {
        $('#start-date-input')[0].value = selectedStartDate;
    }
    if (selectedEndDate !== 'undefined' && selectedEndDate!=="") {
        $('#end-date-input')[0].value = selectedEndDate;
    }
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
    var doApply = true;
    var startDateInput = $("#start-date-input")[0];
    if ($("#date-picker-radio")[0].checked) {
        dateQueryString = '?date=' + $('#date-input')[0].value;
    }
    else if ($("#start-date-picker-radio")[0].checked) {
        if (startDateInput.value === "") {
            doApply = false;
            $("#start-date-input").css("border-bottom", "1px solid red");
        }

        var endDateInput = $("#end-date-input")[0];
        if (endDateInput.value === "") {
            doApply = false;
            $("#end-date-input").css("border-bottom", "1px solid red");
        }

        dateQueryString = '?startDate=' + startDateInput.value +
            '&endDate=' + endDateInput.value;
    }

    if (doApply) {
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

function enableElem(elem) {
    elem.removeAttr("disabled");
}

function disableElem(elem) {
    elem.attr("disabled", "disabled");
}

function checkedElem(elem) {
    elem.attr("checked", "checked");
}
