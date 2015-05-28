$(function () {
    $("#input-buscar").keyup(function () {
        var texto = $(this).val();
        $("#lista-left-menu li").css("display", "block");
        $("#lista-left-menu li").each(function () {
            if ($(this).text().toUpperCase().indexOf(texto.toUpperCase()) < 0)
                $(this).css("display", "none");
        });

    });
});
