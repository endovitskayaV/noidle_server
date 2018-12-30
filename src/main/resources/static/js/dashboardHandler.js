function show(link, table, data, flag, type) {
    var text = "";
    if (flag.value) {
        link[0].text = "Show less";
        data = data.slice(6, data.length);
    } else {
        link[0].text = "Show more";
        data = data.slice(1, 6);
        table[0].innerHTML="";
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
                    "         <td><b>"+value.languageName+"</b></td>\n" +
                    "         <td><i>"+value.time+"</i></td>\n" +
                    "         <td><i>"+value.symbols+"</i></td>\n" +
                    "    </tr>";
                break;
        }

    });
    flag.value = !flag.value;
    table.append(text);
}