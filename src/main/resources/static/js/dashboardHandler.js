function showKeys(keys) {
    var text = "";
    var href = $("#show");
    var table= $("#keys_table");
    if (showMore) {
        href[0].text = "Show less";

        keys = keys.slice(6, keys.length);
        $.each(keys, function (index, value) {
            text += "  <tr>\n" +
                "           <td class=\"center-align\"><b>" + value.name + "</b></td>\n" +
                "           <td><i>" + value.value + "</i></td>\n" +
                "      </tr>"
        });
    } else {
        href[0].text = "Show more";

        keys = keys.slice(1, 6);
        table[0].innerHTML="";
        $.each(keys, function (index, value) {
            text += "  <tr>\n" +
                "           <td class=\"center-align\"><b>" + value.name + "</b></td>\n" +
                "           <td><i>" + value.value + "</i></td>\n" +
                "      </tr>"
        });

    }
    showMore = !showMore;
    table.append(text);
}