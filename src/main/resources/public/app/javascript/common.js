

"use strict";



$(function(){
	var isVisible = false;
	
	$('table th.showInfo').click(function(){
		if (isVisible) {
			$(this).find('span').removeClass('fa-minus-square').addClass('fa-plus-square');
			
			$('td a.info').each(function(){
				var span = $('<span>').text($(this).text());
				span.addClass('info');
				
				$(this).text('...');
				$(this).append(span);
			});
			
			isVisible = false;
		}
		else {
			$(this).find('span').removeClass('fa-plus-square').addClass('fa-minus-square');
			
			$('td a.info').each(function(){
				$(this).text($(this).find('span.info').text());
				$(this).css('text-decoration', 'none');
				$(this).css('color', 'black');
			});
			
			isVisible = true;
		}
	});
});



