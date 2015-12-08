function check(input) {
        var reg=/^\.|\d+\..*\.|[^\d\.{1}]/g;
        input.value = input.value.replace(reg, '');
};

$(document).ready(function(){
	$("div.container").css("background-color", "#FFDEAD");
});