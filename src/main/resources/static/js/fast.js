 function createCookie(name, value, days) {
    var expires;

    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toGMTString();
    } else {
        expires = "";
    }
    document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + expires + "; path=/";
}
function loadLanguage()
{
	var test;
	          $.ajax({
                type: 'GET',
                url: 'http://127.0.0.1:8090/show/language',
                dataType: 'json',
                data: $(this).serialize(),
                success: function (json) {					
					 test=json[0];
					 if(test=="1")
                     {
                         test="PL";
                     }
                     else
                     {
                         test="EN";
                     }
					createCookie("language",test,90000);
                },
                error: function (xhr) {
					createCookie("language","EN",10);
                }
            });
}
